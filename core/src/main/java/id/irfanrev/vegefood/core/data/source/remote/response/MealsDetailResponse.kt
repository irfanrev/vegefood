package id.irfanrev.vegefood.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MealsDetailResponse(
    @SerializedName("meals")
    val meals: List<MealsDetail>
)