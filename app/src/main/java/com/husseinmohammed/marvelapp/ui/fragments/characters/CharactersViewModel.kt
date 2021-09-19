package com.husseinmohammed.marvelapp.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husseinmohammed.marvelapp.data.api.ApiHelper
import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class CharactersViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    var charactersMLD: MutableLiveData<Resource<CharacterPojo>> = MutableLiveData()

    fun getCharacters(limit: Int): LiveData<Resource<CharacterPojo>> {
        viewModelScope.launch {
            apiHelper.getCharacters(limit)
                .catch { e ->
                    charactersMLD.postValue(Resource.error(e.localizedMessage, null))
                    Timber.d("Characters::OnFailure::${e.localizedMessage}")
                }
                .collect { response ->
                    charactersMLD.postValue(Resource.success(response))
                }
        }
        return charactersMLD
    }
}

