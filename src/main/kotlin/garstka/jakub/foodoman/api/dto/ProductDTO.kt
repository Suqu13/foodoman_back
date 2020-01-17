package garstka.jakub.foodoman.api.dto

data class ProductDTO(
        var id: Long?,
        var name: String?,
        var weight: Double?,
        var height: Double?,
        var allPiecesNumber: Int?,
        var surface: Double?,
        var imageUrl: String?,
        var description: String?,
        var producer: ProducerDTO?
) {
    constructor() : this(null, null, null, null, null, null, null, null, null)

}