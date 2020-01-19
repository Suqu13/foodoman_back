package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.controllers.ProductsController.Companion.PRODUCTS_CONTROLLER_BASE_URL
import garstka.jakub.foodoman.api.dto.ProductDTO
import garstka.jakub.foodoman.api.mappers.ProductConverter
import garstka.jakub.foodoman.api.services.ProductsService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(PRODUCTS_CONTROLLER_BASE_URL)
class ProductsController(private val productsService: ProductsService, private val converter: ProductConverter) {
    companion object {
        const val PRODUCTS_CONTROLLER_BASE_URL = "products"
    }

    @GetMapping(produces = ["application/json; charset=utf-8"])
    fun fetchAllProducts() = converter.convertToDTOsSet(productsService.findAll())

    @GetMapping("/without_fields", produces = ["application/json; charset=utf-8"])
    fun fetchAllProductsWithoutFields() = converter.convertToDTOsSetWithoutFields(productsService.findAll())

    @GetMapping("{id}", produces = ["application/json; charset=utf-8"])
    fun fetchProductById(@PathVariable id: Long) = converter.convertToDTO(productsService.findById(id))

    @PutMapping(consumes = ["application/json; charset=utf-8"])
    fun editProduct(@RequestBody productDTO: ProductDTO) {
        productsService.save(converter.convertToModel(productDTO))
    }

    @PatchMapping(consumes = ["application/json; charset=utf-8"])
    fun editProductPartially(@RequestBody productDTO: ProductDTO) {
        productsService.editPartially(converter.convertToModel(productDTO))
    }
}