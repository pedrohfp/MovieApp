package br.com.movieapp.presentation.details.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.movieapp.R
import br.com.movieapp.domain.model.Genres
import kotlinx.android.synthetic.main.chips_item_list.*

/**
 * Created by pedrohenrique on 26/09/17.
 */
class GenreChipsAdapter(val context:Context): RecyclerView.Adapter<GenreChipsAdapter.ViewHolder>(){

    lateinit var genreList: ArrayList<Genres>

    fun setGenres(genre: ArrayList<Genres>){
        genreList = genre
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindItems(genreList[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.chips_item_list, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
        fun bindItems(genre: Genres, context: Context){
            val chipTextView = itemView.findViewById<TextView>(R.id.chipTextView)
            chipTextView.text = genre.name
        }
    }
}