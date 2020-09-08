package lej.modern.mvvm_recyclerview.ui.movies

import android.view.View
import lej.modern.mvvm_recyclerview.data.models.Movie

interface RecyclerViewClickListener {

    fun onRecyclerViewItemClick(view: View, movie: Movie)
}