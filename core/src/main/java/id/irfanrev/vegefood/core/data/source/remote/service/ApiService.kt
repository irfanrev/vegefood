package id.irfanrev.vegefood.core.data.source.remote.service

import id.irfanrev.vegefood.core.data.source.remote.response.MealsDetailResponse
import id.irfanrev.vegefood.core.data.source.remote.response.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): MealsResponse

    @GET("lookup.php")
    suspend fun getMealsDetail(
        @Query("i") id: String
    ): MealsDetailResponse
}