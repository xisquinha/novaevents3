package pt.unl.fct.iadi.novaevents

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import pt.unl.fct.iadi.novaevents.model.Club
import pt.unl.fct.iadi.novaevents.model.ClubCategory
import pt.unl.fct.iadi.novaevents.model.Event
import pt.unl.fct.iadi.novaevents.model.EventType
import pt.unl.fct.iadi.novaevents.repository.ClubRepository
import pt.unl.fct.iadi.novaevents.repository.EventRepository
import pt.unl.fct.iadi.novaevents.repository.EventTypeRepository
import java.time.LocalDate

@Component
class DataInitializer(
    private val eventRepository: EventRepository,
    private val clubRepository: ClubRepository,
    private val eventTypeRepository: EventTypeRepository
): ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        if(clubRepository.count() > 0L) return

        val eventTypes =
            eventTypeRepository.saveAll(
                listOf(
                    EventType().apply { name = "WORKSHOP" },
                    EventType().apply { name = "TALK" },
                    EventType().apply { name = "COMPETITION" },
                    EventType().apply { name = "SOCIAL" },
                    EventType().apply { name = "MEETING" },
                    EventType().apply { name = "OTHER" }
                )
            )

        val typesMap = mutableMapOf<String, EventType>()
        for (e in eventTypes){
            typesMap[e.name!!] = e
        }

        val clubs: MutableList<Club> =
        clubRepository.saveAll(
            listOf(
                Club().apply {
                    name = "Chess Club"
                    description = "The Chess club is a place where the critical thinking takes place"
                    category = ClubCategory.ACADEMIC
                },
                Club().apply {
                    name = "Robotics Club"
                    description = "The Robotics Club is the place to turn ideas into machines"
                    category = ClubCategory.TECHNOLOGY
                },
                Club().apply {
                    name = "Photography Club"
                    description = "The Photography club is a place where the beauty of the world is stored."
                    category = ClubCategory.ARTS
                },
                Club().apply {
                    name = "Hiking & Outdoors Club"
                    description = "The Hiking & Outdoors Club is a place to connect to the nature"
                    category = ClubCategory.SOCIAL
                },
                Club().apply {
                    name = "Film Society"
                    description = "The film society is a place to lear about the masterpieces"
                    category = ClubCategory.CULTURAL
                }
            )
        )


        eventRepository.saveAll(
            listOf(
                // Chess Club
                Event().apply {
                    club = clubs[0]
                    name = "Blitz Night"
                    date = LocalDate.now().plusDays(2)
                    location = "Lisboa"
                    type = typesMap["COMPETITION"]
                    description = "Fast-paced blitz matches for all skill levels."
                },
                Event().apply {
                    club = clubs[0]
                    name = "Spring Chess Tournament"
                    date = LocalDate.now().plusDays(10)
                    location = "Porto"
                    type = typesMap["COMPETITION"]
                    description = "Annual tournament with multiple brackets and prizes."
                },
                Event().apply {
                    club = clubs[0]
                    name = "Strategy Deep Dive"
                    date = LocalDate.now().plusDays(5)
                    location = "Coimbra"
                    type = typesMap["TALK"]
                    description = "Discussion on advanced chess strategies and famous games."
                },
                Event().apply {
                    club = clubs[0]
                    name = "Casual Play Evening"
                    date = LocalDate.now().plusDays(1)
                    location = "Lisboa"
                    type = typesMap["SOCIAL"]
                    description = "Relaxed games and socializing with other members."
                },
                Event().apply {
                    club = clubs[0]
                    name = "Beginner's Chess Workshop"
                    date = LocalDate.now().plusDays(7)
                    location = "Lisboa"
                    type = typesMap["WORKSHOP"]
                    description = "Learn the basics and improve your opening moves."
                },

                // Robotics Club
                Event().apply {
                    club = clubs[1]
                    name = "Intro to Arduino"
                    date = LocalDate.now().plusDays(3)
                    location = "Lisboa"
                    type = typesMap["WORKSHOP"]
                    description = "Hands-on introduction to Arduino and simple circuits."
                },
                Event().apply {
                    club = clubs[1]
                    name = "Robot Battle Night"
                    date = LocalDate.now().plusDays(12)
                    location = "Porto"
                    type = typesMap["COMPETITION"]
                    description = "Bring your robot and compete in fun challenges."
                },
                Event().apply {
                    club = clubs[1]
                    name = "AI in Robotics Talk"
                    date = LocalDate.now().plusDays(6)
                    location = "Online"
                    type = typesMap["TALK"]
                    description = "Exploring how AI is shaping modern robotics."
                },
                Event().apply {
                    club = clubs[1]
                    name = "Build & Chill"
                    date = LocalDate.now().plusDays(4)
                    location = "Lisboa"
                    type = typesMap["SOCIAL"]
                    description = "Work on your projects in a relaxed environment."
                },
                Event().apply {
                    club = clubs[1]
                    name = "Sensor Integration Session"
                    date = LocalDate.now().plusDays(8)
                    location = "Braga"
                    type = typesMap["MEETING"]
                    description = "Collaborative session on integrating sensors into robots."
                },

                // Photography Club
                Event().apply {
                    club = clubs[2]
                    name = "Golden Hour Walk"
                    date = LocalDate.now().plusDays(2)
                    location = "Lisboa"
                    type = typesMap["SOCIAL"]
                    description = "Capture stunning sunset shots around the city."
                },
                Event().apply {
                    club = clubs[2]
                    name = "Portrait Photography Workshop"
                    date = LocalDate.now().plusDays(9)
                    location = "Porto"
                    type = typesMap["WORKSHOP"]
                    description = "Learn lighting and composition for portraits."
                },
                Event().apply {
                    club = clubs[2]
                    name = "Photo Editing Basics"
                    date = LocalDate.now().plusDays(5)
                    location = "Online"
                    type = typesMap["TALK"]
                    description = "Introduction to editing tools and techniques."
                },
                Event().apply {
                    club = clubs[2]
                    name = "Street Photography Challenge"
                    date = LocalDate.now().plusDays(11)
                    location = "Lisboa"
                    type = typesMap["COMPETITION"]
                    description = "Compete to capture the best street moment."
                },
                Event().apply {
                    club = clubs[2]
                    name = "Gear Talk Meetup"
                    date = LocalDate.now().plusDays(7)
                    location = "Coimbra"
                    type = typesMap["MEETING"]
                    description = "Discuss cameras, lenses, and gear setups."
                },

                // Hiking & Outdoors Club
                Event().apply {
                    club = clubs[3]
                    name = "Sintra Trail Hike"
                    date = LocalDate.now().plusDays(6)
                    location = "Sintra"
                    type = typesMap["SOCIAL"]
                    description = "Explore scenic trails and enjoy nature."
                },
                Event().apply {
                    club = clubs[3]
                    name = "Camping Weekend"
                    date = LocalDate.now().plusDays(14)
                    location = "Gerês"
                    type = typesMap["OTHER"]
                    description = "Two-day camping experience in the national park."
                },
                Event().apply {
                    club = clubs[3]
                    name = "Outdoor Safety Talk"
                    date = LocalDate.now().plusDays(3)
                    location = "Lisboa"
                    type = typesMap["TALK"]
                    description = "Learn essential survival and safety tips."
                },
                Event().apply {
                    club = clubs[3]
                    name = "Trail Cleanup Day"
                    date = LocalDate.now().plusDays(8)
                    location = "Cascais"
                    type = typesMap["MEETING"]
                    description = "Help keep nature clean while meeting others."
                },
                Event().apply {
                    club = clubs[3]
                    name = "Navigation Workshop"
                    date = LocalDate.now().plusDays(10)
                    location = "Évora"
                    type = typesMap["WORKSHOP"]
                    description = "Learn how to navigate using maps and compasses."
                },

                // Film Society
                Event().apply {
                    club = clubs[4]
                    name = "Classic Movie Night"
                    date = LocalDate.now().plusDays(2)
                    location = "Lisboa"
                    type = typesMap["SOCIAL"]
                    description = "Screening of a timeless cinema classic."
                },
                Event().apply {
                    club = clubs[4]
                    name = "Director Spotlight: Nolan"
                    date = LocalDate.now().plusDays(7)
                    location = "Porto"
                    type = typesMap["TALK"]
                    description = "Discussion about Christopher Nolan's films."
                },
                Event().apply {
                    club = clubs[4]
                    name = "Short Film Competition"
                    date = LocalDate.now().plusDays(13)
                    location = "Lisboa"
                    type = typesMap["COMPETITION"]
                    description = "Submit and showcase your original short films."
                },
                Event().apply {
                    club = clubs[4]
                    name = "Screenwriting Workshop"
                    date = LocalDate.now().plusDays(5)
                    location = "Coimbra"
                    type = typesMap["WORKSHOP"]
                    description = "Learn the basics of writing compelling scripts."
                },
                Event().apply {
                    club = clubs[4]
                    name = "Open Discussion Night"
                    date = LocalDate.now().plusDays(9)
                    location = "Online"
                    type = typesMap["MEETING"]
                    description = "Share thoughts on recent films and trends."
                }
            )
        )
    }
}