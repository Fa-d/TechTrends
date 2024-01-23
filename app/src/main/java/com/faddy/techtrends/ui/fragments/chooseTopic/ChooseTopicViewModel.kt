package com.faddy.techtrends.ui.fragments.chooseTopic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faddy.techtrends.core.MainRepository
import com.faddy.techtrends.models.newModels.CategoryModel
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
class ChooseTopicViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val sessionManager: SessionManager
) : ViewModel() {
    var allCategoriesList = MutableLiveData<List<CategoryModel>>()

    fun updateCategorySelectionState(isSelected: Boolean, catKey: String) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                mainRepository.setItemSelected(isSelected, catKey)
            }
        }
    }

    fun getAllCategoriesData() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val previousObjects = mainRepository.getAllCategoriesFromDb()
                if (previousObjects.isNotEmpty()) {
                    allCategoriesList.postValue(previousObjects)
                } else {
                    getAllCategoriesFromApi()
                }
            }
        }

    }

    private fun getAllCategoriesFromApi() {
        mainRepository.getAllCategories().enqueue(object : Callback<List<Map<String, String>>> {
            override fun onFailure(call: Call<List<Map<String, String>>>, t: Throwable) {}
            override fun onResponse(
                call: Call<List<Map<String, String>>>, response: Response<List<Map<String, String>>>
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
}