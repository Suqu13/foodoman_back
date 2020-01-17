package garstka.jakub.foodoman.api.dto

class RackDTO(
        var id: Long?,
        var number: Int?,
        var description: String?,
        var sector: SectorDTO?
) {
    constructor() : this(null, null, null, null)
}
