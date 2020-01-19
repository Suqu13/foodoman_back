package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.mappers.ProducerConverter
import garstka.jakub.foodoman.api.mappers.ProducerConverterImpl
import garstka.jakub.foodoman.api.services.ProducersService
import garstka.jakub.foodoman.api.services.impl.ProducersServiceImpl
import garstka.jakub.foodoman.api.v1.model.Priority
import garstka.jakub.foodoman.api.v1.model.Producer
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.Matchers.hasSize
import org.hamcrest.collection.IsCollectionWithSize
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
@WebMvcTest(ProducersController::class)
internal class ProducersControllerTest {

    @TestConfiguration
    class PrioritiesControllerTestConfig {
        @Bean
        fun service() = mockk<ProducersServiceImpl>()

        @Bean
        fun converter() = ProducerConverterImpl()
    }

    @Autowired
    private lateinit var converter: ProducerConverter

    @Autowired
    private lateinit var service: ProducersService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun fetchAllProducers() {
        val firstProducer = Producer(id = 1, name = "1", description = "1", products = null)
        val secondProducer = Producer(id = 2, name = "2", description = "2", products = null)
        val thirdProducer = Producer(id = 3, name = "3", description = "3", products = null)
        val set = setOf(firstProducer, secondProducer, thirdProducer)
        every { service.findAll() } returns set

        mockMvc.perform(MockMvcRequestBuilders.get("/${ProducersController.PRODUCERS_CONTROLLER_BASE_URL}"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize<Producer>(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andDo(MockMvcResultHandlers.print())
    }
}