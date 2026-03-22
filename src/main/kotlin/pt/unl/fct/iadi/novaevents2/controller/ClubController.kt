package pt.unl.fct.iadi.novaevents2.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import pt.unl.fct.iadi.novaevents2.service.ClubService

@Controller
@RequestMapping("/clubs")
class ClubController(private val service: ClubService) {

    @GetMapping
    fun listClubs(model: ModelMap): String {
        model["clubs"] = service.getClubs()
        return "clubs/list"
    }

    @GetMapping("/{id}")
    fun viewClubDetails(@PathVariable id: Long, model: ModelMap): String {
        model["club"] = service.findClubById(id)
        return "clubs/detail"
    }
}