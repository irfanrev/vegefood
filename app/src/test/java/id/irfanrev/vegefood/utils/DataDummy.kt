package id.irfanrev.vegefood.utils

import id.irfanrev.vegefood.core.domain.model.Meals

object DataDummy {

    fun generateDummyMealsEntity(): List<Meals> {

        val mealsList = ArrayList<Meals>()

        for (i in 0..10) {
            val meals = Meals(
                "$i",
                "title $i",
                "https://picsum.photos/200/300",
            )
            mealsList.add(meals)
        }

        return mealsList

    }
}