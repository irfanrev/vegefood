package id.irfanrev.vegefood.core.data.source.local

import android.util.Log
import id.irfanrev.vegefood.core.data.source.local.entity.MealsEntity
import id.irfanrev.vegefood.core.data.source.local.room.MealsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mealsDao: MealsDao) {

    fun getFavoriteMeals() : Flow<List<MealsEntity>> = mealsDao.getAllMeals()

    fun insertFavoriteMeals(meals: MealsEntity) {
        mealsDao.updateFavoriteMeals(meals)
        Log.i("LocalDataSource", "Berhasil ditambahkan ke database lokal")
    }

    fun getMealsById(idMeal: String) : Flow<MealsEntity> = mealsDao.getMealsById(idMeal)

    suspend fun deleteFavoriteMeals(idMeal: String) {
        mealsDao.removeFavoriteMeal(idMeal)
        Log.i("LocalDataSource", "Berhasil dihapus dari database lokal")
    }

}