package com.hoodad.test.ui.main.Content

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoodad.test.R
import com.hoodad.test.data.models.responses.Episod
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
                download(itemView.context, episode.SyncUrl, episode.Size)
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

fun download(context: Context, url: String?, totalSize: Long?) {
    val request = DownloadManager.Request(Uri.parse(url))
    val mgr = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val id = mgr.enqueue(request)
    val q = DownloadManager.Query()
    q.setFilterById(id)
    val cursor: Cursor = mgr.query(q)
    cursor.moveToFirst()
    val bytes_downloaded: Int =
        cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
    cursor.close()
}