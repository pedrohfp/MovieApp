package br.com.movieapp.presentation.home.adapter

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.movieapp.R
import br.com.movieapp.data.rest.Authenticator
import br.com.movieapp.domain.model.Movie
import br.com.movieapp.presentation.application.MovieApplication
import com.squareup.picasso.Picasso

/**
 * Created by pedrohenrique on 26/09/17.
 */
class MovieListAdapter(val context: Context): RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){

    lateinit var movieList: ArrayList<Movie>

    fun setMovies(movies: ArrayList<Movie>){
        movieList = movies
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.bindItems(movieList[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieListAdapter.ViewHolder{
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item_list, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(movie: Movie, context: Context){
            val movieImageView = itemView.findViewById<ImageView>(R.id.movieImageView)
            val movieTitleTextView = itemView.findViewById<TextView>(R.id.movieTitleTextView)
            val movieDescriptionTextView = itemView.findViewById<TextView>(R.id.movieDescriptionTextView)

            val url = "https://image.tmdb.org/t/p/w500" + movie.posterPath
            Picasso.with(context).load(url).into(movieImageView)
            movieTitleTextView.text = movie.title
            movieDescriptionTextView.text = movie.description
        }
    }
}