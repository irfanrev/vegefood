package id.irfanrev.vegefood.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealsEntity(
    @PrimaryKey
    @ColumnInfo(name = "idMeal")
    val id: String,

    @ColumnInfo(name = "strMeal")
    val name: String,

    @ColumnInfo(name = "strMealThumb")
    val image: String
)
