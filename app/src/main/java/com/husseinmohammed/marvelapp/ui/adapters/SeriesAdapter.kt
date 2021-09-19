package com.husseinmohammed.marvelapp.ui.adapters;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.series.SeriesPojo
import com.husseinmohammed.marvelapp.data.pojos.series.SeriesPojo.SeriesDataPojo.SeriesItemPojo
import com.husseinmohammed.marvelapp.databinding.ItemListBinding
import com.husseinmohammed.marvelapp.utils.ImageType

class SeriesAdapter(
    var context: Context,
    private val mSeries: SeriesPojo
) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        context = parent.context
        val view = ItemListBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) =
        holder.bind(mSeries.data.results[position])

    override fun getItemCount(): Int = mSeries.data.results.size

    inner class SeriesViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mSeriesPojo: SeriesItemPojo) {
            binding.tvItemName.text = mSeriesPojo.title
            val image =
                "${mSeriesPojo.thumbnail?.path}${ImageType.IMAGE_TYPE}${mSeriesPojo.thumbnail?.extension}"

            Glide.with(context)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(binding.ivItemImage)
        }
    }
}