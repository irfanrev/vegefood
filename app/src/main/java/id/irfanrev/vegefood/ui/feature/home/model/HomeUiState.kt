package id.irfanrev.vegefood.ui.feature.home.model

import id.irfanrev.vegefood.core.domain.model.Meals

sealed interface HomeUiState {

    object Idle: HomeUiState
    object Loading: HomeUiState
    object Empty: HomeUiState

    data class Success(val data: List<Meals>): HomeUiState
    data class Error(val error: String): HomeUiState
}