package com.husseinmohammed.marvelapp.ui.fragments.chractersearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husseinmohammed.marvelapp.data.api.ApiHelper
import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharactersSearchViewModel(val apiHelper: ApiHelper) : ViewModel() {
    var characterSearchMLD = MutableLiveData<Resource<CharacterPojo>>()

    fun getCharacterSearch(charName: String) {
        viewModelScope.launch {
            characterSearchMLD.postValue(Resource.loading(null))

            apiHelper.getCharacterSearch(charName)
                .catch { e ->
                    characterSearchMLD.postValue(Resource.error(e.localizedMessage, null))
                }
                .collect {
                    characterSearchMLD.postValue(Resource.success(it))
                }
        }
    }
}