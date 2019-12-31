package com.example.topratedmoviespersistent.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.topratedmoviespersistent.R
import com.example.topratedmoviespersistent.databinding.ItemRowBinding
import com.example.topratedmoviespersistent.listener.CustomClickListener
import com.example.topratedmoviespersistent.listener.MovieIdListener
import com.example.topratedmoviespersistent.model.Result

class MovieAdapter constructor(result: List<Result>,context :MovieIdListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(), CustomClickListener {


    override fun cardClicked(movie: Result) {

        Log.d("clicked", movie.id.toString())

        movieIdListener?.movieIdListener(movie.id)
    }

    private var listOfMovie = result
    private val movieIdListener:MovieIdListener?=context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding: ItemRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_row,
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return listOfMovie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfMovie[position])
        holder.setListener(this)
    }

    class ViewHolder(itemView: ItemRowBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val itemRowBinding = itemView
        fun bind(result: Result) {
            itemRowBinding.model = result
            itemRowBinding.executePendingBindings()

        }

        fun setListener(movieAdapter: MovieAdapter) {
            itemRowBinding.itemClickListener = movieAdapter
        }
    }
}