package pt.unl.fct.iadi.novaevents.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name="club_id")
    var club: Club? = null

    var name: String? = null
    var date: LocalDate? = null
    var location: String? = null
    @ManyToOne
    @JoinColumn(name = "event_type")
    var type: EventType? = null
    var description: String? = null
}