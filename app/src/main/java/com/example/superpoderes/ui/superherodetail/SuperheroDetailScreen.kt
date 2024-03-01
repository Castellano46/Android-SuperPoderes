package com.example.superpoderes.ui.superherodetail
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.superpoderes.domain.model.Serie
import com.example.superpoderes.domain.model.Thumbnail
import com.example.superpoderes.domain.model.UIHero

/*
Como complemento, he integrado los siguientes componentes adicionales en el código:
    - 'CircularProgressIndicator': Se utiliza para proporcionar retroalimentación visual
    al usuario durante la carga de datos.
    - 'FAB' (Botón de Acción Flotante): Implementado para agregar o eliminar elementos de
    la lista de favoritos cuando el ícono de corazón no es accesible directamente.
    - 'Elevated Card' de Material3: En lugar de la tarjeta clásica, he incorporado la tarjeta
    elevada de Material3. Esta opción ofrece propiedades adicionales y se puede configurar para
    lograr un efecto visual tipo flotante.
    - Reemplacé las imágenes asíncronas por 'SubcomposeAsyncImage': Esta alternativa permite la
    incorporación de un componente personalizado, en este caso, he agregado el
    'CircularProgressIndicator', que se muestra mientras la imagen está cargando. Este enfoque
     mejora la experiencia del usuario al proporcionar un indicador visual de progreso durante
     la carga de imágenes.
 */

@Composable
fun SuperheroDetailScreen(id: String, viewModel: SuperheroDetailViewModel, goBack:()-> Unit = {})
{
    val heroState by viewModel.state.collectAsState()
    val seriesState by viewModel.stateSeries.collectAsState()
    val comicsState by viewModel.stateComics.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getSuperhero(id)
        viewModel.getSeries(id)
        viewModel.getComics(id)
    }

    fun onSuperHeroFavClicked(heroID: String) {
        viewModel.updateFavSuperhero(heroID)
    }

    SuperHeroDetailScreenContent(heroState, seriesState, comicsState, goBack = goBack){
        onSuperHeroFavClicked(heroID = heroState.id)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroDetailScreenContent(hero: UIHero, series: List<Serie>, comics:List<Serie>, goBack:() -> Unit, onSuperHeroFavClicked: (String) -> Unit) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopBarDetail(hero.name){
                goBack()
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    if(hero.favorite){
                        Text("👎🏼 💔")
                    }
                    else{
                        Text("👍🏼 ❤️")
                    }
                },
                onClick = {
                    onSuperHeroFavClicked(hero.id)
                },
                backgroundColor = Color.White
            )
        }
    ) { contentPadding ->
        HeroDetail(hero, series, comics, modifier = Modifier
            .padding(contentPadding)){
            onSuperHeroFavClicked(hero.id)
        }
    }
}

@Composable
fun HeroDetail(hero: UIHero, series: List<Serie>, comics: List<Serie>, modifier: Modifier, onSuperHeroFavClicked: (String) -> Unit){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding()) {

        item{
            HeroInfo(hero = hero){
                onSuperHeroFavClicked(hero.id)
            }
        }

        item{
            MyTitle("Series", series.size)
        }

        item{
            SeriesList(series)
        }

        item{
            MyTitle(title = "Comics", size = comics.size)
        }

        item{
            SeriesList(comics)
        }
    }
}

@Composable
fun HeroInfo(hero: UIHero, onSuperHeroFavClicked: (String) -> Unit){
    Column(
        modifier = Modifier
            .padding()
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        AsyncImage(
            model = hero.thumbnail,
            contentDescription = "${hero.name} photo",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = hero.name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(8.dp))

        MyFavIcon(
            name = hero.name,
            isFav = hero.favorite,
            modifier= Modifier
                .clickable { onSuperHeroFavClicked(hero.id) })

        Text(
            text = hero.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(8.dp))
    }
}

@Composable
fun SeriesList(series: List<Serie>){

    if(series.isNotEmpty()){
        LazyRow(
            Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(series,
                key = { it.id }) { serie ->
                SerieItem(serie = serie)
            }
        }
    }
    else{
        MyCircularProgressIndicator()
    }
}

@Composable
fun MyCircularProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.size(size = 50.dp),
        color = Color.DarkGray,
        strokeWidth = 6.dp
    )
}
@Composable
fun SerieItem(serie: Serie, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .width(200.dp)
            .height(300.dp),

        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = CardDefaults.elevatedShape

    ) {
        SubcomposeAsyncImage(
            model = serie.thumbnail.path + "."+ serie.thumbnail.extension,
            contentDescription = "${serie.title} photo",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop,
            loading = {
                MyCircularProgressIndicator()
            },
        )
        Text(
            text = serie.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(10.dp))
    }
}

@Composable
fun MyTopBarDetail(name: String, goBack:() -> Unit = {}){
    TopAppBar(
        title = {
            androidx.compose.material.Text(text = name)
        },
        navigationIcon = {
            IconButton(
                onClick = { goBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
        }
    )
}

@Preview
@Composable
fun MyTopBarDetail_Preview() {
    MyTopBarDetail("Hero Name") { }
}

@Preview
@Composable
fun MySerieItem_Preview() {
    SerieItem(serie = Serie("1", "Serie Title", Thumbnail("","")))
}

@Composable
fun MyFavIcon(name:String, isFav: Boolean, modifier: Modifier){
    if(isFav){
        androidx.compose.material3.Icon(
            Icons.Rounded.Favorite,
            contentDescription = "$name Is Favorite",
            modifier
                .padding(8.dp),
            tint = Color.Red
        )
    }
    else {
        androidx.compose.material3.Icon(
            Icons.Rounded.FavoriteBorder,
            contentDescription = "$name Is not Favorite",
            modifier
                .padding(8.dp),
            tint = Color.Red
        )
    }
}

@Composable
fun MyTitle(title: String, size: Int){
    Text(text = "$title ($size)",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .padding(8.dp))
}