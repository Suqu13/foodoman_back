package garstka.jakub.foodoman.api.repositories

import garstka.jakub.foodoman.api.v1.model.Priority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Interfejst wspierający operacje 'crud' z wykorzystaniem 'jpa_repository' dla 'priority'
 *
 */
interface PrioritiesRepository: JpaRepository<Priority, Long> {
    /**
     * Funkcja zwracająca 'priority' powiązane z 'product' identyfikujący przez 'id' znajdujący się w 'db'
     * Wykorzystuje zapytanie 'JPQL'
     *
     * @param id - parametr będący numerem identyfikujacym 'product' w 'db''
     * @return
     */
    @Query("SELECT P FROM Priority P JOIN P.prioritiesLevels PL JOIN PL.productSet PS WHERE PS.product.id = ?1")
    fun findAllByProductId(id: Long): List<Priority>
}