package com.rss.fizzbuzzapp.mapper

import com.rss.domain.model.FizzBuzzModel
import com.rss.fizzbuzzapp.model.FizzBuzzUiModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class FizzBuzzUiMapperTest {

    private lateinit var fizzBuzzUiMapper: FizzBuzzUiMapper

    @Before
    fun setup() {
        fizzBuzzUiMapper = FizzBuzzUiMapper()
    }

    @Test
    fun `verify success ui mapping`() {
        val givenInt1 = Random.nextInt()
        val givenInt2 = Random.nextInt()
        val givenLimit = Random.nextInt()
        val givenStr1 = "Fizz"
        val givenStr2 = "Buzz"

        val expectedValue = FizzBuzzUiModel(givenInt1, givenInt2, givenLimit, givenStr1, givenStr2)

        val actualValue = fizzBuzzUiMapper.toUiModel(givenInt1.toString(),
            givenInt2.toString(),
            givenLimit.toString(),
            givenStr1,
            givenStr2)

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `verify success domain mapping`() {
        val givenInt1 = Random.nextInt()
        val givenInt2 = Random.nextInt()
        val givenLimit = Random.nextInt()
        val givenStr1 = "Fizz"
        val givenStr2 = "Buzz"
        val givenUiModel = FizzBuzzUiModel(givenInt1, givenInt2, givenLimit, givenStr1, givenStr2)

        val expectedValue = FizzBuzzModel(givenInt1, givenInt2, givenLimit, givenStr1, givenStr2)

        val actualValue = fizzBuzzUiMapper.toDomainModel(givenUiModel)

        assertEquals(expectedValue, actualValue)
    }

}