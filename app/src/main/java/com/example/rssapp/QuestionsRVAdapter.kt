package com.example.rssapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class QuestionsRVAdapter (private val questions: ArrayList<Questions>): RecyclerView.Adapter<QuestionsRVAdapter.VideoViewHolder>(){
    class VideoViewHolder(item_row: View): RecyclerView.ViewHolder(item_row){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_row,
            parent,
            false
        )
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val question=questions[position]
        holder.itemView.apply{
            textView2.text= question.title.toString()

        }
    }

    override fun getItemCount() = questions.size
}