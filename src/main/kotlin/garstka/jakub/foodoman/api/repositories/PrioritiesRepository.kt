package garstka.jakub.foodoman.api.repositories

import garstka.jakub.foodoman.api.v1.model.Priority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PrioritiesRepository: JpaRepository<Priority, Long> {
    @Query("SELECT P FROM Priority P JOIN P.prioritiesLevels PL JOIN PL.productSet PS WHERE PS.product.id = ?1")
    fun findAllByProductId(id: Long): List<Priority>
}