package com.faddy.techtrends.core

import androidx.lifecycle.ViewModel
import com.faddy.techtrends.models.MotherArticle
import com.faddy.techtrends.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val sessionManager: SessionManager
) : ViewModel() {

    fun getArticleData() {
        mainRepository.getArticleData().enqueue(object : retrofit2.Callback<List<MotherArticle>> {
            override fun onResponse(
                call: Call<List<MotherArticle>>, response: Response<List<MotherArticle>>
            ) {

            }

            override fun onFailure(call: Call<List<MotherArticle>>, t: Throwable) {
            }
        })
    }

}