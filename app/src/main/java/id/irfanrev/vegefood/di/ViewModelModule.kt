package id.irfanrev.vegefood.di

import id.irfanrev.vegefood.ui.feature.detail.MealsDetailViewModel
import id.irfanrev.vegefood.ui.feature.home.HomeViewModel
import id.irfanrev.vegefood.ui.feature.search.SearchViewModel
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
        SearchViewModel(get())
    }
}