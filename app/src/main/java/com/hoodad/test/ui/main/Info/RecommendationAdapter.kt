package com.hoodad.test.ui.main.Info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoodad.test.R
import com.hoodad.test.data.models.Book
import kotlinx.android.synthetic.main.book_layout.view.*
import kotlinx.android.synthetic.main.episod_layout.view.book_image


class RecommendationAdapter(private val books: ArrayList<Book>) :
    RecyclerView.Adapter<RecommendationAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView
    ) {
        fun bind(book: Book) {
            itemView.book_title.text = book.Title
            Glide.with(itemView.context).load(book.PhotoUrl).into(itemView.book_image)
//            itemView.download.setOnClickListener {
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.book_layout, parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(books[position])

    fun addData(list: List<Book>) {
        books.addAll(list)
    }
}
