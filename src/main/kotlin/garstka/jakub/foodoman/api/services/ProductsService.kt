package garstka.jakub.foodoman.api.services

import garstka.jakub.foodoman.api.v1.model.Product

interface ProductsService : CrudService<Product, Long> {
    fun editPartially(product: Product)
}

