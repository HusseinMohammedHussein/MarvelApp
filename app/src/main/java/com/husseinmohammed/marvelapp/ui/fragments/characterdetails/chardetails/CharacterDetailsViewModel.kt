package com.husseinmohammed.marvelapp.ui.fragments.characterdetails.chardetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.data.pojos.comic.ComicPojo
import com.husseinmohammed.marvelapp.data.pojos.series.SeriesPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class CharacterDetailsViewModel : ViewModel() {
    private val characterDetailsRepo = CharacterDetailsRepo()
    private val charDetailsMLD: MutableLiveData<CharacterPojo> = MutableLiveData()
    private val charComicsMLD: MutableLiveData<ComicPojo> = MutableLiveData()
    private val charSeriesMLD: MutableLiveData<SeriesPojo> = MutableLiveData()

    fun getCharacterDetails(charId: Int): MutableLiveData<CharacterPojo> {
        characterDetailsRepo.getCharacterDetails(charId)
            .enqueue(object : Callback<CharacterPojo> {
                override fun onResponse(
                    call: Call<CharacterPojo>,
                    response: Response<CharacterPojo>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        charDetailsMLD.value = response.body()
                    }
                }

                override fun onFailure(call: Call<CharacterPojo>, t: Throwable) {
                    Timber.e("CharDetails::OnFailure::${t.localizedMessage}")
                }

            })
        return charDetailsMLD
    }

    fun getCharacterComics(charId: Int): MutableLiveData<ComicPojo> {
        characterDetailsRepo.getCharacterComics(charId).enqueue(object : Callback<ComicPojo> {
            override fun onResponse(call: Call<ComicPojo>, response: Response<ComicPojo>) {
                if (response.isSuccessful && response.body() != null) {
                    charComicsMLD.value = response.body()
                }
            }

            override fun onFailure(call: Call<ComicPojo>, t: Throwable) {
                Timber.d("CharComics:OnFailure::${t.localizedMessage}")
            }
        })
        return charComicsMLD
    }

    fun getCharacterSeries(charId: Int): MutableLiveData<SeriesPojo> {
        characterDetailsRepo.getCharacterSeries(charId).enqueue(object : Callback<SeriesPojo> {
            override fun onResponse(call: Call<SeriesPojo>, response: Response<SeriesPojo>) {
                if (response.isSuccessful && response.body() != null) {
                    charSeriesMLD.value = response.body()
                }
            }

            override fun onFailure(call: Call<SeriesPojo>, t: Throwable) {
                Timber.d("CharSeries:OnFailure::${t.localizedMessage}")
            }
        })
        return charSeriesMLD
    }
}