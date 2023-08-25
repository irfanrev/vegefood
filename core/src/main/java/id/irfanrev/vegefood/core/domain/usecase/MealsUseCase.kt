package id.irfanrev.vegefood.core.domain.usecase

import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel
import id.irfanrev.vegefood.core.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class MealsUseCase(
    private val mealsRepository: MealsRepository
) {
    operator fun invoke(category: String): Flow<List<Meals>> {
        return mealsRepository.getMealsByCategory(
            category = category
        )
    }

    fun getMealsDetail(id: String): Flow<MealsDetailModel> {
        return mealsRepository.getMealsDetail(
            id = id
        )
    }

    fun setFavoriteMeals(meals: Meals) {
        mealsRepository.setFavoriteMeals(meals)
    }

    fun getMealsFavorite(): Flow<List<Meals>> {
        return mealsRepository.getMealsFavorite()
    }

    fun getMealsById(idMeal: String): Flow<Meals> {
        return mealsRepository.getMealsById(idMeal)
    }

    suspend fun deleteFavoriteMeals(idMeal: String) {
        mealsRepository.deleteFavoriteMeals(idMeal)
    }

    fun searchMeals(search: String): Flow<List<MealsDetailModel>> {
        return mealsRepository.searchMeals(search)
    }
}