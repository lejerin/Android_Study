package lej.modern.mvvm_recyclerview

class MoviesRepository (
    private val api: MoviesApi
) : SafeApiRequest(){

    suspend fun getMovies() = apiRequest { api.getMovies() }
}