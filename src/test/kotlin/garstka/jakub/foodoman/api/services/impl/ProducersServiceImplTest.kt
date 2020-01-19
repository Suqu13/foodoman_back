package garstka.jakub.foodoman.api.services.impl
import garstka.jakub.foodoman.api.custom_exceptions.ProducerNotFoundException
import garstka.jakub.foodoman.api.producerNotFoundByIdMessage
import garstka.jakub.foodoman.api.repositories.ProducersRepository
import garstka.jakub.foodoman.api.services.ProducersService
import garstka.jakub.foodoman.api.v1.model.Producer
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class ProducersServiceImplTest {

    @MockK
    private lateinit var producersRepository: ProducersRepository

    private lateinit var producersService: ProducersService

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)
        producersService = ProducersServiceImpl(producersRepository)
    }

    @Test
    internal fun `findAll returns empty set`() {
        //given
        every { producersRepository.findAll() } returns listOf()
        //when
        val result = producersService.findAll()
        //then
        assertEquals(setOf<Producer>(), result)
    }

    @Test
    internal fun `findAll returns set with three elements`() {
        //given
        val firstProducer = Producer(id = 1, name = "1", description = "1", products = null)
        val secondProducer = Producer(id = 2, name = "2", description = "2", products = null)
        val thirdProducer = Producer(id = 3, name = "3", description = "3", products = null)
        val list = listOf(firstProducer, secondProducer, thirdProducer)
        every { producersRepository.findAll() } returns list
        //when
        val result = producersService.findAll()
        //then
        assertEquals(list.toSet(), result)
    }

    @Test
    internal fun `findById throws exception, priority not found`() {
        //given
        val id: Long = 1;
        every { producersRepository.findById(id) } throws ProducerNotFoundException(id);
        //when
        val result: Exception = assertThrows(ProducerNotFoundException::class.java) { producersService.findById(id) }
        //then
        assertEquals(producerNotFoundByIdMessage(id), result.message)
    }

    @Test
    internal fun `findById returns right priority`() {
        //given
        val id: Long = 1;
        val producer = Producer(id = 1, name = "1", description = "1", products = null)
        every { producersRepository.findById(id) } returns Optional.of(producer)
        //when
        val result = producersService.findById(id)
        //then
        assertEquals(result, producer)
    }

    @Test
    internal fun `save returns right priority`() {
        //given
        val producer = Producer(id = 1, name = "1", description = "1", products = null)
        every { producersRepository.save(producer) } returns producer
        //when
        val result = producersService.save(producer)
        //then
        assertEquals(result, producer)
    }
}