package com.ipsoft.crudapplication

import android.app.Application
import com.ipsoft.crudapplication.commons.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    appModule
                )
            )
            androidContext(this@AppApplication)
        }

    }

}