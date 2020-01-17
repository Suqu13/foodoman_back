package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.dto.ProductDTO
import garstka.jakub.foodoman.api.mappers.ProductConverter
import garstka.jakub.foodoman.api.services.ProductsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("products")
class ProductsController(private val productsService: ProductsService, private val converter: ProductConverter) {

    @GetMapping
    fun fetchAllProducts() = converter.convertToDTOsSet(productsService.findAll())


    @GetMapping(value = ["/{id}"])
    fun fetchProductById(@PathVariable id: Long) = converter.convertToDto(productsService.findById(id))


    @PutMapping
    fun editProduct(@RequestBody productDTO: ProductDTO) {
        productsService.save(converter.convertToModel(productDTO))
    }
}