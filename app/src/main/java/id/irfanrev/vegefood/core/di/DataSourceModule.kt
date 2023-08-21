package id.irfanrev.vegefood.core.di

import id.irfanrev.vegefood.core.data.source.remote.MealsDataSource
import id.irfanrev.vegefood.core.data.source.remote.MealsDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<MealsDataSource> {
        MealsDataSourceImpl(get())
    }
}