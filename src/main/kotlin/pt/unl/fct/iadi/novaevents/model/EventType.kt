package pt.unl.fct.iadi.novaevents.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class EventType {
    @Id
    var name: String? = null
}