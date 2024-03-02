package com.example.superpoderes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.superpoderes.domain.model.Serie
import com.example.superpoderes.domain.model.Thumbnail
import com.example.superpoderes.domain.model.UIHero
import com.example.superpoderes.ui.superherodetail.SuperHeroDetailScreenContent
import org.junit.Rule
import org.junit.Test

class SuperheroDetailScreenContentTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun given_a_not_fav_hero_its_heart_is_correctly_displayed(){
        val hero = UIHero("", "", "", "", false)
        composeRule.setContent {
            SuperHeroDetailScreenContent(hero=hero,
                listOf(Serie("", "", Thumbnail("", ""))),
                listOf(Serie("", "", Thumbnail("", ""))),
                goBack = {},
                onSuperHeroFavClicked = {})
        }

        composeRule.onNodeWithContentDescription("${hero.name} Is not Favorite").assertIsDisplayed()
    }
}