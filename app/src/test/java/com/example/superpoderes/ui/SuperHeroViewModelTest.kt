package com.example.superpoderes.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.superpoderes.data.Repository
import com.example.superpoderes.ui.superherolist.SuperHeroListViewModel
import com.example.superpoderes.utils.generateUISuperheroes
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SuperHeroViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SuperHeroListViewModel

    private lateinit var repository: Repository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mockk()
        viewModel = SuperHeroListViewModel(repository)
    }

    @Test
    fun `WHEN getHero EXPECT successful response`()  {
        coEvery { repository.getHeroes() } returns generateUISuperheroes()

        val actual = viewModel.getSuperheros()
        val actualLiveData = viewModel.state.value

        assertEquals(actualLiveData.size, 1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}