package com.example.tribial.ranking.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tribial.R

class RankingRecyclerAdapter(private val dataSet: List<RecyclerData>): RecyclerView.Adapter<RankingRecyclerAdapter.MySimpleViewHolder>() {

    class MySimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val username: TextView
        val score: TextView

        init {
            username = view.findViewById(R.id.tv_item_username)
            score = view.findViewById(R.id.tv_item_score)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySimpleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_recycler_view_item, parent, false)
        return MySimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: MySimpleViewHolder, position: Int) {
        val currentData: RecyclerData = dataSet[position]
        holder.username.text = currentData.username
        holder.score.text = currentData.score
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}