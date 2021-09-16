package com.husseinmohammed.marvelapp.ui.fragments.characterdetails.comics;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.comic.ComicPojo.ComicItemsPojo
import com.husseinmohammed.marvelapp.databinding.ItemComicListBinding

class ComicsAdapter(
    var context: Context,
    private val comics: ArrayList<ComicItemsPojo>
) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        context = parent.context
        val view = ItemComicListBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) =
        holder.bind(comics[position])

    override fun getItemCount(): Int = comics.size

    inner class ComicsViewHolder(private val binding: ItemComicListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mComicPojo: ComicItemsPojo) {
            binding.tvComicName.text = mComicPojo.title
            val image = "${mComicPojo.thumbnail.path}.${mComicPojo.thumbnail.extension}"
            Glide.with(context)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivComic)
        }
    }
}