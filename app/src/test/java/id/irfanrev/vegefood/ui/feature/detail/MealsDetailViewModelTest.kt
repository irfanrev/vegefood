package id.irfanrev.vegefood.ui.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.irfanrev.vegefood.MainDispatcherRule
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel
import id.irfanrev.vegefood.core.domain.usecase.MealsUseCase
import id.irfanrev.vegefood.ui.feature.detail.model.MealsDetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MealsDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getMealsDetail from api and return meals detail data`()  {
        val id = "1"
        val fakeMealsDetail = MealsDetailModel(
            idMeal = "1",
            strArea = "some_area",
            strCategory = "some_category",
            strIngredient1 = "some_ingredient1",
            strIngredient2 = "some_ingredient2",
            strIngredient3 = "some_ingredient3",
            strIngredient4 =  "some_ingredient4",
            strIngredient5 = "some_ingredient5",
            strInstructions = "some_instructions",
            strMeal = "some_meal",
            strMealThumb = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
            strTags = "some_tags",
            strYoutube = "some_youtube"
        )

        val mealsUseCase = Mockito.mock(MealsUseCase::class.java)
        val viewModel = MealsDetailViewModel(mealsUseCase)
        val fakeMealsDetailFlow = flowOf(fakeMealsDetail)

        Mockito.`when`(mealsUseCase.getMealsDetail(id)).thenReturn(fakeMealsDetailFlow)

        viewModel.getMealsDetail(id)

        verify(mealsUseCase).getMealsDetail(id)

        val result = viewModel.response.value

        Mockito.verify(mealsUseCase, Mockito.atLeastOnce()).getMealsDetail(id)
        Mockito.verifyNoMoreInteractions(mealsUseCase)
        Assert.assertNotNull(result)


    }

}