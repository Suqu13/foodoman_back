package garstka.jakub.foodoman.api.repositories

import garstka.jakub.foodoman.api.v1.model.ProductSet
import org.springframework.data.jpa.repository.JpaRepository

interface ProductsSetsRepository: JpaRepository<ProductSet, Long> {
    fun findAllByProduct_Id(id: Long): Set<ProductSet>
}