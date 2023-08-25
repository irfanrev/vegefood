package id.irfanrev.vegefood.ui.feature.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.irfanrev.vegefood.core.domain.usecase.MealsUseCase
import id.irfanrev.vegefood.ui.feature.home.model.HomeUiState
import id.irfanrev.vegefood.ui.feature.search.model.SearchUiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val mealsUseCase: MealsUseCase
) : ViewModel() {
    val response: MutableState<SearchUiState> = mutableStateOf(SearchUiState.Empty)
    var searchValue: MutableState<String> = mutableStateOf("")

    init {
        searchMeals()
    }

    fun searchMeals() {
        Log.d("Search Value: ", searchValue.value)
        viewModelScope.launch {
            mealsUseCase.searchMeals(searchValue.value).onStart {
                response.value = SearchUiState.Loading
            }.catch {
                response.value = SearchUiState.Error(
                    error = it.localizedMessage ?: "Unknown Error"
                )
            }.collectLatest {data ->
                if (data.isEmpty()) {
                    response.value = SearchUiState.Empty
                } else {
                    response.value = SearchUiState.SuccessSearch(
                        data = data
                    )
                }
            }
        }
    }
}