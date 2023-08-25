package id.irfanrev.vegefood.core.data.source.remote

import id.irfanrev.vegefood.core.data.source.remote.response.MealsDetailResponse
import id.irfanrev.vegefood.core.data.source.remote.response.MealsResponse

interface MealsDataSource {
    suspend fun getMealsByCategory(category: String): MealsResponse
    suspend fun getMealsDetail(id: String): MealsDetailResponse
    suspend fun searchMeals(search: String): MealsDetailResponse
}