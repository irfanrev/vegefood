package id.irfanrev.vegefood.ui.feature.home

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.irfanrev.vegefood.core.domain.usecase.MealsUseCase
import id.irfanrev.vegefood.ui.feature.home.model.HomeUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mealsUseCase: MealsUseCase
) : ViewModel() {

    val response: MutableState<HomeUiState> = mutableStateOf(HomeUiState.Empty)

    init {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch {
            mealsUseCase.invoke(
                category = "Vegetarian"
            ).onStart {
                response.value = HomeUiState.Loading
            }.catch {
                response.value = HomeUiState.Error(
                    error = it.localizedMessage ?: "Unknown Error"
                )
            }.collectLatest {data ->
                if (data.isEmpty()) {
                    response.value = HomeUiState.Empty
                } else {
                    response.value = HomeUiState.Success(
                       data = data
                    )
                }
            }
        }
    }

}