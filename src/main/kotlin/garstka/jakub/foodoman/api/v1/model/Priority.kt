package garstka.jakub.foodoman.api.v1.model

import javax.persistence.*

@Entity
@Table(name = "priorities")
data class Priority(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(nullable = false)
        var name: String?,

        @Column(nullable = false)
        var description: String?,

        @OneToMany(mappedBy = "priority", cascade = [CascadeType.ALL])
        var prioritiesLevels: List<PriorityLevel>?
) {
    constructor() : this(null, null, null, null)

    override fun toString(): String {
        return "Priority(id=$id, name=$name, description=$description)"
    }
}