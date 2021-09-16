package com.husseinmohammed.marvelapp.ui.fragments.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.pojos.character.CharacterItemPojo
import com.husseinmohammed.marvelapp.databinding.ItemCharacterListBinding
import timber.log.Timber

// Created by Hussein Mohammed on 9/13/2021.
class CharactersAdapter(
    var context: Context,
    private val characters: ArrayList<CharacterItemPojo>
) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {


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

        fun bind(mCharacterPojo: CharacterItemPojo) {
            binding.tvCharacterName.text = mCharacterPojo.name
            Timber.d("CharacterName::${mCharacterPojo.name}")
            val image = "${mCharacterPojo.thumbnail.path}.${mCharacterPojo.thumbnail.extension}"
            Timber.d("CharacterImage::$image")
            Glide.with(context)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivCharacter)

            binding.cvCharacter.setOnClickListener {
                it.findNavController().navigate(R.id.action_charactersFragment_to_characterDetailsFragment)
            }
        }
    }
}