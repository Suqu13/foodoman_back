package garstka.jakub.foodoman.api.v1.model

import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(nullable = false)
        var name: String?,

        @Column(nullable = false)
        var weight: Double?,

        @Column(nullable = false)
        var height: Double?,

        @Column(nullable = false)
        var allPiecesNumber: Int?,

        @Column(nullable = false)
        var surface: Double?,

        @Column
        var imageUrl: String?,

        @Column
        var description: String?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "producer_id")
        var producer: Producer?,

        @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
        var productSets: List<ProductSet>?
) {
    constructor() : this(null, null, null, null, null, null, null, null, null, null)

    override fun toString(): String {
        return "Product(id=$id, name=$name, weight=$weight, height=$height, allPiecesNumber=$allPiecesNumber, surface=$surface, imageUrl=$imageUrl, description=$description)"
    }
}

