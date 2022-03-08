package com.rss.domain.di

import com.rss.domain.usecase.GetFizzBuzzUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetFizzBuzzUseCase() }
}