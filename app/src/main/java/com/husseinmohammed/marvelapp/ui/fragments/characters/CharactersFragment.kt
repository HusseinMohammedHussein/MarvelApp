package com.husseinmohammed.marvelapp.ui.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.husseinmohammed.marvelapp.databinding.FragmentCharactersBinding
import timber.log.Timber

class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
    }

    private fun methods() {
        init()
        getCharacters()
    }

    private fun init() {
        binding.rvCharacters.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun getCharacters() {
        viewModel.getCharacters().observe(requireActivity(), { characters ->
            Timber.d("ResponseCode::${characters.code}")
            Timber.d("ResponseData::${characters.characterData.characterItems}")
            charactersAdapter =
                CharactersAdapter(requireContext(), characters.characterData.characterItems)
            binding.rvCharacters.adapter = charactersAdapter
        })
    }
}
