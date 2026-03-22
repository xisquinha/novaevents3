package pt.unl.fct.iadi.novaevents.controller.dto

import pt.unl.fct.iadi.novaevents.model.EventType
import java.time.LocalDate

data class FilterEventsRequest (
    val type: EventType?,
    val clubId: Long?,
    val startDate: LocalDate?,
    val endDate: LocalDate?
)