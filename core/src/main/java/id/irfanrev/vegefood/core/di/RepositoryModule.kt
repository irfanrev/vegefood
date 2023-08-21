package id.irfanrev.vegefood.core.di

import id.irfanrev.vegefood.core.data.repository.MealsRepositoryImpl
import id.irfanrev.vegefood.core.domain.repository.MealsRepository
import id.irfanrev.vegefood.core.utils.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppExecutors() }
    single<MealsRepository> {
        MealsRepositoryImpl(get(), get(), get())
    }
}