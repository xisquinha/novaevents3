package pt.unl.fct.iadi.novaevents.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents.controller.EventForm
import pt.unl.fct.iadi.novaevents.controller.dto.EditEventRequest
import pt.unl.fct.iadi.novaevents.model.Event
import pt.unl.fct.iadi.novaevents.model.EventType
import pt.unl.fct.iadi.novaevents.repository.EventRepository
import java.time.LocalDate

@Service
class EventService(
    private val clubService: ClubService,
    private val eventRepository: EventRepository,
    private val eventTypeService: EventTypeService
    ) {

    fun findById(id: Long): Event = eventRepository.findById(id).orElseThrow()
    fun findAll(): List<Event> = eventRepository.findAll()

    fun filter(type: EventType?, clubId: Long?, from: LocalDate?,
               to: LocalDate?): List<Event>
    {
        if(clubId != null) clubService.findById(clubId)

        val events = eventRepository.findByClubId_Type_DateRange(clubId, type, from, to)
        return events
    }

    fun countGroupedByClub(): Map<Long, Long> =
        eventRepository.countEventsGroupedByClub()
            .associate { (it[0] as Long) to (it[1] as Long) }

    fun createEvent(request: EventForm, clubId: Long): Event {

        clubService.findById(clubId)

        val event = Event()
        event.club = clubService.findById(clubId)
        event.name = request.name
        event.date = request.date
        event.location = request.location
        event.type = eventTypeService.findByName(request.type!!)
        event.description = request.description

        eventRepository.save(event)
        return event
    }

    fun editEvent(ogId: Long, request: EditEventRequest, clubId: Long): Event {

        clubService.findById(clubId)

        val ogEvent = findById(ogId)

        if (request.name != ogEvent.name) ogEvent.name = request.name
        if (request.date != ogEvent.date) ogEvent.date = request.date!!
        if (request.location != ogEvent.location) ogEvent.location = request.location
        if (request.type != ogEvent.type) ogEvent.type = request.type!!
        if (request.description != ogEvent.description) ogEvent.description = request.description

        eventRepository.save(ogEvent)
        return ogEvent
    }

    fun deleteEvent(id: Long) =
        eventRepository.delete(
            findById(id)
        )


    fun getEventsFromClub(clubId: Long): List<Event>{

        clubService.findById(clubId)
        return eventRepository.findByClubId_Type_DateRange(clubId)
    }

    // true if there is a duplicate (event with the same name)
    fun checkDuplicateName(name: String): Boolean{
        return eventRepository.findDuplicate(name) != null
    }

    /*fun findNumEventsFromClub(clubId: Long): Int =
        eventRepository.findNumEventsFromClub(clubId)*/
}