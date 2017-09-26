package br.com.movieapp.presentation.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.movieapp.R
import br.com.movieapp.presentation.application.MovieApplication
import br.com.movieapp.presentation.home.contract.MainActivityView
import br.com.movieapp.presentation.home.contract.MoviePresenter
import br.com.movieapp.presentation.home.di.MovieModule
import javax.inject.Inject

class MainActivity : MainActivityView() {

    //Presenter
    lateinit var mPresenter: MoviePresenter

    //Fragment
    var mMovieListFragment: MovieListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMovieListFragment =
                supportFragmentManager.findFragmentById(R.id.content) as MovieListFragment?

        if(mMovieListFragment == null){
           //Create the fragment
           mMovieListFragment = MovieListFragment.newInstance()

           supportFragmentManager.beginTransaction()
                   .add(R.id.content, mMovieListFragment)
                   .commit()
        }

        //Create the presenter:
        MovieApplication.mAppComponent
                .plusMovie(MovieModule(this, mMovieListFragment!!))
                .inject(this)

    }

    @Inject
    override fun setPresenter(presenter: MoviePresenter) {
        mPresenter = presenter
    }
}
