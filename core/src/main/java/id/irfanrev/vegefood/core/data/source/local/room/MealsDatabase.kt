package id.irfanrev.vegefood.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.irfanrev.vegefood.core.data.source.local.entity.MealsEntity

@Database(entities = [MealsEntity::class], version = 1, exportSchema = false)
abstract class MealsDatabase : RoomDatabase() {

    abstract fun mealsDao(): MealsDao

}