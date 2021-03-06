package br.com.movieapp.presentation.home


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast

import br.com.movieapp.R
import br.com.movieapp.domain.model.Movie
import br.com.movieapp.domain.model.MovieResponse
import br.com.movieapp.presentation.application.MovieApplication
import br.com.movieapp.presentation.details.MovieDetailsActivity
import br.com.movieapp.presentation.home.adapter.MovieListAdapter
import br.com.movieapp.presentation.home.contract.MovieListView
import br.com.movieapp.presentation.home.contract.MoviePresenter
import br.com.movieapp.presentation.utils.EndlessRecyclerViewScrollListener
import br.com.movieapp.presentation.utils.RxSearch
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_movie_list.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment(), MovieListView {

    //Presenter
    private lateinit var mPresenter: MoviePresenter

    //Adapter
    private lateinit var mAdapter: MovieListAdapter

    //ScrollListener - EndlessRecyclerViewScrollListener
    private lateinit var mScrollListener: EndlessRecyclerViewScrollListener

    //Search Text from Search View
    private var mSearchText = ""


    companion object {

        val MOVIE_ID = "movie_id"

        @JvmStatic
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        mAdapter = MovieListAdapter(activity)
        mAdapter.setMovies(arrayListOf())

        mAdapter.setDetailsItemClick(object: MovieListAdapter.OnDetailsItemClickListener{
            override fun onItemClick(id: Int) {
                val bundle = Bundle()
                bundle.putInt(MOVIE_ID, id)
                val intent = Intent(activity, MovieDetailsActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        mAdapter.setShareItemClick(object: MovieListAdapter.OnShareItemClickListener{
            override fun onItemClick(movie: Movie) {
                val sendIntent = Intent()

                val url = MovieApplication.mImageBaseUrl + "/w780" + movie.posterPath


                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, url)
                sendIntent.type = "text/plain"
                startActivity(Intent.createChooser(sendIntent, getString(R.string.share)))
            }
        })

        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = layoutManager

        mScrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (mSearchText.isNotEmpty()) {
                    mPresenter.loadMovies(mSearchText, page)
                }
            }
        }

        recyclerView.addOnScrollListener(mScrollListener)

        RxSearch.instance.fromSearchView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ query ->
                    mSearchText = query

                    if (query!!.isEmpty()) {
                        mAdapter.movieList.clear()
                        mAdapter.notifyDataSetChanged()
                        mScrollListener.resetState()
                    } else {
                        mPresenter.loadMovies(query, 1)
                    }
                })
    }

    override fun setPresenter(presenter: MoviePresenter) {
        mPresenter = presenter
    }

    override fun showMovies(movieResponse: MovieResponse) {
        mScrollListener.previousTotalItemCount = movieResponse.totalPages

        if (movieResponse.page == 1) {
            mAdapter.movieList.clear()
        }

        mAdapter.movieList.addAll(movieResponse.movieList)
        mAdapter.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle?) {

        super.onSaveInstanceState(outState)

    }

}
