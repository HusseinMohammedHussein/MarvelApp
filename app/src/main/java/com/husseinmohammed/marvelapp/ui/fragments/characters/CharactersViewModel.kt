package com.husseinmohammed.marvelapp.ui.fragments.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.husseinmohammed.marvelapp.data.pojos.CharacterPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class CharactersViewModel : ViewModel() {

    private var characterRepo = CharactersRepo()
    private var mutableLiveData: MutableLiveData<CharacterPojo> = MutableLiveData()


    fun getCharacters(): MutableLiveData<CharacterPojo> {
        characterRepo.getCharacters().enqueue(object : Callback<CharacterPojo> {
            override fun onResponse(call: Call<CharacterPojo>, response: Response<CharacterPojo>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CharacterPojo>, t: Throwable) {
                Timber.e("Characters::OnFailure::${t.localizedMessage}")
            }
        })

        return mutableLiveData
    }
}