package com.husseinmohammed.marvelapp.ui.fragments.chractersearch

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.api.ApiClient
import com.husseinmohammed.marvelapp.data.api.ApiHelperImpl
import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.databinding.FragmentCharactersSearchBinding
import com.husseinmohammed.marvelapp.ui.adapters.CharacterSearchAdapter
import com.husseinmohammed.marvelapp.utils.Status
import com.husseinmohammed.marvelapp.utils.ViewModelFactory
import com.husseinmohammed.marvelapp.utils.textInputAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@FlowPreview
class CharactersSearchFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCharactersSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharactersSearchViewModel
    private lateinit var charSearchAdapter: CharacterSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        setupViewModel()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { parent ->
                val behaviour = BottomSheetBehavior.from(parent)
                setupFullHeight(parent)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelperImpl(ApiClient.apiServer))
            ).get(CharactersSearchViewModel::class.java)
    }

    private fun methods() {
        initSearchEditText()
        onClick()
    }

    private fun initSearchEditText() {
        binding.inc.etSearch.textInputAsFlow()
            .debounce(2000) // delay to prevent searching immediately on every character input
            .onEach {
                viewModel.getCharacterSearch(it.toString())
                responseCharSearch()
            }
            .launchIn(lifecycleScope)
    }

    private fun responseCharSearch() {
        viewModel.characterSearchMLD.observe(viewLifecycleOwner, { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let {
                        renderSearchResult(it)
                    }
                    binding.inc.rvSearchResult.visibility = View.VISIBLE
                    binding.loader.loader.visibility = View.GONE
                }

                Status.LOADING -> {
                    binding.loader.loader.visibility = View.VISIBLE
                    binding.inc.rvSearchResult.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.loader.loader.visibility = View.GONE
                    binding.inc.rvSearchResult.visibility = View.GONE
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderSearchResult(result: CharacterPojo) {
        charSearchAdapter =
            CharacterSearchAdapter(requireContext(), result.characterData.characterResults)
        binding.inc.rvSearchResult.apply {
            setHasFixedSize(true)
            adapter = charSearchAdapter
        }
    }

    private fun onClick() {
        binding.inc.tvCancelSearch.setOnClickListener { dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}