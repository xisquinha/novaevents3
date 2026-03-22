package pt.unl.fct.iadi.novaevents.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents.model.Club
import pt.unl.fct.iadi.novaevents.repository.ClubRepository

@Service
class ClubService(val clubRepository: ClubRepository) {

    fun getClubs(): List<Club> = clubRepository.findAll()

    fun findById(clubId: Long): Club =
        clubRepository.findById(clubId).orElseThrow()

    fun findAll(): List<Club> = clubRepository.findAll()

}