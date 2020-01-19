package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.controllers.ProductsSetsController.Companion.PRODUCTS_SETS_CONTROLLER_BASE_URL
import garstka.jakub.foodoman.api.custom_exceptions.ProductSetNotFoundException
import garstka.jakub.foodoman.api.mappers.*
import garstka.jakub.foodoman.api.services.ProductsSetsService
import garstka.jakub.foodoman.api.services.impl.ProductsSetsServiceImpl
import garstka.jakub.foodoman.api.v1.model.ProductSet
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.collection.IsCollectionWithSize.hasSize
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
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(ProductsSetsController::class)
internal class ProductsSetsControllerTest {
    @TestConfiguration
    class PrioritiesControllerTestConfig {
        @Bean
        fun service() = mockk<ProductsSetsServiceImpl>()

        @Bean
        fun productSetConverter() = ProductSetConverterImpl()

        @Bean
        fun rackConverter() = RackConverterImpl()

        @Bean
        fun sectorConverter() = SectorConverterImpl()
    }

    @Autowired
    private lateinit var productSetConverter: ProductSetConverter

    @Autowired
    private lateinit var rackConverter: RackConverter

    @Autowired
    private lateinit var sectorConverter: SectorConverter

    @Autowired
    private lateinit var service: ProductsSetsService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun fetchProductSetById() {
        val id: Long = 1;
        val productSet = ProductSet(id = 1, pieces = 10, description = "description", rack = null, prioritiesLevels = null, product = null, expiryDate = Date())
        every { service.findById(id) } returns productSet

        mockMvc.perform(MockMvcRequestBuilders.get("/${PRODUCTS_SETS_CONTROLLER_BASE_URL}/$id"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `fetchProductSetById failed`() {
        val id: Long = 1;
        every { service.findById(id) } throws ProductSetNotFoundException(id)

        mockMvc.perform(MockMvcRequestBuilders.get("/${PRODUCTS_SETS_CONTROLLER_BASE_URL}/$id"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun fetchAllProductsSetsByProductId() {
        val id: Long = 1;
        val firstProductSet = ProductSet(id = 1, pieces = 10, description = "first description", rack = null, prioritiesLevels = null, product = null, expiryDate = Date())
        val secondProductSet = ProductSet(id = 2, pieces = 20, description = "second description", rack = null, prioritiesLevels = null, product = null, expiryDate = Date())
        val thirdProductSet = ProductSet(id = 3, pieces = 30, description = "third description", rack = null, prioritiesLevels = null, product = null, expiryDate = Date())
        val set = setOf(firstProductSet, secondProductSet, thirdProductSet)
        every { service.findAllByProductId(id) } returns set

        mockMvc.perform(MockMvcRequestBuilders.get("/${PRODUCTS_SETS_CONTROLLER_BASE_URL}/product/$id"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize<ProductSet>(3)))
                .andDo(MockMvcResultHandlers.print())

    }

    @Test
    fun fetchAllProductsSetsByProductIdAndPriorityId() {
        val id: Long = 1;
        val firstProductSet = ProductSet(id = 1, pieces = 10, description = "first description", rack = null, prioritiesLevels = null, product = null, expiryDate = Date())
        val secondProductSet = ProductSet(id = 2, pieces = 20, description = "second description", rack = null, prioritiesLevels = null, product = null, expiryDate = Date())
        val thirdProductSet = ProductSet(id = 3, pieces = 30, description = "third description", rack = null, prioritiesLevels = null, product = null, expiryDate = Date())
        val set = setOf(firstProductSet, secondProductSet, thirdProductSet)
        every { service.findAllByProductIdAndPriorityId(priorityId = id, productId = id) } returns set

        mockMvc.perform(MockMvcRequestBuilders.get("/${PRODUCTS_SETS_CONTROLLER_BASE_URL}/product/$id/priority/$id"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize<ProductSet>(3)))
                .andDo(MockMvcResultHandlers.print())

    }
}