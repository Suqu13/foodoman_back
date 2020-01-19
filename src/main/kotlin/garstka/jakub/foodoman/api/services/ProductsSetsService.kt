package garstka.jakub.foodoman.api.services

import garstka.jakub.foodoman.api.v1.model.ProductSet

/**
 * Interfejst wspierający operacje 'crud' dla obiektów typu 'product_set'
 *
 */
interface ProductsSetsService : CrudService<ProductSet, Long> {
    /**
     * Funkcja zwracająca 'product_set' powiązane z 'product' identyfikujący przez 'id' znajdujący się w 'db'
     *
     * @param id - parametr będący numerem identyfikujacym 'product' w 'db'
     * @return
     */
    fun findAllByProductId(id: Long): Set<ProductSet>

    /**
     * Funkcja zwracająca zbiór 'product_set' powiązany z 'product' identyfikujący przez 'productId' i z 'priority' identyfikujący przez 'priorityId' znajdujący się w 'db'
     *
     * @param productId - parametr będący numerem identyfikujacym 'product' w 'db'
     * @param priorityId - parametr będący numerem identyfikujacym 'priority' w 'db'
     * @return
     */
    fun findAllByProductIdAndPriorityId(productId: Long, priorityId: Long): Set<ProductSet>
}