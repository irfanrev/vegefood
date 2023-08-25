package id.irfanrev.vegefood.ui.feature.search.model

import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel

sealed interface SearchUiState {

    object Idle: SearchUiState
    object Loading: SearchUiState
    object Empty: SearchUiState

    data class SuccessSearch(val data: List<MealsDetailModel>): SearchUiState
    data class Error(val error: String): SearchUiState
}