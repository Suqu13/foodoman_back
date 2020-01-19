package garstka.jakub.foodoman.api.repositories

import garstka.jakub.foodoman.api.v1.model.Product
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Interfejst wspierający operacje 'crud' z wykorzystaniem 'jpa_repository' dla 'product'
 *
 */
interface ProductsRepository: JpaRepository<Product, Long>