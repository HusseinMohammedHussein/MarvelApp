package com.husseinmohammed.marvelapp.ui.fragments.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.api.ApiClient
import com.husseinmohammed.marvelapp.data.api.ApiHelperImpl
import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.databinding.FragmentCharactersBinding
import com.husseinmohammed.marvelapp.ui.adapters.CharactersAdapter
import com.husseinmohammed.marvelapp.utils.InfiniteScrollListener
import com.husseinmohammed.marvelapp.utils.Status
import com.husseinmohammed.marvelapp.utils.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import timber.log.Timber

@ExperimentalCoroutinesApi
@FlowPreview
@SuppressLint("NotifyDataSetChanged")
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var charactersViewModel: CharactersViewModel

    private val defaultLimit = 10
    private var countLimit = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
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
        requests()
        responseCharacters()
        onClick()
    }

    private fun setupViewModel() {
        charactersViewModel =
            ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelperImpl(ApiClient.apiServer))
            ).get(CharactersViewModel::class.java)
    }

    private fun init() {
        charactersAdapter = CharactersAdapter(requireContext())
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvCharacters.apply {
            setHasFixedSize(false)
            this.layoutManager = layoutManager

            adapter = charactersAdapter

            addOnScrollListener(InfiniteScrollListener({
                loadMoreCharacters()
            }, layoutManager))
        }
    }

    private fun requests() {
        charactersViewModel.getCharacters(defaultLimit)
    }

    private fun responseCharacters() {
        charactersViewModel.charactersMLD.observe(viewLifecycleOwner, { characters ->
            when (characters.status) {
                Status.SUCCESS -> {
                    characters.data?.let { char ->
                        charactersAdapter.addData(char.characterData.characterResults)
                        updateIndexesForRequests(charactersAdapter, char)
                        charactersAdapter.notifyDataSetChanged()

                    }
                    binding.loader.loader.visibility = View.GONE
                    binding.rvCharacters.visibility = View.VISIBLE
                }

                Status.LOADING -> {
                    binding.loader.loader.visibility = View.VISIBLE
                    binding.rvCharacters.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.rvCharacters.visibility = View.GONE
                    binding.loader.loader.visibility = View.GONE
                    Toast.makeText(requireContext(), characters.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun loadMoreCharacters() {
        charactersViewModel.getCharacters(countLimit + defaultLimit)
    }

    private fun updateIndexesForRequests(
        adapter: CharactersAdapter,
        response: CharacterPojo
    ) {
        adapter.addData(response.characterData.characterResults)
        Timber.d("CountLimit::$countLimit")
        adapter.notifyItemRangeChanged(countLimit, countLimit + defaultLimit)
        countLimit += defaultLimit
    }

    private fun onClick() {
        binding.ivSearch.setOnClickListener {
            findNavController().navigate(R.id.action_charactersFragment_to_charactersSearchFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

