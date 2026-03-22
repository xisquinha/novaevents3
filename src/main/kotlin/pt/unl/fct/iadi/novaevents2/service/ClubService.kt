package pt.unl.fct.iadi.novaevents2.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents2.model.Club
import pt.unl.fct.iadi.novaevents2.model.ClubCategory
import pt.unl.fct.iadi.novaevents2.repository.ClubRepository

@Service
class ClubService(val clubRepository: ClubRepository) {

    fun getClubs(): List<Club> = clubRepository.findAll()

    fun findClubById(clubId: Long): Club =
        clubRepository.findById(clubId).orElseThrow()

}