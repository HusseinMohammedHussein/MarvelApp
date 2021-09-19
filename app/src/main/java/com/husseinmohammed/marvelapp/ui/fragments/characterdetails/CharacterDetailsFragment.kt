package com.husseinmohammed.marvelapp.ui.fragments.characterdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.data.api.ApiClient
import com.husseinmohammed.marvelapp.data.api.ApiHelperImpl
import com.husseinmohammed.marvelapp.data.pojos.character.CharacterItemPojo
import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.data.pojos.comic.ComicPojo
import com.husseinmohammed.marvelapp.data.pojos.event.EventPojo
import com.husseinmohammed.marvelapp.data.pojos.series.SeriesPojo
import com.husseinmohammed.marvelapp.data.pojos.story.StoryPojo
import com.husseinmohammed.marvelapp.databinding.FragmentCharacterDetailsBinding
import com.husseinmohammed.marvelapp.ui.activity.MainActivity
import com.husseinmohammed.marvelapp.ui.adapters.ComicsAdapter
import com.husseinmohammed.marvelapp.ui.adapters.EventsAdapter
import com.husseinmohammed.marvelapp.ui.adapters.SeriesAdapter
import com.husseinmohammed.marvelapp.ui.adapters.StoriesAdapter
import com.husseinmohammed.marvelapp.utils.ImageType
import com.husseinmohammed.marvelapp.utils.Status
import com.husseinmohammed.marvelapp.utils.ViewModelFactory
import timber.log.Timber

class CharacterDetailsFragment : Fragment() {
    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharacterDetailsViewModel

    private lateinit var mComicsAdapter: ComicsAdapter
    private lateinit var mSeriesAdapter: SeriesAdapter
    private lateinit var mStoriesAdapter: StoriesAdapter
    private lateinit var mEventsAdapter: EventsAdapter

    private lateinit var bundle: Bundle
    private lateinit var getCharItem: CharacterItemPojo
    private var charId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
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
        initBundle()
        requests()
        responseCharDetails()
        responseCharComics()
        responseCharSeries()
        responseCharStories()
        responseCharEvents()
        onClick()
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperImpl(ApiClient.apiServer))
        ).get(CharacterDetailsViewModel::class.java)
    }

    private fun initBundle() {
        bundle = Bundle()
        bundle = requireArguments()
        getCharItem = bundle.getParcelable(getString(R.string.charItem))!!
        charId = getCharItem.id
    }

    private fun requests() {
        Timber.d("GetCharId::${charId}")
        viewModel.getCharDetails(charId)
        viewModel.getCharComics(charId)
        viewModel.getCharSeries(charId)
        viewModel.getCharStories(charId)
        viewModel.getCharEvents(charId)
    }

    private fun responseCharDetails() {
        viewModel.charDetailsMLD.observe(viewLifecycleOwner, { detail ->
            when (detail.status) {
                Status.SUCCESS -> {
                    detail.data?.let {
                        renderDetails(it)
                    }
                    binding.grpCharDetails.visibility = View.VISIBLE
                    binding.loader.loader.visibility = View.GONE
                }

                Status.LOADING -> {
                    binding.grpCharDetails.visibility = View.GONE
                    binding.loader.loader.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    binding.loader.loader.visibility = View.GONE
                    Toast.makeText(requireContext(), detail.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun responseCharComics() {
        viewModel.charComicsMLD.observe(viewLifecycleOwner, { comics ->
            when (comics.status) {
                Status.SUCCESS -> {
                    comics.data?.let {
                        renderComics(it)
                    }
                    binding.grpCharDetails.visibility = View.VISIBLE
                    binding.loader.loader.visibility = View.GONE
                }

                Status.LOADING -> {
                    binding.grpCharDetails.visibility = View.GONE
                    binding.loader.loader.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    binding.grpCharDetails.visibility = View.GONE
                    binding.loader.loader.visibility = View.GONE
                    Toast.makeText(requireContext(), comics.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun responseCharSeries() {
        viewModel.charSeriesMLD.observe(viewLifecycleOwner, { series ->
            when (series.status) {
                Status.SUCCESS -> {
                    series.data?.let {
                        renderSeries(it)
                    }
                    binding.grpCharDetails.visibility = View.VISIBLE
                    binding.loader.loader.visibility = View.GONE
                }

                Status.LOADING -> {
                    binding.grpCharDetails.visibility = View.GONE
                    binding.loader.loader.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    binding.grpCharDetails.visibility = View.GONE
                    binding.loader.loader.visibility = View.GONE
                    Toast.makeText(requireContext(), series.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun responseCharStories() {
        viewModel.charStoriesMLD.observe(viewLifecycleOwner, { stories ->
            when (stories.status) {
                Status.SUCCESS -> {
                    stories.data?.let {
                        renderStories(it)
                    }
                    binding.grpCharDetails.visibility = View.VISIBLE
                    binding.loader.loader.visibility = View.GONE
                }

                Status.LOADING -> {
                    binding.grpCharDetails.visibility = View.GONE
                    binding.loader.loader.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    binding.grpCharDetails.visibility = View.GONE
                    binding.loader.loader.visibility = View.GONE
                    Toast.makeText(requireContext(), stories.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun responseCharEvents() {
        viewModel.charEventsMLD.observe(viewLifecycleOwner, { event ->
            when (event.status) {
                Status.SUCCESS -> {
                    event.data?.let {
                        renderEvents(it)
                    }
                    binding.grpCharDetails.visibility = View.VISIBLE
                    binding.loader.loader.visibility = View.GONE
                }

                Status.LOADING -> {
                    binding.grpCharDetails.visibility = View.GONE
                    binding.loader.loader.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    binding.grpCharDetails.visibility = View.GONE
                    binding.loader.loader.visibility = View.GONE
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderDetails(charDetails: CharacterPojo) {
        for (details in charDetails.characterData.characterResults) {
            Timber.d("CharDetails::${details.name}, Desc::${details.description}")
            val image =
                "${details.thumbnail?.path}${ImageType.IMAGE_TYPE}${details.thumbnail?.extension}"

            if (details.description.isEmpty()) {
                binding.includeDetailsChar.incDescription.tvDescriptionChar.text =
                    getString(R.string.description_hint)
            } else {
                binding.includeDetailsChar.incDescription.tvDescriptionChar.text =
                    details.description
            }

            binding.includeDetailsChar.tvNameChar.text = details.name
            Glide.with(this)
                .load(image)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(binding.ivHeaderChar)
        }
    }

    private fun renderComics(comic: ComicPojo) {
        mComicsAdapter = ComicsAdapter(requireContext(), comic)
        binding.includeDetailsChar.incComicsList.incRecycler.rvComponent.apply {
            setHasFixedSize(true)
            adapter = mComicsAdapter
        }
    }

    private fun renderStories(story: StoryPojo) {
        mStoriesAdapter = StoriesAdapter(requireContext(), story)
        binding.includeDetailsChar.incStoriesList.incRecycler.rvComponent.apply {
            setHasFixedSize(true)
            adapter = mStoriesAdapter
        }
    }

    private fun renderSeries(series: SeriesPojo) {
        mSeriesAdapter = SeriesAdapter(requireContext(), series)
        binding.includeDetailsChar.incSeriesList.incRecycler.rvComponent.apply {
            setHasFixedSize(true)
            adapter = mSeriesAdapter
        }
    }

    private fun renderEvents(events: EventPojo) {
        mEventsAdapter = EventsAdapter(requireContext(), events)
        binding.includeDetailsChar.incEventsList.incRecycler.rvComponent.apply {
            setHasFixedSize(true)
            adapter = mEventsAdapter
        }

        Timber.d("EventsSize::${events.data.results.size}")
        if (events.data.results.size == 0) {
            binding.includeDetailsChar.incEventsList.eventsRoot.visibility = View.GONE
        }
    }

    private fun onClick() {
        binding.ivBackButton.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
