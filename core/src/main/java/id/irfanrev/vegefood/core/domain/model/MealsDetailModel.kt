package id.irfanrev.vegefood.core.domain.model

import com.google.gson.annotations.SerializedName

data class MealsDetailModel(
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strInstructions: String,
    val strMeal: String,
    val strMealThumb: String,
    val strTags: String,
    val strYoutube: String
)