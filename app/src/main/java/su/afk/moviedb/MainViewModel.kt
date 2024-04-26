package su.afk.moviedb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import su.afk.moviedb.data.models.MoviesShow
import su.afk.moviedb.data.retrofit.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _allMovies = MutableLiveData<List<MoviesShow>>()

    val allMovies: LiveData<List<MoviesShow>>
        get() {
            return _allMovies
        }


    fun getAllMovies() {
        viewModelScope.launch {
            repository.getAllMovie().let {
                if(it.isSuccessful) {
                    _allMovies.postValue(it.body())
                } else {
                    Log.d("MyLog", "Неудалось загрузить данные ${it.errorBody()}")
                }
            }
        }
    }


}