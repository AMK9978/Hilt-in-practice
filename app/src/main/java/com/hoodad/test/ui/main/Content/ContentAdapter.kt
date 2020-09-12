package com.hoodad.test.ui.main.Content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoodad.test.R
import com.hoodad.test.data.models.responses.Episod
import com.hoodad.test.utils.Util
import kotlinx.android.synthetic.main.episod_layout.view.*


class ContentAdapter(private val episodes: ArrayList<Episod>) :
    RecyclerView.Adapter<ContentAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView
    ) {
        fun bind(episode: Episod) {
            itemView.episode_title.text = episode.Title
            itemView.episode_info.text =
                episodeSubTitleCreator(episode.SizeDescription, episode.LengthTitle)
            Glide.with(itemView.context).load(episode.PhotoUrl).into(itemView.book_image)
            itemView.download.setOnClickListener {
                itemView.download_icon.setImageResource(R.drawable.ic_baseline_close_24)
                Util.download(itemView.context, episode.SyncUrl, episode.Size, "درحال دانلود " + episode.Title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.episod_layout, parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = episodes.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(episodes[position])

    fun addData(list: List<Episod>) {
        episodes.addAll(list)
    }
}

fun episodeSubTitleCreator(sizeDescription: String?, LengthTitle: String?): String {
    return "$sizeDescription | $LengthTitle"
}
