package br.com.movieapp.presentation.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.movieapp.R
import br.com.movieapp.presentation.home.contract.MovieListView
import br.com.movieapp.presentation.home.contract.MoviePresenter


/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment(), MovieListView {

    companion object {
        @JvmStatic
        fun newInstance(): MovieListFragment{
            return MovieListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun setPresenter(presenter: MoviePresenter) {

    }

}
