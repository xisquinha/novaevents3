package pt.unl.fct.iadi.novaevents2.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents2.controller.EventForm
import pt.unl.fct.iadi.novaevents2.controller.dto.EditEventRequest
import pt.unl.fct.iadi.novaevents2.model.Event
import pt.unl.fct.iadi.novaevents2.model.EventType
import pt.unl.fct.iadi.novaevents2.repository.ClubRepository
import pt.unl.fct.iadi.novaevents2.repository.EventRepository
import java.time.LocalDate
import java.util.*
import kotlin.NoSuchElementException

@Service
class EventService(private val clubRepository: ClubRepository, private val eventRepository: EventRepository ) {

    private var actualId: Long = 25

    fun newId(): Long{
        actualId += 1
        return actualId
    }

    fun filter(type: EventType?, clubId: Long?, from: LocalDate?,
               to: LocalDate?): List<Event>
    {
        if(clubId != null) clubRepository.findById(clubId)

        val events = eventRepository.findAll().filter{ event ->
            (type == null || event.type == type) &&
            (clubId == null || clubId == event.clubId) &&
            (from == null || !event.date.isBefore(from)) &&
            (to == null || event.date.isAfter(to))
        }
        return events
    }

    fun createEvent(request: EventForm, clubId: Long): Event {

        val id = newId()
        clubRepository.findById(clubId)

        val event = Event(id, clubId, request.name, request.date!!,
            request.location, request.type!!, request.description)

        events[event.id] = event
        return event
    }

    fun editEvent(ogId: Long, request: EditEventRequest, clubId: Long): Event {

        clubRepository.findById(clubId)

        val ogEvent = eventRepository.findById(ogId).orElseThrow()

        if (request.name != ogEvent.name) ogEvent.name = request.name
        if (request.date != ogEvent.date) ogEvent.date = request.date!!
        if (request.location != ogEvent.location) ogEvent.location = request.location
        if (request.type != ogEvent.type) ogEvent.type = request.type!!
        if (request.description != ogEvent.description) ogEvent.description = request.description

        events[ogId] = ogEvent

        return ogEvent
    }

    fun deleteEvent(id: Long) =
        eventRepository.delete(
            eventRepository.findById(id).orElseThrow()
        )


    fun getEventsFromClub(clubId: Long): List<Event>{

        val evs = mutableListOf<Event>()

        clubRepository.findById(clubId)

        for(event in eventRepository.findAll()){
            if(event.clubId == clubId)
                evs.add(event)
        }
        return evs
    }

    fun checkDuplicateName(name: String): Boolean{
        return eventRepository.findAll().any { it.name == name }
    }
}