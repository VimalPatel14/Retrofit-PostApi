package com.vimal.retrofitpostapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vimal.retrofitpostapi.R
import com.vimal.retrofitpostapi.interfaces.ItemClickListener
import com.vimal.retrofitpostapi.model.PostResult

class PostAdapter(val context: Context, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var movieList = mutableListOf<PostResult>()

    fun setMovies(movies: List<PostResult>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_movie, parent, false)
        return ViewHolder(inflater)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val mainlay: CardView = itemView.findViewById(R.id.mainlay)
        val name: TextView = itemView.findViewById(R.id.name)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movieList[position].let {
//            if (ValidationUtil.validateMovie(it)) {
            holder.name.text = it.title
            Glide.with(holder.itemView.context).load(it.image)
                .placeholder(R.drawable.ic_launcher_background).into(holder.imageView)
            holder.mainlay.setOnClickListener() {
                var pos = position
                pos++
                itemClickListener.onItemClick(pos)
            }
//            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}
