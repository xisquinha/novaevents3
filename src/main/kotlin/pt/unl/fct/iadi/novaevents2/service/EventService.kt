package pt.unl.fct.iadi.novaevents2.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents2.controller.DuplicateEventNameException
import pt.unl.fct.iadi.novaevents2.controller.EventForm
import pt.unl.fct.iadi.novaevents2.controller.dto.CreateEventRequest
import pt.unl.fct.iadi.novaevents2.controller.dto.EditEventRequest
import pt.unl.fct.iadi.novaevents2.model.Event
import pt.unl.fct.iadi.novaevents2.model.EventType
import java.time.LocalDate

@Service
class EventService(private val clubService: ClubService) {

    private var actualId: Long = 25

    private val events: MutableMap<Long, Event> = mutableMapOf(
        1L to Event(1, 1, "evento 1-1", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        2L to Event(2, 1, "Spring Chess Tournament", LocalDate.now(), "Lisboa", EventType.TALK, "yapping"),
        3L to Event(3, 1, "evento 1-3", LocalDate.now(), "Lisboa", EventType.MEETING, "yapping"),
        4L to Event(4, 1, "evento 1-4", LocalDate.now(), "Lisboa", EventType.COMPETITION, "yapping"),
        5L to Event(5, 1, "Beginner's Chess Workshop", LocalDate.now(), "Lisboa", EventType.WORKSHOP, "yapping"),

        6L to Event(6, 2, "evento 2-1", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        7L to Event(7, 2, "evento 2-2", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        8L to Event(8, 2, "evento 2-3", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        9L to Event(9, 2, "evento 2-4", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        10L to Event(10, 2, "evento 2-5", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),

        11L to Event(11, 3, "evento 3-1", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        12L to Event(12, 3, "evento 3-2", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        13L to Event(13, 3, "evento 3-3", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        14L to Event(14, 3, "evento 3-4", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        15L to Event(15, 3, "evento 3-5", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),

        16L to Event(16, 4, "evento 4-1", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        17L to Event(17, 4, "evento 4-2", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        18L to Event(18, 4, "evento 4-3", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        19L to Event(19, 4, "evento 4-4", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        20L to Event(20, 4, "evento 4-5", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),

        21L to Event(21, 5, "evento 5-1", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        22L to Event(22, 5, "evento 5-2", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        23L to Event(23, 5, "evento 5-3", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        24L to Event(24, 5, "evento 5-4", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
        25L to Event(25, 5, "evento 5-5", LocalDate.now(), "Lisboa", EventType.SOCIAL, "yapping"),
    )

    fun newId(): Long{
        actualId += 1
        return actualId
    }

    fun filter(type: EventType?, clubId: Long?, from: LocalDate?,
               to: LocalDate?): List<Event>
    {
        if(clubId != null) clubService.findClubById(clubId)

        val events = events.values.filter{ event ->
            (type == null || event.type == type) &&
            (clubId == null || clubId == event.clubId) &&
            (from == null || !event.date.isBefore(from)) &&
            (to == null || event.date.isAfter(to))
        }
        return events
    }

    fun createEvent(request: EventForm, clubId: Long): Event {

        val id = newId()
        clubService.findClubById(clubId)

        println("type -> ${request.type}")

        val event = Event(id, clubId, request.name, request.date!!,
            request.location, request.type!!, request.description)

        events[event.id] = event
        return event
    }

    fun findById(eventId: Long): Event{
        val event = events[eventId] ?: throw NoSuchElementException()
        return event
    }

    fun editEvent(ogId: Long, request: EditEventRequest, clubId: Long): Event {

        clubService.findClubById(clubId)

        if(events[ogId] == null) throw NoSuchElementException()
        val ogEvent = events[ogId] ?: throw NoSuchElementException()

        if (request.name != ogEvent.name) ogEvent.name = request.name
        if (request.date != ogEvent.date) ogEvent.date = request.date!!
        if (request.location != ogEvent.location) ogEvent.location = request.location
        if (request.type != ogEvent.type) ogEvent.type = request.type!!
        if (request.description != ogEvent.description) ogEvent.description = request.description

        events[ogId] = ogEvent

        return ogEvent
    }

    fun deleteEvent(id: Long){
        if(events[id] == null) throw NoSuchElementException()
        else events.remove(id)
    }

    fun getEventsFromClub(clubId: Long): List<Event>{

        val evs = mutableListOf<Event>()

        clubService.findClubById(clubId)

        for(pair in events){
            val event = pair.value
            if(event.clubId == clubId)
                evs.add(event)
        }
        return evs
    }

    fun checkDuplicateName(name: String): Boolean{
        return events.values.any { it.name == name }
    }
}