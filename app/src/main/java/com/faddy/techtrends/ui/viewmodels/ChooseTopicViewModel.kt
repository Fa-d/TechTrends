package com.faddy.techtrends.ui.viewmodels

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import com.faddy.techtrends.core.MainRepository
import com.faddy.techtrends.datastore.TtPref
import com.faddy.techtrends.datastore.copy
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.work.CategoriesSyncWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChooseTopicViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val userPreferences: DataStore<TtPref>

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
                        allCategoriesList.emit(dataFromRoom.filter { it.name!!.length <= 10 })
                    }
                }
            }
        }

    }

    private fun loadDataFromApi() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val apiData = mainRepository.getAllCategoriesAPI().filter { it.name!!.length <= 10 }
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

    fun startChildFeedFetching(): List<OneTimeWorkRequest> {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true).setRequiresBatteryNotLow(true).build()
        val workReqList = mutableListOf<OneTimeWorkRequest>()

        allCategoriesList.value.forEach { categoryItem ->
            if (categoryItem.selectedByUser == "user1") {
                workReqList.add(
                    OneTimeWorkRequestBuilder<CategoriesSyncWorker>().setInputData(
                        inputData = Data.Builder().putString("categoryName", categoryItem.name)
                            .build()
                    ).addTag(categoryItem.name ?: "").setConstraints(constraints).build()
                )
            }

        }
        return workReqList
    }

    fun setTopicSelectedStatus() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userPreferences.updateData {
                    it.copy { this.isMinimumTopicSelected = true }
                }
            }
        }
    }
}