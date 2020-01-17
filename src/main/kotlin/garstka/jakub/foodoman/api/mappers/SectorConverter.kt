package garstka.jakub.foodoman.api.mappers

import garstka.jakub.foodoman.api.dto.RackDTO
import garstka.jakub.foodoman.api.dto.SectorDTO
import garstka.jakub.foodoman.api.v1.model.Rack
import garstka.jakub.foodoman.api.v1.model.Sector
import org.mapstruct.InheritConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface SectorConverter {
    @Mappings(
            Mapping(source = "id", target = "id"),
            Mapping(source = "number", target = "number"),
            Mapping(source = "description", target = "description")
    )
    fun convertToDTO(sector: Sector): SectorDTO

    @InheritConfiguration
    fun convertToModel(sectorDTO: SectorDTO): Sector
}