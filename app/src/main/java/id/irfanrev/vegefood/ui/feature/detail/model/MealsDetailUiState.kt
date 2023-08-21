package id.irfanrev.vegefood.ui.feature.detail.model

import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel

sealed interface MealsDetailUiState {

    object Idle: MealsDetailUiState
    object Loading: MealsDetailUiState
    object Empty: MealsDetailUiState

    data class Success(val data: MealsDetailModel): MealsDetailUiState
    data class Error(val error: String): MealsDetailUiState
}