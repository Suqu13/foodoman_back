package garstka.jakub.foodoman.api.mappers

import garstka.jakub.foodoman.api.dto.ProductDTO
import garstka.jakub.foodoman.api.v1.model.Product
import org.mapstruct.*

@Mapper(componentModel = "spring", uses = [ProducerConverter::class])
interface ProductConverter {
    @Named("convertToDTO")
    @Mappings(
            Mapping(source = "id", target = "id"),
            Mapping(source = "name", target = "name"),
            Mapping(source = "weight", target = "weight"),
            Mapping(source = "height", target = "height"),
            Mapping(source = "allPiecesNumber", target = "allPiecesNumber"),
            Mapping(source = "surface", target = "surface"),
            Mapping(source = "imageUrl", target = "imageUrl"),
            Mapping(source = "description", target = "description"),
            Mapping(source = "producer", target = "producer")
    )
    fun convertToDTO(product: Product): ProductDTO

    @Named("convertToDTOWithoutFields")
    @InheritConfiguration(name = "convertToDTO")
    @Mappings(
            Mapping(ignore = true, target = "weight"),
            Mapping(ignore = true, target = "height"),
            Mapping(ignore = true, target = "surface"),
            Mapping(ignore = true, target = "description"),
            Mapping(ignore = true, target = "producer")
    )
    fun convertToDTOWithoutFields(product: Product): ProductDTO

    @Named("convertToModel")
    @InheritInverseConfiguration(name = "convertToDTO")
    fun convertToModel(productDTO: ProductDTO): Product

    @IterableMapping(qualifiedByName = ["convertToDTOWithoutFields"])
    fun convertToDTOsSetWithoutFields(products: Set<Product>): Set<ProductDTO>

    @IterableMapping(qualifiedByName = ["convertToDTO"])
    fun convertToDTOsSet(products: Set<Product>): Set<ProductDTO>

    @IterableMapping(qualifiedByName = ["convertToModel"])
    fun convertToModelsSet(productsDTOs: Set<ProductDTO>): Set<Product>
}
