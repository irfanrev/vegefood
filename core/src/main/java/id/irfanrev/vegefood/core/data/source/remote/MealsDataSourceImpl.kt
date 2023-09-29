package id.irfanrev.vegefood.core.data.source.remote

import id.irfanrev.vegefood.core.data.source.remote.response.MealsDetailResponse
import id.irfanrev.vegefood.core.data.source.remote.response.MealsResponse
import id.irfanrev.vegefood.core.data.source.remote.service.ApiService

class MealsDataSourceImpl(private val apiService: ApiService) : MealsDataSource {
    override suspend fun getMealsByCategory(category: String): MealsResponse {
        return apiService.getMealsByCategory(
            category = category
        )
    }

    override suspend fun getMealsDetail(id: String): MealsDetailResponse {
        return apiService.getMealsDetail(
            id = id
        )
    }

    override suspend fun searchMeals(search: String): MealsDetailResponse {
        return apiService.searchMeals(
            search = search
        )
    }
}