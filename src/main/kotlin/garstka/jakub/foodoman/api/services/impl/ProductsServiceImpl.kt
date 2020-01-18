package garstka.jakub.foodoman.api.services.impl

import garstka.jakub.foodoman.api.custom_exceptions.ProductNotFoundException
import garstka.jakub.foodoman.api.repositories.ProductsRepository
import garstka.jakub.foodoman.api.services.ProductsService
import garstka.jakub.foodoman.api.v1.model.Product
import org.springframework.stereotype.Service

@Service
class ProductsServiceImpl(private val productsRepository: ProductsRepository) : ProductsService {
    override fun editPartially(product: Product) {
        val foundProduct = findById(product.id ?: -1)

        val editedProduct = Product(
                id = product.id ?: foundProduct.id,
                name = product.name ?: foundProduct.name,
                weight = product.weight ?: foundProduct.weight,
                surface = product.surface ?: foundProduct.surface,
                height = product.height ?: foundProduct.height,
                description = product.description ?: foundProduct.description,
                imageUrl = product.imageUrl ?: foundProduct.imageUrl,
                producer = product.producer ?: foundProduct.producer,
                productSets = product.productSets ?: foundProduct.productSets,
                allPiecesNumber = product.allPiecesNumber ?: foundProduct.allPiecesNumber
        )
        save(editedProduct)
    }

    override fun findAll() = productsRepository.findAll().toSet()

    override fun findById(id: Long): Product = productsRepository.findById(id).orElseThrow { ProductNotFoundException(id) }

    override fun save(t: Product): Product = productsRepository.save(t);

    override fun deleteById(id: Long) = productsRepository.deleteById(id);
}