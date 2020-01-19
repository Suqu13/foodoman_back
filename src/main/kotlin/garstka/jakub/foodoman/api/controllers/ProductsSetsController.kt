package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.controllers.ProductsSetsController.Companion.PRODUCTS_SETS_CONTROLLER_BASE_URL
import garstka.jakub.foodoman.api.mappers.ProductSetConverter
import garstka.jakub.foodoman.api.services.ProductsSetsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(PRODUCTS_SETS_CONTROLLER_BASE_URL)
class ProductsSetsController(private val productsSetsService: ProductsSetsService, private val converter: ProductSetConverter) {
    companion object {
        const val PRODUCTS_SETS_CONTROLLER_BASE_URL = "products_sets"
    }

    @GetMapping("/{id}", produces = ["application/json; charset=utf-8"])
    fun fetchProductSetById(@PathVariable id: Long) = converter.convertToDTO(productsSetsService.findById(id))

    @GetMapping("/product/{id}", produces = ["application/json; charset=utf-8"])
    fun fetchAllProductsSetsByProductId(@PathVariable id: Long) = converter.convertToDTOsSet(productsSetsService.findAllByProductId(id))

    @GetMapping("/product/{productId}/priority/{priorityId}", produces = ["application/json; charset=utf-8"])
    fun fetchAllProductsSetsByProductIdAndPriorityId(@PathVariable productId: Long, @PathVariable priorityId: Long) = converter.convertToDTOsSet(productsSetsService.findAllByProductIdAndPriorityId(productId, priorityId))
}