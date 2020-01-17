package garstka.jakub.foodoman.api.v1.model

import javax.persistence.*

@Entity
@Table(name = "priorities_levels", uniqueConstraints = [UniqueConstraint(columnNames = ["priority_id", "product_set_id"])])
data class PriorityLevel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column
        var level: Int?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "priority_id")
        var priority: Priority?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_set_id")
        var productSet: ProductSet?
) {
    constructor() : this(null, null, null, null)

    override fun toString(): String {
        return "PriorityLevel(id=$id, level=$level)"
    }
}