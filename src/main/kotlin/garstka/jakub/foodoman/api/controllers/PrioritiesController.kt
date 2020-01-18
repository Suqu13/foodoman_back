package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.mappers.PriorityConverter
import garstka.jakub.foodoman.api.services.PrioritiesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("priorities")
class PrioritiesController(private val prioritiesService: PrioritiesService, private val converter: PriorityConverter) {

    @GetMapping(produces = ["application/json; charset=utf-8"])
    fun fetchAllPriorities() = converter.convertToDTOsSet(prioritiesService.findAll())

    @GetMapping("/product/{id}", produces = ["application/json; charset=utf-8"])
    fun fetchAllPrioritiesByProductId(@PathVariable id: Long) = converter.convertToDTOsSet(prioritiesService.findAllByProductId(id))
}