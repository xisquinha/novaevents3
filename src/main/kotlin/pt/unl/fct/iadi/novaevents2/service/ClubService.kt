package pt.unl.fct.iadi.novaevents2.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.novaevents2.model.Club
import pt.unl.fct.iadi.novaevents2.model.ClubCategory

@Service
class ClubService {

    private val clubs: List<Club> =
        listOf(
            Club(1L, name = "Chess Club", description = "The Chess club " +
                    "is a place where the critical thinking takes place", category = ClubCategory.ACADEMIC),
            Club(2L, name = "Robotics Club", description = "The Robotics Club " +
                    "is the place to turn ideas into machines", category = ClubCategory.TECHNOLOGY),
            Club(3L, name = "Photography Club", description = "The Photography " +
                    "club is a place where the beauty of the world is stored.", category = ClubCategory.ARTS),
            Club(4L, name = "Hiking & Outdoors Club", description = "The Hiking & " +
                    "Outdoors Club is a place to connect to the nature", category = ClubCategory.SOCIAL),
            Club(5L, name = "Film Society", description = "The film society is a place " +
                    "to lear about the masterpieces", category = ClubCategory.CULTURAL)
        )

    fun getClubs(): List<Club> = clubs

    fun findClubById(clubId: Long): Club{
        for (club in clubs) if (club.id == clubId) return club

        throw NoSuchElementException()
    }
}