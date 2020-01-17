package garstka.jakub.foodoman.api.repositories

import garstka.jakub.foodoman.api.v1.model.ProductSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductsSetsRepository : JpaRepository<ProductSet, Long> {
    fun findAllByProduct_Id(id: Long): Set<ProductSet>

    @Query("SELECT PS FROM ProductSet PS JOIN PS.product P JOIN PS.prioritiesLevels PL WHERE P.id = ?1 AND PL.priority.id = ?2 ORDER BY PL.level")
    fun findAllByProduct_IdAndPriorityId(productId: Long, priorityId: Long): Set<ProductSet>
}