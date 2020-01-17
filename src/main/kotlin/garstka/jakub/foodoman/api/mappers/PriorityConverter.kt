package garstka.jakub.foodoman.api.mappers

import garstka.jakub.foodoman.api.dto.PriorityDTO
import garstka.jakub.foodoman.api.v1.model.Priority
import garstka.jakub.foodoman.api.v1.model.Producer
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface PriorityConverter {
    @Mappings(
            Mapping(source = "id", target = "id"),
            Mapping(source = "name", target = "name"),
            Mapping(source = "description", target = "description")
    )
    fun convertToDto(priority: Priority): PriorityDTO

    @InheritInverseConfiguration
    fun convertToModel(priorityDTO: PriorityDTO): Producer

    fun convertToDTOsSet(priorities: Set<Priority>): Set<PriorityDTO>

    fun convertToModelsSet(prioritiesDTOs: Set<PriorityDTO>): Set<Priority>
}