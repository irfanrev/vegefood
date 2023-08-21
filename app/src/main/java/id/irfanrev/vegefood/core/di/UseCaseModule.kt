package id.irfanrev.vegefood.core.di

import id.irfanrev.vegefood.core.domain.usecase.MealsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        MealsUseCase(get())
    }
}