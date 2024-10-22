package com.faddy.techtrends.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faddy.techtrends.core.MainRepository
import com.faddy.techtrends.models.CategoryModel
import com.faddy.techtrends.models.FeedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    var allCategoriesListByUser = MutableStateFlow<List<CategoryModel>>(listOf())
    var feedItemByCategory = MutableStateFlow<List<FeedItem>>(listOf())

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


    fun getAllFeedByCategory(category: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                feedItemByCategory.emit(mainRepository.getAllFeedItemByCategoryDB(category))
            }
        }
    }

    fun setCompanyFav(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.setCompanyFav(id)
            }
        }
    }

    fun setArticleLater(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.setArticleLater(id)
            }
        }
    }

    fun removeCategoryFromSelected(categoryName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.removeCategoryFromSelected(categoryName)
            }
        }
    }
}