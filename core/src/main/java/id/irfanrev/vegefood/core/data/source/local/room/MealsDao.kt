package id.irfanrev.vegefood.core.data.source.local.room

import androidx.room.*
import id.irfanrev.vegefood.core.data.source.local.entity.MealsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealsDao {

    @Query("SELECT * FROM meals")
    fun getAllMeals(): Flow<List<MealsEntity>>

    @Query("SELECT * FROM meals WHERE idMeal = :idMeal")
    fun getMealsById(idMeal: String): Flow<MealsEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun updateFavoriteMeals(meals: MealsEntity)

    @Query("DELETE FROM meals WHERE idMeal = :idMeal")
    suspend fun removeFavoriteMeal(idMeal: String)

}