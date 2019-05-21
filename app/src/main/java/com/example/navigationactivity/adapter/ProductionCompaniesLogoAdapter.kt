package com.example.navigationactivity.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationactivity.BuildConfig.URL_POSTER
import com.example.navigationactivity.R
import com.example.navigationactivity.model.ProductionCompanies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.production_logo.view.*

class ProductionCompaniesLogoAdapter(private val companies: ArrayList<ProductionCompanies> = ArrayList()) : RecyclerView.Adapter<ProductionCompaniesLogoAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionCompaniesLogoAdapter.ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.production_logo, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCompany(companies[position])
    }

    override fun getItemCount() = companies.size

    fun setCompanies(data: List<ProductionCompanies>) {
        companies.clear()
        companies.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindCompany(company: ProductionCompanies) {
            with(view) {
                Picasso.get().load(URL_POSTER + company.logo).into(company_logo)
            }
        }
    }
}

