package com.husseinmohammed.marvelapp.ui.fragments.characterdetails.series;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.series.SeriesPojo.SeriesDataPojo.SeriesItemPojo
import com.husseinmohammed.marvelapp.databinding.ItemSeriesListBinding

class SeriesAdapter(
    var context: Context,
    private val mSeriesPojo: ArrayList<SeriesItemPojo>
) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        context = parent.context
        val view = ItemSeriesListBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) =
        holder.bind(mSeriesPojo[position])

    override fun getItemCount(): Int = mSeriesPojo.size

    inner class SeriesViewHolder(private val binding: ItemSeriesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mSeriesPojo: SeriesItemPojo) {
            binding.tvStoryName.text = mSeriesPojo.title
            val image = "${mSeriesPojo.thumbnail.path}.${mSeriesPojo.thumbnail.extension}"
            Glide.with(context)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivStory)
        }
    }
}