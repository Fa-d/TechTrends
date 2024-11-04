package dev.experimental.techtrends.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.experimental.techtrends.core.MainRepository
import dev.experimental.techtrends.utils.toSavedItemList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedItemViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    val savedList = MutableStateFlow<List<SavedItem>>(emptyList())

    init {
        getAllSavedFeeds()
    }

    private fun getAllSavedFeeds() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val response = mainRepository.getAllSavedArticles()
                savedList.emit(response.toSavedItemList())
            }
        }

    }
}


data class SavedItem(
    val id: Int,
    val companyName: String,
    val articleTitle: String,
    val authorName: String,
    val content: String,
    val companyLogo: String
)