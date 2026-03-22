package pt.unl.fct.iadi.novaevents.controller

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import pt.unl.fct.iadi.novaevents.controller.dto.EditEventRequest
import pt.unl.fct.iadi.novaevents.model.EventType
import pt.unl.fct.iadi.novaevents.service.ClubService
import pt.unl.fct.iadi.novaevents.service.EventService
import pt.unl.fct.iadi.novaevents.service.EventTypeService
import java.time.LocalDate

@Controller
class EventController(
    private val event_service: EventService,
    private val club_service: ClubService,
    private val eventTypeService: EventTypeService,
) {

    @GetMapping("/events")
    fun list(@RequestParam(required = false) type: EventType?,
             @RequestParam(required = false) clubId: Long?,
             @RequestParam(required = false) from: LocalDate?,
             @RequestParam(required = false) to: LocalDate?,
             model: ModelMap): String
    {
        val events = event_service.filter(type, clubId, from, to)
        model["events"] = events
        return "events/list"
    }

    @GetMapping("/clubs/{clubId}/events")
    fun clubEvents(@PathVariable clubId: Long, model: ModelMap): String {
        val events = event_service.getEventsFromClub(clubId)
        model["events"] = events
        model["club"] = club_service.findById(clubId)
        return "clubs/events"
    }

    @GetMapping("/clubs/{clubId}/events/{id}")
    fun detail(@PathVariable clubId: Long, @PathVariable id: Long, model: ModelMap): String {
        val event = event_service.findById(id)
        model["event"] = event
        model["clubId"] = clubId
        model["club"] = club_service.findById(clubId)
        return "events/detail"
    }

    @GetMapping("/clubs/{clubId}/events/new")
    fun createForm(@PathVariable clubId: Long, model: ModelMap): String {
        model["clubId"] = clubId
        model["eventTypes"] = eventTypeService.findAll().map { it.name }
        model["eventForm"] = EventForm()
        return "events/create"
    }

    @PostMapping("/clubs/{clubId}/events")
    fun create(@Valid @ModelAttribute("eventForm") eventForm: EventForm,
               bindingResult: BindingResult,
               @PathVariable clubId: Long,
               model: ModelMap): String {

        if(bindingResult.hasErrors()) {
            model["clubId"] = clubId
            return "events/create"
        }

        val dupName = event_service.checkDuplicateName(eventForm.name)

        model["clubId"] = clubId

        if(dupName){
            bindingResult.rejectValue("name", "duplicate",
                "An event with this name already exists")
            return "events/create"
        }

        val event = event_service.createEvent(eventForm, clubId)
        model["event"] = event
        return "redirect:/clubs/$clubId/events/${event.id}"
    }


    @GetMapping("/clubs/{clubId}/events/{id}/edit")
    fun editForm(@PathVariable clubId: Long, @PathVariable id: Long, model: ModelMap): String {
        model["event"] = event_service.findById(id)

        val event = event_service.findById(id)

        model["editEventRequest"] = EditEventRequest(
            name = event.name?:"",
            date = event.date?:LocalDate.now(),
            type = event.type,
            location = event.location?:"",
            description = event.description?:""
        )
        model["clubId"] = clubId
        model["eventId"] = id

        return "events/edit"
    }

    @PutMapping("/clubs/{clubId}/events/{id}")
    fun edit(@Valid  @ModelAttribute request: EditEventRequest, bindingResult: BindingResult, @PathVariable clubId: Long,
             @PathVariable id: Long, model: ModelMap): String{

        if(bindingResult.hasErrors()) {
            model["event"] = event_service.findById(id)
            model["request"] = request
            model["clubId"] = clubId
            return "events/edit"
        }

        val dupName = event_service.checkDuplicateName(request.name)
        if (dupName) {
            bindingResult.rejectValue("name", "duplicate", "An event with this name already exists")
            model["event"] = event_service.findById(id)
            model["clubId"] = clubId
            return "events/edit"  // ← view name, not redirect
        }

        val event = event_service.editEvent(id, request, clubId)

        model["event"] = event
        model["clubId"] = clubId
        return "redirect:/clubs/$clubId/events/${event.id}"
    }

    @GetMapping("/clubs/{clubId}/events/{id}/delete")
    fun deleteForm(@PathVariable clubId: Long, @PathVariable id: Long, model: ModelMap): String {
        model["event"] = event_service.findById(id)
        return "events/delete"
    }

    @DeleteMapping("/clubs/{clubId}/events/{id}")
    fun delete(@PathVariable clubId: Long, @PathVariable id: Long, model: ModelMap): String {
        event_service.deleteEvent(id)
        return "redirect:/clubs/${clubId}"
    }
}