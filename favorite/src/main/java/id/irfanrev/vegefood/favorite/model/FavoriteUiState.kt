package id.irfanrev.vegefood.favorite.model

import id.irfanrev.vegefood.core.domain.model.Meals

sealed interface FavoriteUiState {

    object Loading: FavoriteUiState
    object Empty: FavoriteUiState

    data class Success(val data: List<Meals>): FavoriteUiState
    data class Error(val error: String): FavoriteUiState
}