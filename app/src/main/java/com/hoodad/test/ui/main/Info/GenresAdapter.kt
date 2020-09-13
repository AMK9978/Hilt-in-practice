package com.hoodad.test.ui.main.Info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoodad.test.R
import com.hoodad.test.data.models.responses.Genre
import kotlinx.android.synthetic.main.genre_layout.view.*


class GenresAdapter(private val genres: ArrayList<Genre>) :
    RecyclerView.Adapter<GenresAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView
    ) {
        fun bind(genre: Genre) {
            itemView.genre_title.text = genre.Title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.genre_layout, parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(genres[position])

    fun addData(list: List<Genre>) {
        genres.addAll(list)
    }
}
