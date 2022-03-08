package com.rss.fizzbuzzapp.di

import com.rss.fizzbuzzapp.mapper.FizzBuzzUiMapper
import com.rss.fizzbuzzapp.firstscreen.FizzBuzzFirstViewModel
import com.rss.fizzbuzzapp.secondscreen.FizzBuzzSecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel {
        FizzBuzzFirstViewModel(uiMapper = get())
    }
    viewModel {
        FizzBuzzSecondViewModel(getFizzBuzzUseCase = get(), uiMapper = get())
    }
    single { FizzBuzzUiMapper() }
}