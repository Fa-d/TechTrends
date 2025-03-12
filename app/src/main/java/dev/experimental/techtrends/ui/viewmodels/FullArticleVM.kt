package dev.experimental.techtrends.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.experimental.techtrends.core.MainRepository
import dev.experimental.techtrends.models.FeedItem
import dev.experimental.techtrends.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FullArticleVM @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _feedItem = MutableStateFlow<FeedItem?>(null)
    val feedItem: StateFlow<FeedItem?> = _feedItem

    fun getFeedById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getIndividualFeedByIdDB(id)?.let {
                _feedItem.emit(it)
            } ?: run {
                safeApiCall { mainRepository.getIndividualFeedByIdRemote(id) }.onSuccess {
                    _feedItem.emit(it)
                }.onFailure {
                    _feedItem.emit(null)
                }
            }
        }
    }
}