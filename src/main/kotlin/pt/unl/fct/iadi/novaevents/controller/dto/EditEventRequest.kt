package pt.unl.fct.iadi.novaevents.controller.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class EditEventRequest (

    @field:NotBlank
    val name: String = "",
    @field:NotNull
    val date: LocalDate? = null,
    @field:NotNull
    val type: EventType? = null,

    val location: String ="",
    val description: String =""
)