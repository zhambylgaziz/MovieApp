package com.example.navigationactivity


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.navigationactivity.adapter.PopularAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        val movies: ArrayList<String> = ArrayList()
        for(i in 1..100){
            movies.add("Post $i")
        }
        val adapter = PopularAdapter(movies)
        view.popular.adapter = adapter
        return view
    }


}
