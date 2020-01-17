package garstka.jakub.foodoman.api.services

interface CrudService<T, ID> {
    fun findAll(): Set<T>
    fun findById(id: ID): T
    fun save(t: T): T
    fun deleteById(id: ID)
}