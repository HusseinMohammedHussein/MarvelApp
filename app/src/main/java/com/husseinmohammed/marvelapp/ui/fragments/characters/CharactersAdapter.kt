package com.husseinmohammed.marvelapp.ui.fragments.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.CharacterPojo.CharacterDataPojo.CharacterItemPojo
import com.husseinmohammed.marvelapp.databinding.ItemCharacterBinding
import timber.log.Timber


// Created by Hussein Mohammed on 9/13/2021.
class CharactersAdapter(
    val context: Context,
    private val characters: ArrayList<CharacterItemPojo>
) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
//        context = parent.context
        val view = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(characters[position])

    override fun getItemCount(): Int = characters.size

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterPojo: CharacterItemPojo) {
            binding.tvCharacterName.text = characterPojo.name
            Timber.d("CharacterName::${characterPojo.name}")
            val image = "${characterPojo.thumbnail.path}.${characterPojo.thumbnail.extension}"
            Timber.d("CharacterImage::$image")
            Glide.with(context)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivCharacter)
        }
    }
}