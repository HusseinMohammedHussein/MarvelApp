package com.husseinmohammed.marvelapp.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husseinmohammed.marvelapp.data.api.ApiHelper
import com.husseinmohammed.marvelapp.data.local.DatabaseHelper
import com.husseinmohammed.marvelapp.data.pojos.local.CharacterLocalPojo
import com.husseinmohammed.marvelapp.utils.ImageType
import com.husseinmohammed.marvelapp.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class CharactersViewModel(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModel() {

    var charactersMLD = MutableLiveData<Resource<List<CharacterLocalPojo>>>()

    fun getCharacters(
        limit: Int,
        isInternetOpen: Boolean
    ): LiveData<Resource<List<CharacterLocalPojo>>> {
        viewModelScope.launch {
            apiHelper.getCharacters(limit)
                .catch { e ->
                    charactersMLD.postValue(Resource.error(e.localizedMessage, null))
                    Timber.d("Characters::OnFailure::${e.localizedMessage}")
                }
                .collect { response ->
                    charactersMLD.postValue(Resource.loading(null))

                    try {
                        val charsFromDB = dbHelper.getChars()
                        if (charsFromDB.isEmpty()) {
                            val charToInsertInDb = mutableListOf<CharacterLocalPojo>()
                            if (isInternetOpen) {
                                for (apiChar in response.characterData.characterResults) {
                                    val charLocal = CharacterLocalPojo(
                                        apiChar.id,
                                        apiChar.name,
                                        "${apiChar.thumbnail?.path}${ImageType.IMAGE_TYPE}${apiChar.thumbnail?.extension}"
                                    )
                                    charToInsertInDb.add(charLocal)
                                }
                                dbHelper.insertCharacter(charToInsertInDb)
                                charactersMLD.postValue(Resource.success(charToInsertInDb))
                            } else {
                                charactersMLD.postValue(
                                    Resource.error(
                                        "Internet is not available!",
                                        null
                                    )
                                )
                            }
                        } else {
                            charactersMLD.postValue(Resource.success(charsFromDB))
                        }
                    } catch (e: Exception) {
                        charactersMLD.postValue(Resource.error(e.localizedMessage, null))
                        Timber.e("getCharsOnFailure ${e.localizedMessage}")
                    }

                }
        }
        return charactersMLD
    }
}

