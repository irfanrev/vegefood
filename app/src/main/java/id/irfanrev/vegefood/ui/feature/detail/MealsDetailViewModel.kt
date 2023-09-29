package id.irfanrev.vegefood.ui.feature.detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.core.domain.usecase.MealsUseCase
import id.irfanrev.vegefood.ui.feature.detail.model.MealsDetailUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MealsDetailViewModel(
    private val mealsUseCase: MealsUseCase
) : ViewModel() {

    var isFavorite: MutableState<Boolean> = mutableStateOf(false)
    val response: MutableState<MealsDetailUiState> = mutableStateOf(MealsDetailUiState.Empty)

    fun getMealsDetail(id: String) {
        viewModelScope.launch {
            mealsUseCase.getMealsDetail(id)
                .onStart {
                    response.value = MealsDetailUiState.Loading
                }.catch {
                    Log.d("Error in MealsDetailViewModel", it.localizedMessage ?: "Unknown Error")
                    response.value = MealsDetailUiState.Error(
                        error = it.localizedMessage ?: "Unknown Error"
                    )
                }.collect {
                    Log.d("MealsDetailViewModel", "getMealsDetail")
                    response.value = MealsDetailUiState.Success(
                        data = it
                    )
                }
        }
    }

    fun getFavoriteById(idMeals: String) {
        viewModelScope.launch {
            mealsUseCase.getMealsById(idMeals)
                .onStart {
                    isFavorite.value = false
                }.catch {
                    Log.d("Error in MealsDetailViewModel", it.localizedMessage ?: "Unknown Error")
                }.collectLatest {
                    isFavorite.value = true
            }
            Log.d("Check Favorite", "getFavoriteById")
        }
    }

    fun setFavoriteMeals(id: String, name: String, img: String) {
        viewModelScope.launch {
            val meals = Meals(
                id = id,
                name = name,
                image = img
            )
            mealsUseCase.setFavoriteMeals(meals)
            isFavorite.value = true
        }
    }

    fun deleteFavoriteMeals(id: String) {
        viewModelScope.launch {
            mealsUseCase.deleteFavoriteMeals(id)
            isFavorite.value = false
        }
    }

}