package garstka.jakub.foodoman.api.services.impl

import garstka.jakub.foodoman.api.custom_exceptions.ProductNotFoundException
import garstka.jakub.foodoman.api.repositories.ProductsRepository
import garstka.jakub.foodoman.api.services.ProductsService
import garstka.jakub.foodoman.api.v1.model.Product
import org.springframework.stereotype.Service

@Service
class ProductsServiceImpl(private val productsRepository: ProductsRepository) : ProductsService {
    override fun findAll() = productsRepository.findAll().toSet()

    override fun findById(id: Long): Product = productsRepository.findById(id).orElseThrow { ProductNotFoundException(id) }

    override fun save(t: Product): Product = productsRepository.save(t);

    override fun deleteById(id: Long) = productsRepository.deleteById(id);
}