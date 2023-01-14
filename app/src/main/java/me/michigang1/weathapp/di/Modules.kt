package me.michigang1.weathapp.di

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import me.michigang1.weathapp.location.LocationRepository
import me.michigang1.weathapp.location.LocationRepositoryImpl
import me.michigang1.weathapp.ui.main.MainFragment
import me.michigang1.weathapp.ui.main.MainViewModelFactory
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
    fun provideMainViewModelFactory(locationRepo: LocationRepository) =
        MainViewModelFactory(locationRepo)
}

@Singleton
@Component(modules = [ApplicationModule::class, LocationModule::class, ViewModelFactoryModule::class])
interface AppComponent {

    fun inject(mainFragment: MainFragment)
}
