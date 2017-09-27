package br.com.movieapp.presentation.details.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.movieapp.R
import br.com.movieapp.domain.model.Genres
import br.com.movieapp.domain.model.ProductionCompanies

/**
 * Created by pedrohenrique on 26/09/17.
 */
class CompaniesChipsAdapter(val context: Context): RecyclerView.Adapter<CompaniesChipsAdapter.ViewHolder>(){

    lateinit var companyList: ArrayList<ProductionCompanies>

    fun setCompany(genre: ArrayList<ProductionCompanies>){
        companyList = genre
    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bindItems(companyList[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.chips_item_list, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
        fun bindItems(company: ProductionCompanies, context: Context){
            val chipTextView = itemView.findViewById<TextView>(R.id.chipTextView)
            chipTextView.text = company.name
        }
    }
}