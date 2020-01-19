package garstka.jakub.foodoman.api.controllers

import garstka.jakub.foodoman.api.controllers.PrioritiesController.Companion.PROPERTIES_CONTROLLER_BASE_URL
import garstka.jakub.foodoman.api.mappers.PriorityConverter
import garstka.jakub.foodoman.api.mappers.PriorityConverterImpl
import garstka.jakub.foodoman.api.services.PrioritiesService
import garstka.jakub.foodoman.api.services.impl.PrioritiesServiceImpl
import garstka.jakub.foodoman.api.v1.model.Priority
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(PrioritiesController::class)
internal class PrioritiesControllerTest {

    @TestConfiguration
    class PrioritiesControllerTestConfig {
        @Bean
        fun service() = mockk<PrioritiesServiceImpl>()

        @Bean
        fun converter() = PriorityConverterImpl()
    }

    @Autowired
    private lateinit var converter: PriorityConverter

    @Autowired
    private lateinit var service: PrioritiesService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun fetchAllPriorities() {
        val firstPriority = Priority(id = 1, name = "1", description = "1", prioritiesLevels = null)
        val secondPriority = Priority(id = 2, name = "2", description = "2", prioritiesLevels = null)
        val thirdPriority = Priority(id = 3, name = "3", description = "3", prioritiesLevels = null)
        val set = setOf(firstPriority, secondPriority, thirdPriority)
        every { service.findAll() } returns set

        mockMvc.perform(get("/${PROPERTIES_CONTROLLER_BASE_URL}"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Priority>(3)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[2].id").value(3))
                .andDo(print())
    }

    @Test
    fun fetchAllPrioritiesByProductId() {
        val id: Long = 1
        val firstPriority = Priority(id = 1, name = "1", description = "1", prioritiesLevels = null)
        val secondPriority = Priority(id = 2, name = "2", description = "2", prioritiesLevels = null)
        val thirdPriority = Priority(id = 3, name = "3", description = "3", prioritiesLevels = null)
        val set = setOf(firstPriority, secondPriority, thirdPriority)
        every { service.findAllByProductId(id) } returns set

        mockMvc.perform(get("/${PROPERTIES_CONTROLLER_BASE_URL}/product/$id"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=utf-8"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Priority>(3)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[2].id").value(3))
                .andDo(print())
    }
}