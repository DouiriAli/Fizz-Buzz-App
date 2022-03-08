package com.rss.fizzbuzzapp

import android.app.Application
import com.rss.domain.di.domainModule
import com.rss.fizzbuzzapp.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FizzBuzzApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidLogger(Level.NONE)
            androidContext(applicationContext)
            modules(
                uiModule,
                domainModule,
            )
        }
    }
}