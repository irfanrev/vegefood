package id.irfanrev.vegefood.core.di

import androidx.room.Room
import id.irfanrev.vegefood.core.data.source.local.LocalDataSource
import id.irfanrev.vegefood.core.data.source.local.room.MealsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataSourceModule = module {
    factory { get<MealsDatabase>().mealsDao() }
    single { LocalDataSource(get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            MealsDatabase::class.java, "meals.db"
        ).fallbackToDestructiveMigration().build()
    }
}