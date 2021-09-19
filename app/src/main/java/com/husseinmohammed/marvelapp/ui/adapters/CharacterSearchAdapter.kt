package com.husseinmohammed.marvelapp.ui.adapters;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.character.CharacterItemPojo
import com.husseinmohammed.marvelapp.databinding.ItemCharSearchBinding
import com.husseinmohammed.marvelapp.utils.ImageType

class CharacterSearchAdapter(
    var context: Context,
    private val mCharacters: ArrayList<CharacterItemPojo>
) : RecyclerView.Adapter<CharacterSearchAdapter.CharacterSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterSearchViewHolder {
        context = parent.context
        val view = ItemCharSearchBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return CharacterSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterSearchViewHolder, position: Int) =
        holder.bind(mCharacters[position])

    override fun getItemCount(): Int = mCharacters.size

    inner class CharacterSearchViewHolder(private val binding: ItemCharSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mCharacterItem: CharacterItemPojo) {
            binding.tvCharName.text = mCharacterItem.name
            val image =
                "${mCharacterItem.thumbnail?.path}${ImageType.IMAGE_TYPE}${mCharacterItem.thumbnail?.extension}"

            Glide.with(context)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(binding.ivCharImage)
        }
    }
}