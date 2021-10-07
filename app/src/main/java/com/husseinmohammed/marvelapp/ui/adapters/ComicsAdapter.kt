package com.husseinmohammed.marvelapp.ui.adapters;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.api.comic.ComicPojo
import com.husseinmohammed.marvelapp.data.pojos.api.comic.ComicPojo.ComicItemsPojo
import com.husseinmohammed.marvelapp.databinding.ItemListBinding
import com.husseinmohammed.marvelapp.utils.ImageType

class ComicsAdapter(
    var context: Context,
    private val comics: ComicPojo
) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        context = parent.context
        val view = ItemListBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) =
        holder.bind(comics.data.results[position])

    override fun getItemCount(): Int = comics.data.results.size

    inner class ComicsViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mComicPojo: ComicItemsPojo) {
            binding.tvItemName.text = mComicPojo.title
            val image =
                "${mComicPojo.thumbnail?.path}${ImageType.IMAGE_TYPE}${mComicPojo.thumbnail?.extension}"
            Glide.with(context)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(binding.ivItemImage)
        }
    }
}