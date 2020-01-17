package garstka.jakub.foodoman.api.mappers

import garstka.jakub.foodoman.api.dto.ProductDTO
import garstka.jakub.foodoman.api.v1.model.Product
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [ProducerConverter::class])
interface ProductConverter {
    @Mappings(
            Mapping(source = "id", target = "id"),
            Mapping(source = "name", target = "name"),
            Mapping(source = "weight", target = "weight"),
            Mapping(source = "height", target = "height"),
            Mapping(source = "allPiecesNumber", target = "allPiecesNumber"),
            Mapping(source = "surface", target = "surface"),
            Mapping(source = "description", target = "description"),
            Mapping(source = "producer", target = "producer")
    )
    fun convertToDto(product: Product): ProductDTO

    @InheritInverseConfiguration
    fun convertToModel(productDTO: ProductDTO): Product

    fun convertToDTOsSet(products: Set<Product>): Set<ProductDTO>

    fun convertToModelsSet(productsDTOs: Set<ProductDTO>): Set<Product>
}
