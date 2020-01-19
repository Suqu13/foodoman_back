package garstka.jakub.foodoman.api.custom_exceptions

import garstka.jakub.foodoman.api.priorityNotFoundByIdMessage
import garstka.jakub.foodoman.api.producerNotFoundByIdMessage
import garstka.jakub.foodoman.api.productNotFoundByIdMessage
import garstka.jakub.foodoman.api.productSetNotFoundByIdMessage

/**
 * Klasa abstrakcyjnego wyjątku powiązanego z brakiem wystąpienia 'resource' w 'db'
 *
 */
abstract class ResourceNotFoundException(message: String) : Exception(message)

/**
 * Klasa wyjątku dziedziczącego z 'ResourceNotFoundException', powiązanego z brakiem wystąpienia 'product' w 'db'
 *
 */
class ProductNotFoundException(id: Long) : ResourceNotFoundException(productNotFoundByIdMessage(id))

/**
 * Klasa wyjątku dziedziczącego z 'ResourceNotFoundException', powiązanego z brakiem wystąpienia 'product_set' w 'db'
 *
 */
class ProductSetNotFoundException(id: Long) : ResourceNotFoundException(productSetNotFoundByIdMessage(id))

/**
 * Klasa wyjątku dziedziczącego z 'ResourceNotFoundException', powiązanego z brakiem wystąpienia 'producer' w 'db'
 *
 */
class ProducerNotFoundException(id: Long) : ResourceNotFoundException(producerNotFoundByIdMessage(id))

/**
 * Klasa wyjątku dziedziczącego z 'ResourceNotFoundException', powiązanego z brakiem wystąpienia 'priority' w 'db'
 *
 */
class PriorityNotFoundException(id: Long) : ResourceNotFoundException(priorityNotFoundByIdMessage(id))