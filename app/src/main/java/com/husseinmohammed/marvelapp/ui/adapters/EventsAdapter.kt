package com.husseinmohammed.marvelapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.api.event.EventPojo
import com.husseinmohammed.marvelapp.data.pojos.api.event.EventPojo.EventsDataPojo.EventItemPojo
import com.husseinmohammed.marvelapp.databinding.ItemListBinding
import com.husseinmohammed.marvelapp.utils.ImageType

class EventsAdapter(var context: Context, var events: EventPojo) :
    RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        context = parent.context
        val view = ItemListBinding.inflate(LayoutInflater.from(context), parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(events.data.results[position])

    override fun getItemCount(): Int = events.data.results.size

    inner class EventViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mEventPojo: EventItemPojo) {
            binding.tvItemName.text = mEventPojo.title
            val image =
                "${mEventPojo.thumbnail?.path}${ImageType.IMAGE_TYPE}${mEventPojo.thumbnail?.extension}"

            Glide.with(context)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivItemImage)
        }
    }
}