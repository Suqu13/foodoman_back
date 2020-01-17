package garstka.jakub.foodoman.api.dto

class ProducerDTO(
        var id: Long?,
        var name: String?,
        var description: String?
) {
    constructor() : this(null, null, null)

}