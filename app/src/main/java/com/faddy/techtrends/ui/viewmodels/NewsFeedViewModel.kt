package com.faddy.techtrends.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faddy.techtrends.core.MainRepository
import com.faddy.techtrends.models.FeedChildItem
import com.faddy.techtrends.models.FeedItem
import com.faddy.techtrends.models.newModels.CategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    var feedItemList = MutableLiveData<List<FeedItem>>()
    var allCategoriesListByUser = MutableStateFlow<List<CategoryModel>>(listOf())

    fun getAllFeedsData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val tempFeedItemList = mainRepository.getAllFeeds()
                feedItemList.postValue(tempFeedItemList)
            }
        }
    }


    fun getAllCategoriesData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.getAllCategoriesDB().collect { dataFromRoom ->
                    if (dataFromRoom.isNotEmpty()) {
                        val filteredList = dataFromRoom.filter { it.selectedByUser == "user1" }
                        allCategoriesListByUser.emit(filteredList)
                    }
                }
            }
        }
    }


    suspend fun getAllFeedChildByCategory(category: String): List<FeedChildItem> {
        return mainRepository.getAllFeedChildByCategory(category)
    }
}