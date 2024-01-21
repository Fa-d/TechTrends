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
    var allCategoriesList = MutableLiveData<List<Map<String, String>>>()
    var allChildArticleModelList = MutableLiveData<List<ChildArticleModel>>()
    var allMotherArticleModelList = MutableLiveData<List<Map<String, String>>>()

    fun getAllCategoriesData() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val previousObjects = mainRepository.getAllCategoriesFromDb()
                if (previousObjects.isNotEmpty()) {
                    val tempMap = mutableMapOf<String, String>()
                    previousObjects.forEach {
                        tempMap[it.cat_key] = it.cat_name
                    }
                    allCategoriesList.postValue(listOf(tempMap))
                } else {
                    getAllCategoriesFromApi()
                }
            }
        }

    }

    private fun getAllCategoriesFromApi() {
        mainRepository.getAllCategories()
            .enqueue(object : retrofit2.Callback<List<Map<String, String>>> {
                override fun onFailure(call: Call<List<Map<String, String>>>, t: Throwable) {}
                override fun onResponse(
                    call: Call<List<Map<String, String>>>,
                    response: Response<List<Map<String, String>>>
                ) {
                    if (response.isSuccessful) {
                        var apiResponse = response.body()
                        val tempCategoryList = mutableListOf<CategoryModel>()
                        apiResponse?.forEach {
                            tempCategoryList.add(
                                CategoryModel(
                                    cat_key = it.keys.first(), cat_name = it.values.first()
                                )
                            )
                        }
                        viewModelScope.launch {
                            CoroutineScope(Dispatchers.IO).launch {
                                mainRepository.insetCategory(tempCategoryList)
                            }.invokeOnCompletion {
                                getAllCategoriesData()
                            }
                        }
                    }
                }
            })
    }

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