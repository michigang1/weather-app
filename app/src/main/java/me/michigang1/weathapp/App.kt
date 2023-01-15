package me.michigang1.weathapp

import android.app.Application

import me.michigang1.weathapp.di.ApplicationModule
import me.michigang1.weathapp.di.DaggerAppComponent
import me.michigang1.weathapp.di.NetworkModule

class App : Application() {
    val appComponent: NetworkModule.AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}
