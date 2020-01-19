package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.controllers.ProducersController.Companion.PRODUCERS_CONTROLLER_BASE_URL
import garstka.jakub.foodoman.api.mappers.ProducerConverter
import garstka.jakub.foodoman.api.services.ProducersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Klasa kontrolera obsługujące 'request' wysyłane na adres '.../producers'
 *
 */
@RestController
@RequestMapping(PRODUCERS_CONTROLLER_BASE_URL)
class ProducersController(private val producersService: ProducersService, private val converter: ProducerConverter) {
    companion object {
        const val PRODUCERS_CONTROLLER_BASE_URL = "producers"
    }

    /**
     * Funkcja przyjmująca 'request' wysylany na bazowy adres z wykorzystaniem 'HttpMethod-GET'.
     * Jako 'response' zwraca wszystkie 'producer' znajdujące się w 'db'
     *
     */
    @GetMapping(produces = ["application/json; charset=utf-8"])
    fun fetchAllProducers() = converter.convertToDTOsSet(producersService.findAll())
}