package com.rss.domain.usecase

import com.rss.domain.model.FizzBuzzModel
import org.junit.Before
import org.junit.Test

class GetFizzBuzzUseCaseTest {

    private lateinit var getFizzBuzzUseCase: GetFizzBuzzUseCase

    @Before
    fun setup() {
        getFizzBuzzUseCase = GetFizzBuzzUseCase()
    }

    @Test
    fun `verify returns result`() {
        val givenFizz = "Fizz"
        val givenBuzz = "Buzz"
        val givenFizzBuzz = "$givenFizz$givenBuzz"
        val givenDomainModel = FizzBuzzModel(3, 5, 15, givenFizz, givenBuzz)

        val expected = listOf("1", "2", givenFizz, "4", givenBuzz, givenFizz, "7", "8", givenFizz,
            givenBuzz, "11", givenFizz, "13", "14", givenFizzBuzz)

        getFizzBuzzUseCase.invoke(givenDomainModel)
            .test()
            .assertValue(expected)
    }

    @Test
    fun `verify returns empty list when limit equal 0`() {
        val givenDomainModel = FizzBuzzModel(3, 5, 0, "Fizz", "Buzz")

        getFizzBuzzUseCase.invoke(givenDomainModel)
            .test()
            .assertValue(emptyList())
    }

    @Test
    fun `verify returns empty list when int1 or int2 equals 0`() {
        val givenDomainModel = FizzBuzzModel(0, 0, 1000, "Fizz", "Buzz")

        getFizzBuzzUseCase.invoke(givenDomainModel)
            .test()
            .assertValue(emptyList())
    }
}