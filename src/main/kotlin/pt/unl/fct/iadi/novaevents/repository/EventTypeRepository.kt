package pt.unl.fct.iadi.novaevents.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pt.unl.fct.iadi.novaevents.model.EventType

@Repository
interface EventTypeRepository: JpaRepository<EventType, Long>{
    @Query(
        "SELECT t FROM EventType t WHERE t.name = :name"
    )
    fun findByName(name: String): EventType?
}