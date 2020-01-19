package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.controllers.ProductsSetsController.Companion.PRODUCTS_SETS_CONTROLLER_BASE_URL
import garstka.jakub.foodoman.api.mappers.ProductSetConverter
import garstka.jakub.foodoman.api.services.ProductsSetsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Klasa kontrolera obsługujące 'request' wysyłane na adres '.../products_sets'
 *
 */
@RestController
@RequestMapping(PRODUCTS_SETS_CONTROLLER_BASE_URL)
class ProductsSetsController(private val productsSetsService: ProductsSetsService, private val converter: ProductSetConverter) {
    companion object {
        const val PRODUCTS_SETS_CONTROLLER_BASE_URL = "products_sets"
    }

    /**
     * Funkcja przyjmująca 'request' wysylany na bazowy adres rozszerzony o '/{id}', gdzie 'id' to parametr, z wykorzystaniem 'HttpMethod-GET'.
     * Jako 'response' zwraca 'product_set' powiązany z 'id' znajdujący się w 'db'
     *
     * @param id - parametr będący numerem identyfikujacym 'product_set' w 'db'
     */
    @GetMapping("/{id}", produces = ["application/json; charset=utf-8"])
    fun fetchProductSetById(@PathVariable id: Long) = converter.convertToDTO(productsSetsService.findById(id))

    /**
     * Funkcja przyjmująca 'request' wysylany na bazowy adres rozszerzony o '/{id}', gdzie 'id' to parametr, z wykorzystaniem 'HttpMethod-GET'.
     * Jako 'response' zwraca zbiór 'product_set' powiązany z 'product' identyfikujący przez 'id' znajdujący się w 'db'
     *
     * @param id - parametr będący numerem identyfikujacym 'product' w 'db'
     */
    @GetMapping("/product/{id}", produces = ["application/json; charset=utf-8"])
    fun fetchAllProductsSetsByProductId(@PathVariable id: Long) = converter.convertToDTOsSet(productsSetsService.findAllByProductId(id))

    /**
     * Funkcja przyjmująca 'request' wysylany na bazowy adres rozszerzony o '/{id}', gdzie 'id' to parametr, z wykorzystaniem 'HttpMethod-GET'.
     * Jako 'response' zwraca zbiór 'product_set' powiązany z 'product' identyfikujący przez 'productId' i z 'priority' identyfikujący przez 'priorityId' znajdujący się w 'db'
     *
     * @param productId - parametr będący numerem identyfikujacym 'product' w 'db'
     * @param priorityId - parametr będący numerem identyfikujacym 'priority' w 'db'
     */
    @GetMapping("/product/{productId}/priority/{priorityId}", produces = ["application/json; charset=utf-8"])
    fun fetchAllProductsSetsByProductIdAndPriorityId(@PathVariable productId: Long, @PathVariable priorityId: Long) = converter.convertToDTOsSet(productsSetsService.findAllByProductIdAndPriorityId(productId, priorityId))
}