package com.husseinmohammed.marvelapp.ui.fragments.characterdetails.chardetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.databinding.FragmentCharacterDetailsBinding
import com.husseinmohammed.marvelapp.ui.fragments.characterdetails.comics.ComicsAdapter
import com.husseinmohammed.marvelapp.ui.fragments.characterdetails.series.SeriesAdapter
import timber.log.Timber

class CharacterDetailsFragment : Fragment() {
    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CharacterDetailsViewModel
    private lateinit var mComicsAdapter: ComicsAdapter
    private lateinit var mSeriesAdapter: SeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
    }

    private fun methods() {
        getCharDetails()
        getCharComics()
        getCharSeries()
    }

    private fun getCharDetails() {
        viewModel.getCharacterDetails(1009144).observe(viewLifecycleOwner, { detail ->
            for (charDetails in detail.characterData.characterResults) {
                binding.includeDetailsChar.tvNameChar.text = charDetails.name
                binding.includeDetailsChar.tvDescriptionChar.text = charDetails.description
                Timber.d("CharDetails::${charDetails.name}, ${charDetails.description}")
                val image = "${charDetails.thumbnail.path}.${charDetails.thumbnail.extension}"
                Glide.with(this)
                    .load(image)
                    .override(240)
                    .placeholder(R.drawable.image_placeholder)
                    .into(binding.ivHeaderChar)
            }
        })
    }

    private fun getCharComics() {
        viewModel.getCharacterComics(1009144).observe(viewLifecycleOwner, { comics ->
            mComicsAdapter = ComicsAdapter(requireContext(), comics.data.results)
            binding.includeDetailsChar.incComicsList.rvComics.apply {
                setHasFixedSize(true)
                adapter = mComicsAdapter
            }
        })
    }

    private fun getCharSeries() {
        viewModel.getCharacterSeries(1009144).observe(viewLifecycleOwner, { series ->
            mSeriesAdapter = SeriesAdapter(requireContext(), series.data.results)
            binding.includeDetailsChar.incSeriesList.rvSeries.apply {
                setHasFixedSize(true)
                adapter = mSeriesAdapter
            }
        })
    }
}