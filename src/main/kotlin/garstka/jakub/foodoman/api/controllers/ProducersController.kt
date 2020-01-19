package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.controllers.ProducersController.Companion.PRODUCERS_CONTROLLER_BASE_URL
import garstka.jakub.foodoman.api.mappers.ProducerConverter
import garstka.jakub.foodoman.api.services.ProducersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(PRODUCERS_CONTROLLER_BASE_URL)
class ProducersController(private val producersService: ProducersService, private val converter: ProducerConverter) {
    companion object {
        const val PRODUCERS_CONTROLLER_BASE_URL = "producers"
    }

    @GetMapping(produces = ["application/json; charset=utf-8"])
    fun fetchAllProducers() = converter.convertToDTOsSet(producersService.findAll())
}