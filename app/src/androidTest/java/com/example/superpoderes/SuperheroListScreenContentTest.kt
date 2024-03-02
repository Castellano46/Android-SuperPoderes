package com.example.superpoderes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.superpoderes.domain.model.UIHero
import com.example.superpoderes.ui.superherolist.SuperHeroListScreenContent
import org.junit.Rule
import org.junit.Test

class SuperheroListScreenContentTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun given_hero_list_the_hero_is_correctly_displayed(){
        val hero = UIHero("", "", "", "", false)
        composeRule.setContent {
            SuperHeroListScreenContent(
                listOf(hero),
                onSuperHeroListClicked = {},
                onSuperHeroFavClicked = {})
        }

        composeRule.onNodeWithText(hero.name).assertExists()
        composeRule.onNodeWithContentDescription("${hero.name} Is not Favorite").assertIsDisplayed()
    }
}