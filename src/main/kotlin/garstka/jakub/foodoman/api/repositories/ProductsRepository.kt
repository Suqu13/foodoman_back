package garstka.jakub.foodoman.api.repositories

import garstka.jakub.foodoman.api.v1.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductsRepository: JpaRepository<Product, Long> {

}