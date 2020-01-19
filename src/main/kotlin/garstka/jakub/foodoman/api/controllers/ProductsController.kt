package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.controllers.ProductsController.Companion.PRODUCTS_CONTROLLER_BASE_URL
import garstka.jakub.foodoman.api.dto.ProductDTO
import garstka.jakub.foodoman.api.mappers.ProductConverter
import garstka.jakub.foodoman.api.services.ProductsService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

/**
 * Klasa kontrolera obsługujące 'request' wysyłane na adres '.../products'
 *
 */
@RestController
@RequestMapping(PRODUCTS_CONTROLLER_BASE_URL)
class ProductsController(private val productsService: ProductsService, private val converter: ProductConverter) {
    companion object {
        const val PRODUCTS_CONTROLLER_BASE_URL = "products"
    }

    /**
     * Funkcja przyjmująca 'request' wysylany na bazowy adres z wykorzystaniem 'HttpMethod-GET'.
     * Jako 'response' zwraca wszystkie 'products' znajdujące się w 'db'
     *
     */
    @GetMapping(produces = ["application/json; charset=utf-8"])
    fun fetchAllProducts() = converter.convertToDTOsSet(productsService.findAll())

    /**
     * Funkcja przyjmująca 'request' wysylany na bazowy adres z wykorzystaniem 'HttpMethod-GET'.
     * Jako 'response' zwraca wszystkie 'products' znajdujące się w 'db', bez określinych pól
     *
     */
    @GetMapping("/without_fields", produces = ["application/json; charset=utf-8"])
    fun fetchAllProductsWithoutFields() = converter.convertToDTOsSetWithoutFields(productsService.findAll())

    /**
     * Funkcja przyjmująca 'request' wysylany na bazowy adres rozszerzony o '/{id}', gdzie 'id' to parametr, z wykorzystaniem 'HttpMethod-GET'.
     * Jako 'response' zwraca 'product' powiązany z 'id' znajdujący się w 'db'
     *
     * @param id - parametr będący numerem identyfikujacym 'product' w 'db'
     */
    @GetMapping("{id}", produces = ["application/json; charset=utf-8"])
    fun fetchProductById(@PathVariable id: Long) = converter.convertToDTO(productsService.findById(id))

    /**
     * Funkcja przyjmująca 'request' wysylany na bazowy adres z wykorzystaniem 'HttpMethod-PUT'.
     * Umożliwia całkowite zastąpienie 'product' jego nową instancją przesłaną jako 'request-body'
     *
     * @param productDTO - 'request-body' po serializacji wykorzystywany do aktualizacji
     */
    @PutMapping(consumes = ["application/json; charset=utf-8"])
    fun editProduct(@RequestBody productDTO: ProductDTO) {
        productsService.save(converter.convertToModel(productDTO))
    }

    /**
     * Funkcja przyjmująca 'request' wysylany na bazowy adres z wykorzystaniem 'HttpMethod-PATCH'.
     * Umożliwia aktualizacje pół 'product' jego nową instancją przesłaną jako 'request-body'
     *
     * @param productDTO - 'request-body' po serializacji wykorzystywany do aktualizacji
     */
    @PatchMapping(consumes = ["application/json; charset=utf-8"])
    fun editProductPartially(@RequestBody productDTO: ProductDTO) {
        productsService.editPartially(converter.convertToModel(productDTO))
    }
}