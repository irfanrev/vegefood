package id.irfanrev.vegefood

import android.app.Application
import id.irfanrev.vegefood.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    dataSourceModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    localDataSourceModule,
                )
            )
        }
    }
}