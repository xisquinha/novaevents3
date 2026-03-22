package pt.unl.fct.iadi.novaevents2.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.unl.fct.iadi.novaevents2.model.Club

interface ClubRepository: JpaRepository<Club, Long> {
}