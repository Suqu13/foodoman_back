package garstka.jakub.foodoman.api.services

import garstka.jakub.foodoman.api.v1.model.Priority

interface PrioritiesService: CrudService<Priority, Long> {
    fun findAllByProductId(id: Long): Set<Priority>

}