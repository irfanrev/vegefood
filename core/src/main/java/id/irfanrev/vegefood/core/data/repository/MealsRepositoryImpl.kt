package id.irfanrev.vegefood.core.data.repository

import id.irfanrev.vegefood.core.data.source.local.LocalDataSource
import id.irfanrev.vegefood.core.data.source.remote.MealsDataSource
import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel
import id.irfanrev.vegefood.core.domain.repository.MealsRepository
import id.irfanrev.vegefood.core.utils.*
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
    }

    override fun searchMeals(search: String): Flow<List<MealsDetailModel>> {
        return flow {
            emit(
                mealsDataSource.searchMeals(
                    search = search
                ).meals.mappingToDetailModel()
            )
        }.flowOn(Dispatchers.IO)
    }
}