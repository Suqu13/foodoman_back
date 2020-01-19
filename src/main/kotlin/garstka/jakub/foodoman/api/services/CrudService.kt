package garstka.jakub.foodoman.api.services

/**
 * Interfejst wspierający operacje 'crud'
 *
 * @param T - parametr deklarujący typ obiektów zarządanych w ramach 'crud_service'
 * @param ID - parametr deklarujący typ 'id' obiektów zarządanych w ramach 'crud_service'
 */
interface CrudService<T, ID> {
    fun findAll(): Set<T>
    fun findById(id: ID): T
    fun save(t: T): T
    fun deleteById(id: ID)
}