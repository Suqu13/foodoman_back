package garstka.jakub.foodoman.api.repositories

import garstka.jakub.foodoman.api.v1.model.ProductSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Interfejst wspierający operacje 'crud' z wykorzystaniem 'jpa_repository' dla 'product_set'
 *
 */
interface ProductsSetsRepository : JpaRepository<ProductSet, Long> {

    /**
     * Funkcja zwracająca 'product_set' powiązane z 'product' identyfikujący przez 'id' znajdujący się w 'db'
     *
     * @param id - parametr będący numerem identyfikujacym 'product' w 'db'
     * @return
     */
    fun findAllByProduct_Id(id: Long): Set<ProductSet>

    /**
     * Funkcja zwracająca zbiór 'product_set' powiązany z 'product' identyfikujący przez 'productId' i z 'priority' identyfikujący przez 'priorityId' znajdujący się w 'db'
     * Wykorzystuje zapytanie 'JPQL'
     *
     * @param productId - parametr będący numerem identyfikujacym 'product' w 'db'
     * @param priorityId - parametr będący numerem identyfikujacym 'priority' w 'db'
     * @return
     */
    @Query("SELECT PS FROM ProductSet PS JOIN PS.product P JOIN PS.prioritiesLevels PL WHERE P.id = ?1 AND PL.priority.id = ?2 ORDER BY PL.level")
    fun findAllByProduct_IdAndPriorityId(productId: Long, priorityId: Long): Set<ProductSet>
}