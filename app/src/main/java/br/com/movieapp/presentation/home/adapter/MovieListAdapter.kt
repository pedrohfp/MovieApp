package br.com.movieapp.presentation.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import br.com.movieapp.R
import br.com.movieapp.domain.model.Movie
import com.squareup.picasso.Picasso

/**
 * Created by pedrohenrique on 26/09/17.
 */
class MovieListAdapter(private val context: Context): RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){

    interface OnDetailsItemClickListener{
        fun onItemClick(id: Int)
    }

    interface OnShareItemClickListener{
        fun onItemClick(movie: Movie)
    }

    lateinit var movieList: ArrayList<Movie>
    lateinit var detailsListener: OnDetailsItemClickListener
    lateinit var shareListener: OnShareItemClickListener

    fun setMovies(movies: ArrayList<Movie>){
        movieList = movies
    }

    fun setDetailsItemClick(listener: OnDetailsItemClickListener){
        this.detailsListener = listener
    }

    fun setShareItemClick(listener: OnShareItemClickListener){
        this.shareListener = listener
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.bindItems(movieList[position], context, detailsListener, shareListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieListAdapter.ViewHolder{
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item_list, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(movie: Movie, context: Context, detailsListener: OnDetailsItemClickListener, shareListener: OnShareItemClickListener){
            val movieImageView = itemView.findViewById<ImageView>(R.id.movieImageView)
            val movieTitleTextView = itemView.findViewById<TextView>(R.id.movieTitleTextView)
            val movieDescriptionTextView = itemView.findViewById<TextView>(R.id.movieDescriptionTextView)
            val btnDetails = itemView.findViewById<Button>(R.id.btnDetails)
            val btnShare = itemView.findViewById<Button>(R.id.btnShare)

            val url = "https://image.tmdb.org/t/p/w780" + movie.posterPath
            Picasso.with(context).load(url).into(movieImageView)
            movieTitleTextView.text = movie.title
            movieDescriptionTextView.text = movie.description
            btnDetails.setOnClickListener({
                detailsListener.onItemClick(movie.id)
            })
            btnShare.setOnClickListener({
                shareListener.onItemClick(movie)
            })
        }
    }
}