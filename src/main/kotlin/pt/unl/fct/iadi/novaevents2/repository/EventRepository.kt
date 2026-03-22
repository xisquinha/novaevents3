package pt.unl.fct.iadi.novaevents2.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.iadi.novaevents2.model.Event
import pt.unl.fct.iadi.novaevents2.model.EventType

interface EventRepository: JpaRepository<Event, Long> {
    fun findDuplicate(name: String, excEventId: Long = -1): Event?
    fun findFilterEvents(clubId: Long = -1, eventType: EventType? = null): List<Event>
}