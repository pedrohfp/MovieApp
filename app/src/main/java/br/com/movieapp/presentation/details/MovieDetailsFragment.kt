package br.com.movieapp.presentation.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.movieapp.R
import br.com.movieapp.presentation.details.contract.MovieDetailsPresenter
import br.com.movieapp.presentation.details.contract.MovieDetailsView
import br.com.movieapp.presentation.home.contract.MoviePresenter

class MovieDetailsFragment : Fragment(), MovieDetailsView {

    //Presenter
    lateinit var mPresenter: MovieDetailsPresenter

    companion object {
        @JvmStatic
        fun newInstance(): MovieDetailsFragment{
            return MovieDetailsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun setPresenter(presenter: MovieDetailsPresenter) {
        mPresenter = presenter
    }
}
