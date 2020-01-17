package garstka.jakub.foodoman.api.services

import garstka.jakub.foodoman.api.v1.model.ProductSet

interface ProductsSetsService : CrudService<ProductSet, Long> {
    fun findAllByProduct_Id(id: Long): Set<ProductSet>
}