package garstka.jakub.foodoman.api.v1.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "producers")
data class Producer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(nullable = false)
        var name: String?,

        @Lob
        @Column(nullable = false, columnDefinition="TEXT")
        var description: String?,

        @OneToMany(mappedBy = "producer", cascade = [CascadeType.ALL])
        var products: List<Product>?
) {
    constructor() : this(null, null, null, null)

        override fun toString(): String {
                return "Producer(id=$id, name=$name, description=$description)"
        }


}