package com.rss.fizzbuzzapp.mapper

import com.rss.domain.model.FizzBuzzModel
import com.rss.fizzbuzzapp.model.FizzBuzzUiModel

class FizzBuzzUiMapper {

    /**
     * Map data to ui model
     */
    fun toUiModel(
        int1: String,
        int2: String,
        limit: String,
        str1: String,
        str2: String,
    ): FizzBuzzUiModel =
        FizzBuzzUiModel(int1.toInt(), int2.toInt(), limit.toInt(), str1, str2)

    /**
     * Map ui model to domain model
     */
    fun toDomainModel(
        uiModel: FizzBuzzUiModel,
    ): FizzBuzzModel =
        FizzBuzzModel(uiModel.int1, uiModel.int2, uiModel.limit, uiModel.str1, uiModel.str2)
}