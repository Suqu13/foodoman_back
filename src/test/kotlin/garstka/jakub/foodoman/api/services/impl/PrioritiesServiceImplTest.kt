package garstka.jakub.foodoman.api.services.impl
import garstka.jakub.foodoman.api.custom_exceptions.PriorityNotFoundException
import garstka.jakub.foodoman.api.priorityNotFoundByIdMessage
import garstka.jakub.foodoman.api.repositories.PrioritiesRepository
import garstka.jakub.foodoman.api.services.PrioritiesService
import garstka.jakub.foodoman.api.v1.model.Priority
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class PrioritiesServiceImplTest {

    @MockK
    private lateinit var prioritiesRepository: PrioritiesRepository

    private lateinit var prioritiesService: PrioritiesService

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)
        prioritiesService = PrioritiesServiceImpl(prioritiesRepository)
    }

    @Test
    internal fun `findAll returns empty set`() {
        //given
        every { prioritiesRepository.findAll() } returns listOf()
        //when
        val result = prioritiesService.findAll()
        //then
        assertEquals(setOf<Priority>(), result)
    }

    @Test
    internal fun `findAll returns set with three elements`() {
        //given
        val firstPriority = Priority(id = 1, name = "1", description = "1", prioritiesLevels = null)
        val secondPriority = Priority(id = 2, name = "2", description = "2", prioritiesLevels = null)
        val thirdPriority = Priority(id = 3, name = "3", description = "3", prioritiesLevels = null)
        val list = listOf(firstPriority, secondPriority, thirdPriority)
        every { prioritiesRepository.findAll() } returns list
        //when
        val result = prioritiesService.findAll()
        //then
        assertEquals(list.toSet(), result)
    }

    @Test
    internal fun `findById throws exception, priority not found`() {
        //given
        val id: Long = 1;
        every { prioritiesRepository.findById(id) } throws PriorityNotFoundException(id);
        //when
        val result: Exception = assertThrows(PriorityNotFoundException::class.java) { prioritiesService.findById(id) }
        //then
        assertEquals(priorityNotFoundByIdMessage(id), result.message)
    }

    @Test
    internal fun `findById returns right priority`() {
        //given
        val id: Long = 1;
        val priority = Priority(id = 1, name = "1", description = "1", prioritiesLevels = null)
        every { prioritiesRepository.findById(id) } returns Optional.of(priority)
        //when
        val result = prioritiesService.findById(id)
        //then
        assertEquals(result, priority)
    }

    @Test
    internal fun `save returns right priority`() {
        //given
        val priority = Priority(id = 1, name = "1", description = "1", prioritiesLevels = null)
        every { prioritiesRepository.save(priority) } returns priority
        //when
        val result = prioritiesService.save(priority)
        //then
        assertEquals(result, priority)
    }
}