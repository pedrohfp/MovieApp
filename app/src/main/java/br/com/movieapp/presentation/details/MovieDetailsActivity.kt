package br.com.movieapp.presentation.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import br.com.movieapp.R
import br.com.movieapp.presentation.application.MovieApplication
import br.com.movieapp.presentation.details.contract.MovieDetailsActivityView
import br.com.movieapp.presentation.details.contract.MovieDetailsPresenter
import br.com.movieapp.presentation.details.di.MovieDetailsModule
import br.com.movieapp.presentation.home.di.MovieModule
import javax.inject.Inject

class MovieDetailsActivity : MovieDetailsActivityView() {

    //Presenter
    lateinit var mPresenter: MovieDetailsPresenter

    //Fragment
    var mMovieDetailsFragment: MovieDetailsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        mMovieDetailsFragment =
                supportFragmentManager.findFragmentById(R.id.contentDetails) as MovieDetailsFragment?

        if(mMovieDetailsFragment == null){
            //Create the fragment
            mMovieDetailsFragment = MovieDetailsFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add(R.id.contentDetails, mMovieDetailsFragment)
                    .commit()
        }

        //Create the presenter:
        MovieApplication.mAppComponent
                .plusDetailsMovie(MovieDetailsModule(this, mMovieDetailsFragment!!))
                .inject(this)
    }

    @Inject
    override fun setPresenter(presenter: MovieDetailsPresenter) {
        mPresenter = presenter
    }
}
