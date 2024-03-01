package com.example.superpoderes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.superpoderes.ui.navigation.NavigationGraph
import com.example.superpoderes.ui.superherodetail.SuperheroDetailViewModel
import com.example.superpoderes.ui.superherolist.SuperHeroListViewModel
import com.example.superpoderes.ui.theme.PracticaSuperPoderesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val superHeroListViewModel: SuperHeroListViewModel by viewModels()
    private val superHeroDetailViewModel: SuperheroDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticaSuperPoderesTheme {
                NavigationGraph(superHeroListViewModel, superHeroDetailViewModel)
            }
        }
    }
}