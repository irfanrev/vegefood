package id.irfanrev.vegefood.core.domain.repository

import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    fun getMealsByCategory(category: String): Flow<List<Meals>>

    fun getMealsDetail(id: String): Flow<MealsDetailModel>

    fun setFavoriteMeals(meals: Meals)

    fun getMealsFavorite(): Flow<List<Meals>>

    fun getMealsById(idMeal: String): Flow<Meals>

    suspend fun deleteFavoriteMeals(idMeal: String)

    fun searchMeals(search: String): Flow<List<MealsDetailModel>>
}