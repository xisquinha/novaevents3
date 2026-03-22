package pt.unl.fct.iadi.novaevents2.model

import jakarta.persistence.*

@Entity
class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String? = null

    @Column(length = 2000)
    var description: String? = null
    @Enumerated(EnumType.STRING)
    var category: ClubCategory? = null
}