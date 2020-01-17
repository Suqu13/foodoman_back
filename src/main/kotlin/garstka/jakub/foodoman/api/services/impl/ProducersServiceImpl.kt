package garstka.jakub.foodoman.api.services.impl

import garstka.jakub.foodoman.api.custom_exceptions.ProducerNotFoundException
import garstka.jakub.foodoman.api.repositories.ProducersRepository
import garstka.jakub.foodoman.api.services.ProducersService
import garstka.jakub.foodoman.api.v1.model.Producer
import org.springframework.stereotype.Service

@Service
class ProducersServiceImpl(private val producersRepository: ProducersRepository) : ProducersService {
    override fun findAll(): Set<Producer> = producersRepository.findAll().toSet()

    override fun findById(id: Long): Producer = producersRepository.findById(id).orElseThrow { ProducerNotFoundException(id) }

    override fun save(t: Producer): Producer = producersRepository.save(t)

    override fun deleteById(id: Long) = producersRepository.deleteById(id)
}