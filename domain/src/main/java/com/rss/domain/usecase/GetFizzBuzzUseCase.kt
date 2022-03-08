package com.rss.domain.usecase

import com.rss.domain.model.FizzBuzzModel
import io.reactivex.Single

class GetFizzBuzzUseCase : (FizzBuzzModel) -> Single<List<String>> {

    /**
     * Find Fizz Buzz from a [FizzBuzzModel]
     * if int1 or int2 equals 0 then return empty list
     * else we replace :
     * all multiples of int1 by str1,
     * all multiples of int2 by str2,
     * and all multiples of int1 and int2 by "str1str2".
     */
    override fun invoke(fizzBuzz: FizzBuzzModel): Single<List<String>> =
        if (fizzBuzz.int1 == 0 || fizzBuzz.int2 == 0) {
            Single.just(emptyList())
        } else {
            Single.just(
                (1..fizzBuzz.limit).map { i ->
                    when {
                        (i % fizzBuzz.int1 == 0 && i % fizzBuzz.int2 == 0)
                        -> "${fizzBuzz.str1}${fizzBuzz.str2}"
                        i % fizzBuzz.int1 == 0 -> fizzBuzz.str1
                        i % fizzBuzz.int2 == 0 -> fizzBuzz.str2
                        else -> "$i"
                    }
                }
            )
        }

}
