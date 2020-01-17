package garstka.jakub.foodoman.api.services.impl

import garstka.jakub.foodoman.api.custom_exceptions.ProductSetNotFoundException
import garstka.jakub.foodoman.api.repositories.ProductsSetsRepository
import garstka.jakub.foodoman.api.services.ProductsSetsService
import garstka.jakub.foodoman.api.v1.model.ProductSet
import org.springframework.stereotype.Service

@Service
class ProductsSetsServiceImpl(private val productsSetsRepository: ProductsSetsRepository) : ProductsSetsService {
    override fun findAllByProduct_Id(id: Long): Set<ProductSet> = productsSetsRepository.findAllByProduct_Id(id);

    override fun findAll(): Set<ProductSet> = productsSetsRepository.findAll().toSet()

    override fun findById(id: Long): ProductSet = productsSetsRepository.findById(id).orElseThrow { ProductSetNotFoundException(id) }

    override fun save(t: ProductSet): ProductSet = productsSetsRepository.save(t);

    override fun deleteById(id: Long) = productsSetsRepository.deleteById(id)
}