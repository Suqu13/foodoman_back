package garstka.jakub.foodoman.api.custom_exceptions

import garstka.jakub.foodoman.api.priorityNotFoundByIdMessage
import garstka.jakub.foodoman.api.producerNotFoundByIdMessage
import garstka.jakub.foodoman.api.productNotFoundByIdMessage
import garstka.jakub.foodoman.api.productSetNotFoundByIdMessage


abstract class ResourceNotFoundException(message: String): Exception(message)
class ProductNotFoundException(id: Long): ResourceNotFoundException(productNotFoundByIdMessage(id))
class ProductSetNotFoundException(id: Long): ResourceNotFoundException(productSetNotFoundByIdMessage(id))
class ProducerNotFoundException(id: Long): ResourceNotFoundException(producerNotFoundByIdMessage(id))
class PriorityNotFoundException(id: Long): ResourceNotFoundException(priorityNotFoundByIdMessage(id))