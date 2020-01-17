package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.mappers.ProducerConverter
import garstka.jakub.foodoman.api.services.ProducersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("producers")
class ProducersController (private val producersService: ProducersService, private val converter: ProducerConverter) {

    @GetMapping
    fun fetchAllProducers() = converter.convertToDTOsSet(producersService.findAll())
}