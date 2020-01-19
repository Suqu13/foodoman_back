package garstka.jakub.foodoman.api.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import garstka.jakub.foodoman.api.controllers.ProductsController.Companion.PRODUCTS_CONTROLLER_BASE_URL
import garstka.jakub.foodoman.api.custom_exceptions.ProductNotFoundException
import garstka.jakub.foodoman.api.dto.ProductDTO
import garstka.jakub.foodoman.api.mappers.ProducerConverter
import garstka.jakub.foodoman.api.mappers.ProducerConverterImpl
import garstka.jakub.foodoman.api.mappers.ProductConverter
import garstka.jakub.foodoman.api.mappers.ProductConverterImpl
import garstka.jakub.foodoman.api.services.ProductsService
import garstka.jakub.foodoman.api.services.impl.ProductsServiceImpl
import garstka.jakub.foodoman.api.v1.model.Product
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(ProductsController::class)
internal class ProductsControllerTest {

    @TestConfiguration
    class PrioritiesControllerTestConfig {
        @Bean
        fun service() = mockk<ProductsServiceImpl>()

        @Bean
        fun productConverter() = ProductConverterImpl()

        @Bean
        fun producerConverter() = ProducerConverterImpl()
    }

    @Autowired
    private lateinit var productConverter: ProductConverter

    @Autowired
    private lateinit var producerConverter: ProducerConverter

    @Autowired
    private lateinit var service: ProductsService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun fetchAllProducts() {
        val firstProduct = Product(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, productSets = null, imageUrl = "1")
        val secondProduct = Product(id = 2, name = "2", description = "2", height = 2.0, weight = 2.0, surface = 2.0, allPiecesNumber = 2, producer = null, productSets = null, imageUrl = "2")
        val thirdProduct = Product(id = 3, name = "3", description = "3", height = 3.0, weight = 3.0, surface = 3.0, allPiecesNumber = 3, producer = null, productSets = null, imageUrl = "3")
        val set = setOf(firstProduct, secondProduct, thirdProduct)
        every { service.findAll() } returns set

        mockMvc.perform(MockMvcRequestBuilders.get("/${PRODUCTS_CONTROLLER_BASE_URL}"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize<Product>(3)))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun fetchAllProductsWithoutFields() {
        val firstProduct = Product(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, productSets = null, imageUrl = "1")
        val secondProduct = Product(id = 2, name = "2", description = "2", height = 2.0, weight = 2.0, surface = 2.0, allPiecesNumber = 2, producer = null, productSets = null, imageUrl = "2")
        val thirdProduct = Product(id = 3, name = "3", description = "3", height = 3.0, weight = 3.0, surface = 3.0, allPiecesNumber = 3, producer = null, productSets = null, imageUrl = "3")
        val set = setOf(firstProduct, secondProduct, thirdProduct)
        every { service.findAll() } returns set

        mockMvc.perform(MockMvcRequestBuilders.get("/${PRODUCTS_CONTROLLER_BASE_URL}/without_fields"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize<Product>(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weight").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].height").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surface").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].producer").doesNotExist())
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun fetchProductById() {
        val id: Long = 1;
        val product = Product(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, productSets = null, imageUrl = "1")
        every { service.findById(id) } returns product

        mockMvc.perform(MockMvcRequestBuilders.get("/${PRODUCTS_CONTROLLER_BASE_URL}/$id"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Throws(ProductNotFoundException::class)
    fun `fetchProductById failed`() {
        val id: Long = 1;
        every { service.findById(id) } throws ProductNotFoundException(id)

        mockMvc.perform(MockMvcRequestBuilders.get("/${PRODUCTS_CONTROLLER_BASE_URL}/$id"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun editProduct() {
        val product = Product(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, productSets = null, imageUrl = "1")
        val productDTO = ProductDTO(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, imageUrl = "1")
        every { service.save(product) } returns product

        mockMvc.perform(MockMvcRequestBuilders.put("/${PRODUCTS_CONTROLLER_BASE_URL}")
                .content(ObjectMapper().writeValueAsString(productDTO))
                .contentType("application/json;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun editProductPartially() {
        val product = Product(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, productSets = null, imageUrl = "1")
        val productDTO = ProductDTO(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, imageUrl = "1")
        every { service.editPartially(product) } returns product

        mockMvc.perform(MockMvcRequestBuilders.patch("/${PRODUCTS_CONTROLLER_BASE_URL}")
                .content(ObjectMapper().writeValueAsString(productDTO))
                .contentType("application/json;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcResultHandlers.print())
    }
}