package com.hoodad.test.ui.main.Comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoodad.test.R
import com.hoodad.test.data.models.responses.Review
import kotlinx.android.synthetic.main.comment_layout.view.*


class CommentsAdapter(private val comments: ArrayList<Review>) :
    RecyclerView.Adapter<CommentsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView
    ) {
        fun bind(comment: Review) {
            itemView.comment_content.text = comment.Comment
            itemView.username.text = comment.UserFullName
            itemView.rating.numStars = comment.Rate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.comment_layout, parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(comments[position])

    fun addData(list: List<Review>) {
        comments.addAll(list)
    }
}