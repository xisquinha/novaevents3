package pt.unl.fct.iadi.novaevents.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import pt.unl.fct.iadi.novaevents.model.Club
import pt.unl.fct.iadi.novaevents.service.ClubService
import pt.unl.fct.iadi.novaevents.service.EventService

@Controller
@RequestMapping("/clubs")
class ClubController(private val clubService: ClubService, private val eventService: EventService) {

    @GetMapping
    fun listClubs(model: ModelMap): String {
        val clubs = clubService.findAll()

        val eventCounts = eventService.countGroupedByClub()

        model["clubs"] = clubs
        model["eventCounts"] = eventCounts
        return "clubs/list"
    }

    @GetMapping("/{id}")
    fun viewClubDetails(@PathVariable id: Long, model: ModelMap): String {
        model["club"] = clubService.findById(id)
        return "clubs/detail"
    }
}