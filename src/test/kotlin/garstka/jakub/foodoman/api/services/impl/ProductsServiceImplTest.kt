package garstka.jakub.foodoman.api.services.impl

import garstka.jakub.foodoman.api.custom_exceptions.ProducerNotFoundException
import garstka.jakub.foodoman.api.producerNotFoundByIdMessage
import garstka.jakub.foodoman.api.repositories.ProductsRepository
import garstka.jakub.foodoman.api.services.ProductsService
import garstka.jakub.foodoman.api.v1.model.Product
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class ProductsServiceImplTest {

    @MockK
    private lateinit var productsRepository: ProductsRepository

    private lateinit var productsService: ProductsService

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)
        productsService = ProductsServiceImpl(productsRepository)
    }

    @Test
    internal fun `findAll returns empty set`() {
        //given
        every { productsRepository.findAll() } returns listOf()
        //when
        val result = productsService.findAll()
        //then
        assertEquals(setOf<Product>(), result)
    }

    @Test
    internal fun `findAll returns set with three elements`() {
        //given
        val firstProduct = Product(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, productSets = null, imageUrl = "1")
        val secondProduct = Product(id = 1, name = "2", description = "2", height = 2.0, weight = 2.0, surface = 2.0, allPiecesNumber = 2, producer = null, productSets = null, imageUrl = "2")
        val thirdProduct = Product(id = 1, name = "3", description = "3", height = 3.0, weight = 3.0, surface = 3.0, allPiecesNumber = 3, producer = null, productSets = null, imageUrl = "3")
        val list = listOf(firstProduct, secondProduct, thirdProduct)
        every { productsRepository.findAll() } returns list
        //when
        val result = productsService.findAll()
        //then
        assertEquals(list.toSet(), result)
    }

    @Test
    internal fun `findById throws exception, priority not found`() {
        //given
        val id: Long = 1;
        every { productsRepository.findById(id) } throws ProducerNotFoundException(id);
        //when
        val result: Exception = assertThrows(ProducerNotFoundException::class.java) { productsService.findById(id) }
        //then
        assertEquals(producerNotFoundByIdMessage(id), result.message)
    }

    @Test
    internal fun `findById returns right priority`() {
        //given
        val id: Long = 1;
        val producer = Product(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, productSets = null, imageUrl = "1")
        every { productsRepository.findById(id) } returns Optional.of(producer)
        //when
        val result = productsService.findById(id)
        //then
        assertEquals(result, producer)
    }

    @Test
    internal fun `save returns right priority`() {
        //given
        val producer = Product(id = 1, name = "1", description = "1", height = 1.0, weight = 1.0, surface = 1.0, allPiecesNumber = 1, producer = null, productSets = null, imageUrl = "1")
        every { productsRepository.save(producer) } returns producer
        //when
        val result = productsService.save(producer)
        //then
        assertEquals(result, producer)
    }
}