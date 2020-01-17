package garstka.jakub.foodoman.api.v1.model

import javax.persistence.*

@Entity
@Table(name = "racks")
data class Rack(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(nullable = false)
        var number: Int?,

        @Column(nullable = false)
        var description: String?,

        @OneToMany(mappedBy = "rack", cascade = [CascadeType.ALL])
        var productsSets: List<ProductSet>?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "sector_id")
        var sector: Sector?
) {
    constructor() : this(null, null, null, null, null)

        override fun toString(): String {
                return "Rack(id=$id, number=$number, description=$description)"
        }
}