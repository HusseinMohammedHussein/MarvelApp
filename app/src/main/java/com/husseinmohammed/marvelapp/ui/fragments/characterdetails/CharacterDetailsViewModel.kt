package com.husseinmohammed.marvelapp.ui.fragments.characterdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husseinmohammed.marvelapp.data.api.ApiHelper
import com.husseinmohammed.marvelapp.data.pojos.api.character.CharacterPojo
import com.husseinmohammed.marvelapp.data.pojos.api.comic.ComicPojo
import com.husseinmohammed.marvelapp.data.pojos.api.event.EventPojo
import com.husseinmohammed.marvelapp.data.pojos.api.series.SeriesPojo
import com.husseinmohammed.marvelapp.data.pojos.api.story.StoryPojo
import com.husseinmohammed.marvelapp.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    val charDetailsMLD = MutableLiveData<Resource<CharacterPojo>>()
    val charComicsMLD = MutableLiveData<Resource<ComicPojo>>()
    val charSeriesMLD = MutableLiveData<Resource<SeriesPojo>>()
    val charStoriesMLD = MutableLiveData<Resource<StoryPojo>>()
    val charEventsMLD = MutableLiveData<Resource<EventPojo>>()


    fun getCharDetails(charId: Int) {
        viewModelScope.launch {
            charDetailsMLD.postValue(Resource.loading(null))

            apiHelper.getCharacterDetails(charId)
                .catch { e ->
                    charDetailsMLD.postValue(Resource.error(e.localizedMessage, null))
                }
                .collect { response ->
                    charDetailsMLD.postValue(Resource.success(response))
                }
        }
    }

    fun getCharComics(charId: Int) {
        viewModelScope.launch {
            charComicsMLD.postValue(Resource.loading(null))
            apiHelper.getCharacterComics(charId)
                .catch { e ->
                    charComicsMLD.postValue(Resource.error(e.localizedMessage, null))
                }
                .collect { response ->
                    charComicsMLD.postValue(Resource.success(response))
                }
        }
    }

    fun getCharSeries(charId: Int) {
        viewModelScope.launch {
            charSeriesMLD.postValue(Resource.loading(null))
            apiHelper.getCharacterSeries(charId)
                .catch { e ->
                    charSeriesMLD.postValue(Resource.error(e.localizedMessage, null))
                }
                .collect { response ->
                    charSeriesMLD.postValue(Resource.success(response))
                }
        }
    }

    fun getCharStories(charId: Int) {
        viewModelScope.launch {
            charStoriesMLD.postValue(Resource.loading(null))
            apiHelper.getCharacterStories(charId)
                .catch { e ->
                    charStoriesMLD.postValue(Resource.error(e.localizedMessage, null))
                }
                .collect { response ->
                    charStoriesMLD.postValue(Resource.success(response))
                }
        }
    }

    fun getCharEvents(charId: Int) {
        viewModelScope.launch {
            charEventsMLD.postValue(Resource.loading(null))
            apiHelper.getCharacterEvents(charId)
                .catch { e ->
                    charEventsMLD.postValue(Resource.error(e.localizedMessage, null))
                }
                .collect { response ->
                    charEventsMLD.postValue(Resource.success(response))
                }
        }
    }
}