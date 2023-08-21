package id.irfanrev.vegefood.ui.feature.favorite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.irfanrev.vegefood.core.domain.usecase.MealsUseCase
import id.irfanrev.vegefood.ui.feature.favorite.model.FavoriteUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val mealsUseCase: MealsUseCase
) : ViewModel() {

    val response: MutableState<FavoriteUiState> = mutableStateOf(FavoriteUiState.Empty)

    init {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch {
            mealsUseCase.getMealsFavorite().onStart {
                response.value = FavoriteUiState.Loading
            }.catch {
                response.value = FavoriteUiState.Error(
                    error = it.localizedMessage ?: "Unknown Error"
                )
            }.collectLatest {data ->
                if (data.isEmpty()) {
                    response.value = FavoriteUiState.Empty
                } else {
                    response.value = FavoriteUiState.Success(
                        data = data
                    )
                }
            }
        }
    }

}