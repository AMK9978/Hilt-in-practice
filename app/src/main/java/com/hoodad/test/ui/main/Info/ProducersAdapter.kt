package com.hoodad.test.ui.main.Info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoodad.test.R
import com.hoodad.test.data.models.responses.ProducerInfo
import kotlinx.android.synthetic.main.producer_layout.view.*


class ProducersAdapter(private val producersInfo: ArrayList<ProducerInfo>) :
    RecyclerView.Adapter<ProducersAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView
    ) {
        fun bind(producerInfo: ProducerInfo) {
            itemView.title.text = producerInfo.Title
            if (producerInfo.Producers.size > 0) {
                itemView.name.text = producerInfo.Producers[0].Name
                Glide.with(itemView.context).load(producerInfo.Producers[0].PhotoURL)
                    .into(itemView.photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.producer_layout, parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = producersInfo.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(producersInfo[position])

    fun addData(list: List<ProducerInfo>) {
        producersInfo.addAll(list)
    }
}
