package garstka.jakub.foodoman.api.mappers

import garstka.jakub.foodoman.api.dto.ProductSetDTO
import garstka.jakub.foodoman.api.v1.model.ProductSet
import org.mapstruct.*

@Mapper(componentModel = "spring", uses = [RackConverter::class])
interface ProductSetConverter {
    @Named("convertToDTO")
    @Mappings(
            Mapping(source = "id", target = "id"),
            Mapping(source = "pieces", target = "pieces"),
            Mapping(source = "expiryDate", target = "expiryDate"),
            Mapping(source = "description", target = "description"),
            Mapping(source = "rack", target = "rack")
    )
    fun convertToDTO(productSet: ProductSet): ProductSetDTO

    @Named("convertToDTOWithoutRack")
    @InheritConfiguration(name = "convertToDTO")
    @Mapping(ignore = true, target = "rack")
    fun convertToDTOWithoutRack(productSet: ProductSet): ProductSetDTO

    @Named("convertToModel")
    @InheritConfiguration(name = "convertToDTO")
    fun convertToModel(productSetDTO: ProductSetDTO): ProductSet

    @IterableMapping(qualifiedByName = ["convertToDTOWithoutRack"])
    fun convertToDTOsWithoutRacksSet(productSets: Set<ProductSet>): Set<ProductSetDTO>

    @IterableMapping(qualifiedByName = ["convertToDTO"])
    fun convertToDTOsSet(productSets: Set<ProductSet>): Set<ProductSetDTO>

    @IterableMapping(qualifiedByName = ["convertToModel"])
    fun convertToModelsSet(productSetsDTOs: Set<ProductSetDTO>): Set<ProductSet>
}