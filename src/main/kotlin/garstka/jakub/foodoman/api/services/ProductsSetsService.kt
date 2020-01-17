package garstka.jakub.foodoman.api.services

import garstka.jakub.foodoman.api.v1.model.ProductSet

interface ProductsSetsService : CrudService<ProductSet, Long> {
    fun findAllByProductId(id: Long): Set<ProductSet>
    fun findAllByProductIdAndPriorityId(productId: Long, priorityId: Long): Set<ProductSet>


}