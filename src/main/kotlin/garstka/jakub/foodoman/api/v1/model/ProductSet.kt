package garstka.jakub.foodoman.api.v1.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "products_sets")
data class ProductSet(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(nullable = false)
        var pieces: Int?,

        @Column(nullable = false)
        var expiryDate: Date?,

        @Column(nullable = false)
        var description: String?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id")
        var product: Product?,

        @ManyToOne
        @JoinColumn(name = "rack_id")
        var rack: Rack?
) {
    constructor() : this(null, null, null, null, null, null)

        override fun toString(): String {
                return "ProductSet(id=$id, pieces=$pieces, expiryDate=$expiryDate, description=$description)"
        }
}