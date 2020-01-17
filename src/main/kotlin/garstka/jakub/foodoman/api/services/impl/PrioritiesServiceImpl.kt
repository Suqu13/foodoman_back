package garstka.jakub.foodoman.api.services.impl

import garstka.jakub.foodoman.api.custom_exceptions.PriorityNotFoundException
import garstka.jakub.foodoman.api.repositories.PrioritiesRepository
import garstka.jakub.foodoman.api.services.PrioritiesService
import garstka.jakub.foodoman.api.v1.model.Priority
import org.springframework.stereotype.Service

@Service
class PrioritiesServiceImpl(private val prioritiesRepository: PrioritiesRepository) : PrioritiesService {
    override fun findAll(): Set<Priority> = prioritiesRepository.findAll().toSet()

    override fun findAllByProductId(id: Long) = prioritiesRepository.findAllByProductId(id).toSet()

    override fun findById(id: Long): Priority = prioritiesRepository.findById(id).orElseThrow { PriorityNotFoundException(id) }

    override fun save(t: Priority): Priority = prioritiesRepository.save(t)

    override fun deleteById(id: Long) = prioritiesRepository.deleteById(id)

}