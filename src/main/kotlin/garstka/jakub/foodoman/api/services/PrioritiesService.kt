package garstka.jakub.foodoman.api.services

import garstka.jakub.foodoman.api.v1.model.Priority

/**
 * Interfejst wspierający operacje 'crud' dla obiektów typu 'priority'
 *
 */
interface PrioritiesService: CrudService<Priority, Long> {
    /**
     * Funkcja zwracająca 'priority' powiązane z 'product' identyfikujący przez 'id' znajdujący się w 'db'
     *
     * @param id - parametr będący numerem identyfikujacym 'product' w 'db'
     * @return
     */
    fun findAllByProductId(id: Long): Set<Priority>

}