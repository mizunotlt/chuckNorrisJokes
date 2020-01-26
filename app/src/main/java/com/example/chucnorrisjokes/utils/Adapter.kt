package com.example.chucnorrisjokes.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chucnorrisjokes.R


class Adapter(private val elem: ArrayList<String>): RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(elem[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems( joke: String) {
            val textView = itemView.findViewById<TextView>(R.id.textViewItem)
            textView.text = joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(textView)
    }

    override fun getItemCount(): Int = elem.size
}
