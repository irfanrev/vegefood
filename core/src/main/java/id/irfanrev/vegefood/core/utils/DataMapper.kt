package id.irfanrev.vegefood.core.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import id.irfanrev.vegefood.core.data.source.local.entity.MealsEntity
import id.irfanrev.vegefood.core.data.source.remote.response.Meal
import id.irfanrev.vegefood.core.data.source.remote.response.MealsDetail
import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel

fun List<Meal>?.mappingToUseCaseEntity(): List<Meals> {
    val newList: MutableList<Meals> = mutableListOf()

    this?.forEach {
        newList.add(
            Meals(
                id = it.idMeal,
                name = it.strMeal,
                image = it.strMealThumb
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}


fun List<MealsDetail>?.mappingToDetailModel(): List<MealsDetailModel> {
    val newList: MutableList<MealsDetailModel> = mutableListOf()

    this?.forEach {
        newList.add(
            MealsDetailModel(
                idMeal = it.idMeal ?: "",
                strArea = it.strArea ?: "",
                strCategory = it.strCategory  ?: "",
                strIngredient1 = it.strIngredient1 ?: "",
                strIngredient2 = it.strIngredient2 ?: "",
                strIngredient3 = it.strIngredient3 ?: "",
                strIngredient4 = it.strIngredient4 ?: "",
                strIngredient5 = it.strIngredient5 ?: "",
                strInstructions = it.strInstructions ?: "",
                strMeal = it.strMeal ?: "",
                strMealThumb = it.strMealThumb ?: "",
                strTags = it.strTags ?: "",
                strYoutube = it.strYoutube ?: ""
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}

fun mapDomainToEntity(meals: Meals): MealsEntity {
    return MealsEntity(
        id = meals.id,
        name = meals.name,
        image = meals.image
    )
}

fun List<MealsEntity>?.mapEntityToDomain(): List<Meals> {
    val newList: MutableList<Meals> = mutableListOf()

    this?.forEach {
        newList.add(
            Meals(
                id = it.id,
                name = it.name,
                image = it.image
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}

fun MealsEntity.mapEntityToDomainObject(): Meals {
    return Meals(
        id = id,
        name = name,
        image = image
    )
}
