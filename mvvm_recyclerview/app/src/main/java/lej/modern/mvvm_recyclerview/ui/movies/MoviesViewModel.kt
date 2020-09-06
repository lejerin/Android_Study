package lej.modern.mvvm_recyclerview.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import lej.modern.mvvm_recyclerview.util.Coroutines
import lej.modern.mvvm_recyclerview.data.models.Movie
import lej.modern.mvvm_recyclerview.data.repositories.MoviesRepository

class MoviesViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _movies = MutableLiveData<List<Movie>>()
    val movies : LiveData<List<Movie>>
        get() = _movies

    fun getMovies(){
        job = Coroutines.ioThenMain(
            { repository.getMovies() },
            {
                _movies.value = it
            }
        )

    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}