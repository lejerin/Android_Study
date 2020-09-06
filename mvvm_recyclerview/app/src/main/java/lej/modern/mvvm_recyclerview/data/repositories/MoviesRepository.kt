package lej.modern.mvvm_recyclerview.data.repositories

import lej.modern.mvvm_recyclerview.data.network.MoviesApi

class MoviesRepository (
    private val api: MoviesApi
) : SafeApiRequest(){

    suspend fun getMovies() = apiRequest { api.getMovies() }
}