package dev.experimental.techtrends.ui.viewmodels

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.experimental.techtrends.core.MainRepository
import dev.experimental.techtrends.datastore.TtPref
import dev.experimental.techtrends.datastore.copy
import dev.experimental.techtrends.models.CategoryModel
import dev.experimental.techtrends.utils.UIState
import dev.experimental.techtrends.work.FeedSyncPerCategoryWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChooseTopicViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val userPreferences: DataStore<TtPref>

) : ViewModel() {
    var allCategoriesList = MutableStateFlow<UIState<List<CategoryModel>>>(UIState.Idle)
    private val apiCallMutex = Mutex()

    fun getAllCategories() {
        loadDataFromRoom()
    }

    private fun loadDataFromRoom() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.getAllCategoriesDB().collect { dataFromRoom ->
                    allCategoriesList.emit(UIState.Idle)
                    apiCallMutex.withLock {
                        if (dataFromRoom.isEmpty()) {
                            loadDataFromApi()
                        } else {
                            val filteredRes = dataFromRoom.filter { it.name!!.length <= 10 }
                            allCategoriesList.emit(UIState.Success(filteredRes))
                        }
                    }
                }
            }
        }

    }

    private fun loadDataFromApi() {
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                val apiRes = mainRepository.getAllCategoriesAPI()
                val listCategory = mutableListOf<CategoryModel>()
                apiRes.onSuccess { categories ->
                    listCategory.addAll(categories)
                    mainRepository.insertAllCategories(listCategory)
                    loadDataFromRoom()
                }.onFailure {
                    allCategoriesList.emit(UIState.Error(it.message.toString()))
                }
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

    fun startFeedFetching(): List<OneTimeWorkRequest> {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true).setRequiresBatteryNotLow(true).build()
        val workReqList = mutableListOf<OneTimeWorkRequest>()
        if (allCategoriesList.value is UIState.Success) {
            val tempAllCatList = (allCategoriesList.value as UIState.Success).data
            tempAllCatList.forEach { categoryItem ->
                if (categoryItem.selectedByUser == "user1") {
                    workReqList.add(
                        OneTimeWorkRequestBuilder<FeedSyncPerCategoryWorker>().setInputData(
                            inputData = Data.Builder().putString("categoryName", categoryItem.name)
                                .build()
                        ).addTag(categoryItem.name ?: "").setConstraints(constraints).build()
                    )
                }
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