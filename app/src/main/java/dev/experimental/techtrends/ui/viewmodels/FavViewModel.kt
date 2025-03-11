package dev.experimental.techtrends.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.experimental.techtrends.core.MainRepository
import dev.experimental.techtrends.models.custom.FavCompanyItem
import dev.experimental.techtrends.utils.toFavCompanyItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    val favList = MutableStateFlow<UIState<List<FavCompanyItem>>>(UIState.Idle)


    fun getAllFavFeeds() {
        viewModelScope.launch {
            favList.emit(UIState.Loading)
            withContext(Dispatchers.IO) {
                val getAllFormattedFavs = mainRepository.getAllFavFeeds("user1")
                    .map { feedItem -> feedItem.toFavCompanyItem() }
                favList.emit(UIState.Success(getAllFormattedFavs))
            }
        }
    }

    fun removeItemFromFav(id: Int, user: String = "user1") {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainRepository.setFeedAsFav(user, id)
                getAllFavFeeds()
            }
        }
    }
}

sealed class UIState<out T> {
    object Idle : UIState<Nothing>()
    object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}

