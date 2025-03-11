package dev.experimental.techtrends.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.experimental.techtrends.core.MainRepository
import dev.experimental.techtrends.models.CategoryModel
import dev.experimental.techtrends.models.FeedItem
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

    suspend fun getAllCategoriesData() {
        mainRepository.getAllCategoriesDB().collect { dataFromRoom ->
            if (dataFromRoom.isNotEmpty()) {
                val filteredList = dataFromRoom.filter { it.selectedByUser == "user1" }
                allCategoriesListByUser.emit(filteredList)
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

    fun setFeedAsFav(user: String = "user1", id: Int, feedCategory: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.setFeedAsFav(user, id)
                getAllFeedByCategory(feedCategory)
            }
        }
    }

    fun setFeedAlert(user: String = "user1", id: Int, feedCategory: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.setFeedAlert(user, id)
                getAllFeedByCategory(feedCategory)
            }
        }
    }
}