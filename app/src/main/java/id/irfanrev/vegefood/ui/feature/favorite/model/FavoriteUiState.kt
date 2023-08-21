package id.irfanrev.vegefood.ui.feature.favorite.model

import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.ui.feature.home.model.HomeUiState

sealed interface FavoriteUiState {

    object Idle: FavoriteUiState
    object Loading: FavoriteUiState
    object Empty: FavoriteUiState

    data class Success(val data: List<Meals>): FavoriteUiState
    data class Error(val error: String): FavoriteUiState
}