package id.irfanrev.vegefood.ui.feature.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.irfanrev.vegefood.core.domain.repository.MealsRepository
import id.irfanrev.vegefood.core.domain.usecase.MealsUseCase
import id.irfanrev.vegefood.ui.feature.home.model.HomeUiState
import id.irfanrev.vegefood.utils.DataDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest  {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get meals from api and return list of meals`() {
        val useCase = Mockito.mock(MealsUseCase::class.java)
        val flow = flowOf(DataDummy.generateDummyMealsEntity())

        Mockito.`when`(useCase.invoke("Vegetarian"))
            .thenReturn(flow)

        val viewModel = HomeViewModel(useCase)

        val result = viewModel.response.value

        Mockito.verify(useCase, Mockito.atLeastOnce()).invoke("Vegetarian")
        Mockito.verifyNoMoreInteractions(useCase)
        Assert.assertNotNull(result)
    }

}