package me.michigang1.weathapp.di

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import me.michigang1.weathapp.location.LocationRepository
import me.michigang1.weathapp.location.impl.LocationRepositoryImpl
import me.michigang1.weathapp.network.NetworkRepository
import me.michigang1.weathapp.network.controller.WeatherController
import me.michigang1.weathapp.network.impl.NetworkRepositoryImpl
import me.michigang1.weathapp.ui.main.MainFragment
import me.michigang1.weathapp.ui.main.MainViewModelFactory
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: Application) {

    @Provides
    fun providesApplication(): Application = app

    @Provides
    fun providesContext(): Context = app
}

@Module
class LocationModule {

    @Provides
    @Singleton
    fun provideLocationRepository(context: Context): LocationRepository =
        LocationRepositoryImpl(context)
}

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun provideMainViewModelFactory(locationRepo: LocationRepository, networkRepository: NetworkRepository) =
        MainViewModelFactory(locationRepo, networkRepository)
}

@Module
class NetworkModule {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherController = retrofit.create(WeatherController::class.java)

    @Provides
    @Singleton
    fun provideNetworkRepo(weatherApi: WeatherController): NetworkRepository = NetworkRepositoryImpl(weatherApi)

    @Singleton
    @Component(
        modules = [
            ApplicationModule::class,
            LocationModule::class,
            ViewModelFactoryModule::class,
            NetworkModule::class
        ]
    )
    interface AppComponent {

        fun inject(mainFragment: MainFragment)
    }
}
