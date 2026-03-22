package pt.unl.fct.iadi.novaevents.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.unl.fct.iadi.novaevents.model.Event
import pt.unl.fct.iadi.novaevents.model.EventType
import java.time.LocalDate

@Repository
interface EventRepository: JpaRepository<Event, Long> {

    @Query(
        "SELECT e FROM Event e WHERE " +
        "(:excEventId = -1 OR e.id <> :excEventId) AND" +
        "(e.name = :name)"
    )
    fun findDuplicate(name: String, excEventId: Long = -1): Event?

    @Query(
        "SELECT e FROM Event e JOIN FETCH e.type WHERE" +
        "(:clubId IS NULL OR e.club.id = :clubId) AND " +
        "(:eventType IS NULL OR e.type = :eventType) AND " +
        "(:dateStart IS NULL OR e.date >= :dateStart) AND " +
        "(:dateEnd IS NULL OR e.date <= :dateEnd)"
    )
    fun findByClubId_Type_DateRange(
        clubId: Long?=-1, eventType: EventType?=null, dateStart: LocalDate?=null, dateEnd: LocalDate?=null
    ): List<Event>


    //@Query("SELECT COUNT(e) FROM Event e WHERE e.club.id = :clubId")
    //fun findNumEventsFromClub(clubId: Long): Int

    @Query("SELECT e.club.id, COUNT(e) FROM Event e GROUP BY e.club.id")
    fun countEventsGroupedByClub(): List<Array<Any>>

}