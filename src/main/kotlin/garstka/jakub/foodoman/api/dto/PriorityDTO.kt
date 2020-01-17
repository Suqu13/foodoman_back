package garstka.jakub.foodoman.api.dto

data class PriorityDTO(
        var id: Long?,
        var name: String?,
        var description: String?
) {
    constructor() : this(null, null, null)
}
