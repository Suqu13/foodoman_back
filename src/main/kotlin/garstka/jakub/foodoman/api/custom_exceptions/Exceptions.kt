package garstka.jakub.foodoman.api.custom_exceptions

import garstka.jakub.foodoman.api.productNotFoundByIdMessage
import garstka.jakub.foodoman.api.productSetNotFoundByIdMessage

class ProductNotFoundException(id: Long): Exception(productNotFoundByIdMessage(id))
class ProductSetNotFoundException(id: Long): Exception(productSetNotFoundByIdMessage(id))
class ProducerNotFoundException(id: Long): Exception(productSetNotFoundByIdMessage(id))