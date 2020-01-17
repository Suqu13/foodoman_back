package garstka.jakub.foodoman.api.dto

data class SectorDTO(
        var id: Long?,
        var number: Int?,
        var description: String?
) {
    constructor() : this(null, null, null)
}
