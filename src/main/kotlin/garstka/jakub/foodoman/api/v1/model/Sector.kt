package garstka.jakub.foodoman.api.v1.model

import javax.persistence.*

@Entity
@Table(name = "sectors")
data class Sector(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(nullable = false)
        var number: Int?,

        @Column(nullable = false)
        var description: String?,

        @OneToMany(mappedBy = "sector", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var racks: List<Rack>?
) {
        constructor() : this(null, null, null, null)

        override fun toString(): String {
                return "Sector(id=$id, number=$number, description=$description)"
        }

}