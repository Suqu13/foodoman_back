package garstka.jakub.foodoman.api.services

import garstka.jakub.foodoman.api.v1.model.Product

/**
 * Interfejst wspierający operacje 'crud' dla obiektów typu 'product'
 *
 */
interface ProductsService : CrudService<Product, Long> {
    /**
     * Funckja umożliwia aktualizacje pół 'product' poprzez obiekt niosący informacje o polach do aktualizacji
     *
     * @param product - obiekt niosący informacje o polach do aktualizacji
     * @return
     */
    fun editPartially(product: Product): Product
}

