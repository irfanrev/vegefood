package id.irfanrev.vegefood.core.data.repository

import android.util.Log
import id.irfanrev.vegefood.core.data.source.local.LocalDataSource
import id.irfanrev.vegefood.core.data.source.local.entity.MealsEntity
import id.irfanrev.vegefood.core.data.source.remote.MealsDataSource
import id.irfanrev.vegefood.core.data.source.remote.response.MealsDetail
import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel
import id.irfanrev.vegefood.core.domain.repository.MealsRepository
import id.irfanrev.vegefood.core.utils.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MealsRepositoryImpl(
    private val mealsDataSource: MealsDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : MealsRepository {
    override fun getMealsByCategory(category: String): Flow<List<Meals>> {
        return flow {
            emit(
                mealsDataSource.getMealsByCategory(
                    category = category
                ).meals.mappingToUseCaseEntity()
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getMealsDetail(id: String): Flow<MealsDetailModel> {
        return flow {
            emit(
                mealsDataSource.getMealsDetail(
                    id = id
                ).meals.mappingToDetailModel().first()
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun setFavoriteMeals(meals: Meals) {
        val mealsEntity = mapDomainToEntity(meals)
        appExecutors.diskIO().execute {
            localDataSource.insertFavoriteMeals(mealsEntity)
        }
        Log.d("MealsRepositoryImpl", "Berhasil ditambahkan ke database lokal")
    }

    override fun getMealsFavorite(): Flow<List<Meals>> {
        return localDataSource.getFavoriteMeals().map {
            it.mapEntityToDomain()
        }
    }

    override fun getMealsById(idMeal: String): Flow<Meals> {
        return localDataSource.getMealsById(idMeal).map {
            it.mapEntityToDomainObject()
        }
    }

    override suspend fun deleteFavoriteMeals(idMeal: String) {
        localDataSource.deleteFavoriteMeals(idMeal)
        Log.d("MealsRepositoryImpl", "Berhasil dihapus dari database lokal")
    }
}