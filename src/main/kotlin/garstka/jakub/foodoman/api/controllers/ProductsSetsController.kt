package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.mappers.ProductSetConverter
import garstka.jakub.foodoman.api.services.ProductsSetsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products_sets")
class ProductsSetsController(private val productsSetsService: ProductsSetsService, private val converter: ProductSetConverter) {

    @GetMapping(value = ["/{id}"])
    fun fetchProductSetById(@PathVariable id: Long) = converter.convertToDTO(productsSetsService.findById(id))

    @GetMapping(value = ["/product/{id}"])
    fun fetchProductsSetsByProductId(@PathVariable id: Long) = converter.convertToDTOsWithoutRacksSet(productsSetsService.findAllByProduct_Id(id))

}