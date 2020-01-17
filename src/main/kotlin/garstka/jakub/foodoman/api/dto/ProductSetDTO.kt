package garstka.jakub.foodoman.api.dto

import java.util.*

class ProductSetDTO(var id: Long?,
                    var pieces: Int?,
                    var expiryDate: Date?,
                    var description: String?,
                    var rack: RackDTO?
) {
    constructor() : this(null, null, null, null, null)
}