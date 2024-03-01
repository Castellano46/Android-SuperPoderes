package com.example.superpoderes.ui.superherodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superpoderes.data.Repository
import com.example.superpoderes.domain.model.Serie
import com.example.superpoderes.domain.model.UIHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SuperheroDetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val hero = UIHero("1","","", "", false)

    private val _state = MutableStateFlow(hero)
    val state: StateFlow<UIHero> get() = _state

    private val _stateSeries = MutableStateFlow<List<Serie>>(emptyList())
    val stateSeries: StateFlow<List<Serie>> get() = _stateSeries

    private val _stateComics = MutableStateFlow<List<Serie>>(emptyList())
    val stateComics: StateFlow<List<Serie>> get() = _stateComics

    fun getSuperhero(heroID: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getHero(heroID)
            }

            _state.update { result }
        }
    }
    fun getSeries(heroID: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getSeries(heroID)
            }

            _stateSeries.update { result }
        }
    }
    fun getComics(heroID: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.getComics(heroID)
            }

            _stateComics.update { result }
        }
    }
    fun updateFavSuperhero(hero: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.updateHero(hero)
                repository.getHero(hero)
            }
            _state.update { result }
        }
    }
}