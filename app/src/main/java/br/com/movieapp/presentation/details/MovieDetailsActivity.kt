package br.com.movieapp.presentation.details

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout

import br.com.movieapp.R
import br.com.movieapp.domain.model.MovieDetail
import br.com.movieapp.presentation.application.MovieApplication
import br.com.movieapp.presentation.details.adapter.CompaniesChipsAdapter
import br.com.movieapp.presentation.details.adapter.GenreChipsAdapter
import br.com.movieapp.presentation.details.contract.MovieDetailsActivityView
import br.com.movieapp.presentation.details.contract.MovieDetailsPresenter
import br.com.movieapp.presentation.details.di.MovieDetailsModule
import br.com.movieapp.presentation.home.MovieListFragment
import br.com.movieapp.presentation.home.adapter.MovieListAdapter
import br.com.movieapp.presentation.home.di.MovieModule
import br.com.movieapp.presentation.utils.NumberUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.movie_item_list.*
import javax.inject.Inject

class MovieDetailsActivity : MovieDetailsActivityView() {

    //Presenter
    lateinit var mPresenter: MovieDetailsPresenter

    //Adapter
    private lateinit var mAdapter: GenreChipsAdapter
    private lateinit var mCompanyAdapter: CompaniesChipsAdapter

    //Homesite
    lateinit var mSite: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.extras.getInt(MovieListFragment.MOVIE_ID)

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)

        val layoutManagerCompany = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)

        mAdapter = GenreChipsAdapter(this)
        mAdapter.setGenres(arrayListOf())

        genresRecyclerView.adapter = mAdapter
        genresRecyclerView.layoutManager = layoutManager

        mCompanyAdapter = CompaniesChipsAdapter(this)
        mCompanyAdapter.setCompany(arrayListOf())

        companiesRecyclerView.adapter = mCompanyAdapter
        companiesRecyclerView.layoutManager = layoutManagerCompany

        //Create the presenter:
        MovieApplication.mAppComponent
                .plusDetailsMovie(MovieDetailsModule(this))
                .inject(this)

        mPresenter.loadMovieDetails(movieId)

        detailsFrameLayout.visibility = View.GONE
        progressFrameLayout.visibility = View.VISIBLE

        btnOpenBrowser.setOnClickListener({
            //Open the browser
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(mSite)
            startActivity(intent)
        })
    }

    @Inject
    override fun setPresenter(presenter: MovieDetailsPresenter) {
        mPresenter = presenter
    }

    override fun showMovieDetails(details: MovieDetail) {
        val url = MovieApplication.mImageBaseUrl + "/w780" + details.backdropPath
        Picasso.with(this).load(url).into(detailsImageView)

        if(details.homepage!!.isEmpty()){
            btnOpenBrowser.visibility = View.INVISIBLE
        }else{
            mSite = details.homepage!!
        }

        detailsTitleTextView.text = details.title

        val dateArray = details.releaseDate!!.split("-")

        detailsYearTextView.text = dateArray[0]
        overviewTextView.text = details.overview

        val rating = details.rating!! / 2
        detailsRatingTextView.text = NumberUtils.roundFloat(rating, 2).toString()
        detailsRatingBar.rating = rating

        if(details.genresList!!.size < 3){
            mAdapter.genreList.addAll(details.genresList!!)
        }else {
            mAdapter.genreList.addAll(details.genresList!!.subList(0, 2))
        }

        mAdapter.notifyDataSetChanged()

        if(details.productionCompanies!!.size != 0) {
            if(details.productionCompanies!!.size < 3) {
                mCompanyAdapter.companyList.addAll(details.productionCompanies!!)
            }else{
                mCompanyAdapter.companyList.addAll(details.productionCompanies!!.subList(0, 2))
            }
            mCompanyAdapter.notifyDataSetChanged()
        }else{
            productionCompaniesTitleTextView.visibility = View.GONE
            companiesRecyclerView.visibility = View.GONE
        }

        detailsFrameLayout.visibility = View.VISIBLE
        progressFrameLayout.visibility = View.GONE
    }

    override fun onDestroy() {
        mPresenter.finish()
        super.onDestroy()
    }
}
