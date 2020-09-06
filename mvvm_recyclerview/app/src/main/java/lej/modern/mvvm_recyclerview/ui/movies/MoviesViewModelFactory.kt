package lej.modern.mvvm_recyclerview.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lej.modern.mvvm_recyclerview.data.repositories.MoviesRepository

class MoviesViewModelFactory(
    private val repository: MoviesRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(repository) as T
    }

}