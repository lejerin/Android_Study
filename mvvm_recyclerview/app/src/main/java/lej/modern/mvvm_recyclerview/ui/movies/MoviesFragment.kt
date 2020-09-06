package lej.modern.mvvm_recyclerview.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.movies_fragment.*
import lej.modern.mvvm_recyclerview.R
import lej.modern.mvvm_recyclerview.data.network.MoviesApi
import lej.modern.mvvm_recyclerview.data.repositories.MoviesRepository

class MoviesFragment : Fragment() {

    private lateinit var factory: MoviesViewModelFactory
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = MoviesApi()
        val repository =
            MoviesRepository(api)
        factory =
            MoviesViewModelFactory(
                repository
            )
        viewModel = ViewModelProvider(this, factory).get(MoviesViewModel::class.java)
        viewModel.getMovies()
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recycler_view_movies.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter =
                    MoviesAdapter(movies)

            }
        })
    }



}