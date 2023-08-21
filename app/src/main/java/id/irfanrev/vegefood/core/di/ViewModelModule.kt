package id.irfanrev.vegefood.core.di

import id.irfanrev.vegefood.ui.feature.detail.MealsDetailViewModel
import id.irfanrev.vegefood.ui.feature.favorite.FavoriteViewModel
import id.irfanrev.vegefood.ui.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        MealsDetailViewModel(get())
    }
    viewModel {
        FavoriteViewModel(get())
    }
}