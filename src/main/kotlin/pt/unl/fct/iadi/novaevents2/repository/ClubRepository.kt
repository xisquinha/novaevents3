package pt.unl.fct.iadi.novaevents2.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.unl.fct.iadi.novaevents2.model.Club

@Repository
interface ClubRepository: JpaRepository<Club, Long>