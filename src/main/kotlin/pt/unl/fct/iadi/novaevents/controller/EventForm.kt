package pt.unl.fct.iadi.novaevents.controller

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import pt.unl.fct.iadi.novaevents.model.EventType
import java.time.LocalDate

data class EventForm(
    @field:NotBlank(message = "Name is required")
    val name: String = "",

    @field:NotNull(message = "Date is required")
    val date: LocalDate? = null,
    @field:NotNull(message = "Event type is required")
    val type: String? = null,

    val location: String = "",
    val description: String = ""
)