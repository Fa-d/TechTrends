package com.faddy.techtrends.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.models.newModels.ChildArticleModel
import com.faddy.techtrends.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val sessionManager: SessionManager
) : ViewModel() {
    var allChildArticleModelList = MutableLiveData<List<ChildArticleModel>>()
    var allMotherArticleModelList = MutableLiveData<List<Map<String, String>>>()


    fun getAllChildArticlesData() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val previousObjects = mainRepository.getAllChildArticleCategoryFromDb()
                if (previousObjects.isNotEmpty()) {
                    allChildArticleModelList.postValue(previousObjects)
                } else {
                    getAllChildArticlesFromApi()
                }
            }
        }

    }

    private fun getAllChildArticlesFromApi() {
        mainRepository.getAllChildArticles().enqueue(object : Callback<List<ChildArticleModel>> {
            override fun onResponse(
                call: Call<List<ChildArticleModel>>, response: Response<List<ChildArticleModel>>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    viewModelScope.launch {
                        CoroutineScope(Dispatchers.IO).launch {
                            mainRepository.insetChildArticleCategory(apiResponse ?: listOf())
                        }.invokeOnCompletion {
                            getAllChildArticlesData()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ChildArticleModel>>, t: Throwable) {}
        })
    }
}