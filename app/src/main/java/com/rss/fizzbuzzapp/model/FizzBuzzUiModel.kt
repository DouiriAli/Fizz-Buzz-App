package com.rss.fizzbuzzapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FizzBuzzUiModel(
    val int1: Int,
    val int2: Int,
    val limit: Int,
    val str1: String,
    val str2: String,
) : Parcelable