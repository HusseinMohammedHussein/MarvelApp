package com.husseinmohammed.marvelapp.ui.fragments.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.databinding.FragmentCharactersBinding
import com.husseinmohammed.marvelapp.ui.activity.MainActivity
import com.husseinmohammed.marvelapp.ui.fragments.chractersearch.CharactersSearchFragment
import timber.log.Timber

class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var viewModel: CharactersViewModel
    private lateinit var characterSearch: CharactersSearchFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        onClick()
    }

    private fun onClick() {
        binding.ivSearch.setOnClickListener {
            characterSearch = CharactersSearchFragment.newInstance()
            characterSearch.show((activity as MainActivity).supportFragmentManager, null)
        }
    }

    private fun init() {
        binding.rvCharacters.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }

        binding.ivSearch.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_charactersFragment_to_charactersSearchFragment)
        }
    }

    private fun getCharacters() {
        binding.loader.loader.visibility = View.VISIBLE
        viewModel.getCharacters().observe(viewLifecycleOwner, { characters ->
            binding.loader.loader.visibility = View.GONE
            Timber.d("ResponseCode::${characters.code}")
            Timber.d("ResponseCode::${characters.status}")
            Timber.d("ResponseData::${characters.characterData.characterResults}")
            charactersAdapter =
                CharactersAdapter(requireContext(), characters.characterData.characterResults)
            binding.rvCharacters.adapter = charactersAdapter
        })
    }
}
