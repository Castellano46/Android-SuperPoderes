package com.example.superpoderes.ui.superherolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.superpoderes.domain.model.UIHero
import com.example.superpoderes.ui.superherodetail.MyCircularProgressIndicator
import com.example.superpoderes.ui.superherodetail.MyFavIcon

@Composable
fun SuperHeroListScreen(viewModel: SuperHeroListViewModel, onHeroClick: (String)-> Unit = { _->}) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getSuperheros()
    }

    fun onSuperHeroFavClicked(heroID: String) {
        viewModel.updateFavSuperhero(heroID)
    }

    SuperHeroListScreenContent(state, onSuperHeroListClicked= onHeroClick) { hero ->
        onSuperHeroFavClicked(hero)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroListScreenContent(heroes: List<UIHero>,
                               onSuperHeroListClicked: (String) -> Unit,
                               onSuperHeroFavClicked: (String) -> Unit) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBar()
        }

    ) { it ->
        LazyColumn(Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = it) {
            items(heroes,
                key = { it.id }) { hero ->
                SuperheroItem(hero = hero,
                    onHeroClick = onSuperHeroListClicked,
                    onFavClick = onSuperHeroFavClicked)
            }
        }
    }
}

@Composable
fun BottomBarItem(text: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon,
            contentDescription = icon.name)
        Text(text = text)
    }
}

@Preview
@Composable
fun BottomBarItem_Preview() {
    BottomBarItem("Home", Icons.Default.Home)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    CenterAlignedTopAppBar(title = {
        Text(text = "Marvel Heroes")
    })
}

@Preview
@Composable
fun MyTopBar_Preview() {
    MyTopBar()
}

@Composable
fun SuperheroItem(hero: UIHero, modifier: Modifier = Modifier, onHeroClick: (String) -> Unit, onFavClick:(String)-> Unit) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable { onHeroClick(hero.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = CardDefaults.elevatedShape

    ) {
        SubcomposeAsyncImage(
            model = hero.thumbnail,
            contentDescription = "${hero.name} photo",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop,
            loading = {
                MyCircularProgressIndicator()
            }
        )
        Text(text = hero.name, style = MaterialTheme.typography.headlineLarge, modifier = Modifier
            .padding(8.dp))

        MyFavIcon(name = hero.name,
            isFav = hero.favorite,
            modifier = modifier
                .clickable { onFavClick(hero.id) }
        )
    }
}

@Preview
@Composable
fun SuperheroItem_Preview() {
    SuperheroItem(UIHero("1", "Name", "", thumbnail = "",false),
        modifier = Modifier,
        onHeroClick = { } ,
        onFavClick = { }
    )
}

@Preview(showBackground = true)
@Composable
fun SuperHeroListScreen_Preview() {
    SuperHeroListScreenContent(emptyList(),
        onSuperHeroListClicked = { },
        onSuperHeroFavClicked = { })
}
