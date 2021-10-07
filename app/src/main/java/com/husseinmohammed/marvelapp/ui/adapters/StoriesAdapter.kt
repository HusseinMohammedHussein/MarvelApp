package com.husseinmohammed.marvelapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.api.story.StoryPojo
import com.husseinmohammed.marvelapp.data.pojos.api.story.StoryPojo.StoryDataPojo.StoryItemPojo
import com.husseinmohammed.marvelapp.databinding.ItemListBinding
import com.husseinmohammed.marvelapp.utils.ImageType
import timber.log.Timber

class StoriesAdapter(var context: Context, var stories: StoryPojo) :
    RecyclerView.Adapter<StoriesAdapter.StoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        context = parent.context
        val view = ItemListBinding.inflate(LayoutInflater.from(context), parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) =
        holder.bind(stories.data.results[position])

    override fun getItemCount(): Int = stories.data.results.size

    inner class StoryViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mStoryPojo: StoryItemPojo) {
            binding.tvItemName.text = mStoryPojo.title
            val image =
                "${mStoryPojo.thumbnail?.path}${ImageType.IMAGE_TYPE}${mStoryPojo.thumbnail?.extension}"

            Timber.d("StoryImage::$image")

            Glide.with(context)
                .load(image)
                .error(R.drawable.image_placeholder)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivItemImage)
        }
    }
}