package com.faddy.techtrends.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faddy.techtrends.core.MainRepository
import com.faddy.techtrends.models.newModels.CategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChooseTopicViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var allCategoriesList = MutableStateFlow<List<CategoryModel>>(listOf())

    fun getAllCategories() {
        loadDataFromRoom()
    }

    private fun loadDataFromRoom() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.getAllCategoriesDB().collect { dataFromRoom ->
                    if (dataFromRoom.isEmpty()) {
                        loadDataFromApi()
                    } else {
                        allCategoriesList.emit(dataFromRoom.filter { it.name.length <= 10 })
                    }
                }
            }
        }

    }

    private fun loadDataFromApi() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val apiData = mainRepository.getAllCategoriesAPI().filter { it.name.length <= 10 }
                    .distinctBy { it.name }
                mainRepository.insertAllCategories(apiData)
                loadDataFromRoom()
            }
        }
    }

    fun setSelectedCategoryByUser(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.setSelectedCategoryByUser(id)
            }
        }
    }
}