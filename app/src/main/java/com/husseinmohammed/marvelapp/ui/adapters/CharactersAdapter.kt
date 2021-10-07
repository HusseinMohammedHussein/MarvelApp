package com.husseinmohammed.marvelapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.api.character.CharacterItemPojo
import com.husseinmohammed.marvelapp.data.pojos.local.CharacterLocalPojo
import com.husseinmohammed.marvelapp.databinding.ItemCharacterListBinding
import com.husseinmohammed.marvelapp.utils.ImageType
import timber.log.Timber

// Created by Hussein Mohammed on 9/13/2021.
@SuppressLint("NotifyDataSetChanged")
class CharactersAdapter(var context: Context) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    var characters: List<CharacterLocalPojo> = arrayListOf()

    fun addData(chars: List<CharacterLocalPojo>) {
        characters = chars
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        context = parent.context
        val view = ItemCharacterListBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(characters[position])

    override fun getItemCount(): Int = characters.size

    inner class CharacterViewHolder(private val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val bundle = Bundle()


        fun bind(mCharacterPojo: CharacterLocalPojo) {
            binding.tvCharacterName.text = mCharacterPojo.name
            Timber.d("CharacterName::${mCharacterPojo.name}")
//            val image =
//                "${mCharacterPojo.thumbnail?.path}${mCharacterPojo.thumbnail}"
//            Timber.d("CharacterImage::$image")
            Glide.with(context)
                .load(mCharacterPojo.thumbnail)
                .error(R.drawable.image_placeholder)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivCharacter)

            itemView.setOnClickListener {
                bundle.putParcelable(context.getString(R.string.charItem), mCharacterPojo)
                it.findNavController()
                    .navigate(R.id.action_charactersFragment_to_characterDetailsFragment, bundle)
            }
        }

    }
}