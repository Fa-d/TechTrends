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
    val favList = MutableStateFlow<List<FavCompanyItem>>(listOf())


    fun getAllFavFeeds() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val getAllFormattedFavs = mainRepository.getAllFavFeeds("user1")
                    .map { feedItem -> feedItem.toFavCompanyItem() }
                favList.emit(getAllFormattedFavs)
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


