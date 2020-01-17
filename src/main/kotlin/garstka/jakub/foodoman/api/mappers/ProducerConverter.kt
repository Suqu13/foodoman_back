package garstka.jakub.foodoman.api.mappers

import garstka.jakub.foodoman.api.dto.ProducerDTO
import garstka.jakub.foodoman.api.v1.model.Producer
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface ProducerConverter {
    @Mappings(
            Mapping(source = "id", target = "id"),
            Mapping(source = "name", target = "name"),
            Mapping(source = "description", target = "description")
    )
    fun convertToDto(producer: Producer): ProducerDTO

    @InheritInverseConfiguration
    fun convertToModel(producerDTO: ProducerDTO): Producer

    fun convertToDTOsSet(producers: Set<Producer>): Set<ProducerDTO>

    fun convertToModelsSet(producersDTOs: Set<ProducerDTO>): Set<Producer>
}
