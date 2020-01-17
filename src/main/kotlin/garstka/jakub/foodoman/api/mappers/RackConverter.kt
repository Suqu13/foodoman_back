package garstka.jakub.foodoman.api.mappers

import garstka.jakub.foodoman.api.dto.RackDTO
import garstka.jakub.foodoman.api.v1.model.Rack
import org.mapstruct.InheritConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [SectorConverter::class])
interface RackConverter {
    @Mappings(
            Mapping(source = "id", target = "id"),
            Mapping(source = "number", target = "number"),
            Mapping(source = "description", target = "description"),
            Mapping(source = "sector", target = "sector")
    )
    fun convertToDTO(rack: Rack): RackDTO

    @InheritConfiguration
    fun convertToModel(rackDTO: RackDTO): Rack
}