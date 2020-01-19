package garstka.jakub.foodoman.api.services.impl

import garstka.jakub.foodoman.api.custom_exceptions.ProductSetNotFoundException
import garstka.jakub.foodoman.api.productSetNotFoundByIdMessage
import garstka.jakub.foodoman.api.repositories.ProductsSetsRepository
import garstka.jakub.foodoman.api.services.ProductsSetsService
import garstka.jakub.foodoman.api.v1.model.ProductSet
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class ProductsSetsServiceImplTest {

    @MockK
    private lateinit var productsSetsRepository: ProductsSetsRepository

    private lateinit var productsSetsService: ProductsSetsService

    @BeforeEach
    internal fun setUp() {
        MockKAnnotations.init(this)
        productsSetsService = ProductsSetsServiceImpl(productsSetsRepository)
    }

    @Test
    internal fun `findAllByProductIdAndPriorityId returns right set`() {
        //given
        val id: Long = 1;
        val firstProductSet = ProductSet(id = 1, expiryDate = Date(), pieces = 1, description = "1", rack = null, product = null, prioritiesLevels = null)
        val secondProductSet = ProductSet(id = 2, expiryDate = Date(), pieces = 2, description = "2", rack = null, product = null, prioritiesLevels = null)
        val thirdProductSet = ProductSet(id = 3, expiryDate = Date(), pieces = 3, description = "3", rack = null, product = null, prioritiesLevels = null)
        val set = setOf(firstProductSet, secondProductSet, thirdProductSet)
        every { productsSetsRepository.findAllByProduct_IdAndPriorityId(productId = id, priorityId = id) } returns set
        //when
        val result = productsSetsService.findAllByProductIdAndPriorityId(productId = id, priorityId = id)
        //then
        assertEquals(set, result)
    }

    @Test
    internal fun `findAllByProductId returns right set`() {
        //given
        val id: Long = 1;
        val firstProductSet = ProductSet(id = 1, expiryDate = Date(), pieces = 1, description = "1", rack = null, product = null, prioritiesLevels = null)
        val secondProductSet = ProductSet(id = 2, expiryDate = Date(), pieces = 2, description = "2", rack = null, product = null, prioritiesLevels = null)
        val thirdProductSet = ProductSet(id = 3, expiryDate = Date(), pieces = 3, description = "3", rack = null, product = null, prioritiesLevels = null)
        val set = setOf(firstProductSet, secondProductSet, thirdProductSet)
        every { productsSetsRepository.findAllByProduct_Id(id) } returns set
        //when
        val result = productsSetsService.findAllByProductId(id)
        //then
        assertEquals(set, result)
    }

    @Test
    internal fun `findAll returns empty set`() {
        //given
        every { productsSetsRepository.findAll() } returns listOf()
        //when
        val result = productsSetsService.findAll()
        //then
        assertEquals(setOf<ProductSet>(), result)
    }

    @Test
    internal fun `findAll returns set with three elements`() {
        //given
        val firstProductSet = ProductSet(id = 1, expiryDate = Date(), pieces = 1, description = "1", rack = null, product = null, prioritiesLevels = null)
        val secondProductSet = ProductSet(id = 2, expiryDate = Date(), pieces = 2, description = "2", rack = null, product = null, prioritiesLevels = null)
        val thirdProductSet = ProductSet(id = 3, expiryDate = Date(), pieces = 3, description = "3", rack = null, product = null, prioritiesLevels = null)
        val list = listOf(firstProductSet, secondProductSet, thirdProductSet)
        every { productsSetsRepository.findAll() } returns list
        //when
        val result = productsSetsService.findAll()
        //then
        assertEquals(list.toSet(), result)
    }

    @Test
    internal fun `findById throws exception, priority not found`() {
        //given
        val id: Long = 1;
        every { productsSetsRepository.findById(id) } throws ProductSetNotFoundException(id);
        //when
        val result: Exception = assertThrows(ProductSetNotFoundException::class.java) { productsSetsService.findById(id) }
        //then
        assertEquals(productSetNotFoundByIdMessage(id), result.message)
    }

    @Test
    internal fun `findById returns right priority`() {
        //given
        val id: Long = 1;
        val productSet = ProductSet(id = 1, expiryDate = Date(), pieces = 1, description = "1", rack = null, product = null, prioritiesLevels = null)
        every { productsSetsRepository.findById(id) } returns Optional.of(productSet)
        //when
        val result = productsSetsService.findById(id)
        //then
        assertEquals(result, productSet)
    }

    @Test
    internal fun `save returns right priority`() {
        //given
        val productSet = ProductSet(id = 1, expiryDate = Date(), pieces = 1, description = "1", rack = null, product = null, prioritiesLevels = null)
        every { productsSetsRepository.save(productSet) } returns productSet
        //when
        val result = productsSetsService.save(productSet)
        //then
        assertEquals(result, productSet)
    }
}
