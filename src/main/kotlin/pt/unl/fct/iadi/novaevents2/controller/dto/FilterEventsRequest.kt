package pt.unl.fct.iadi.novaevents2.controller.dto

import java.time.LocalDate

data class FilterEventsRequest (
    val type: EventType?,
    val clubId: Long?,
    val startDate: LocalDate?,
    val endDate: LocalDate?
)