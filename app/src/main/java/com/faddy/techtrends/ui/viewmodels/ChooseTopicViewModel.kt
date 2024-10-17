package com.faddy.techtrends.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faddy.techtrends.core.MainRepository
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChooseTopicViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val sessionManager: SessionManager
) : ViewModel() {
    var allCategoriesList = MutableStateFlow<List<CategoryModel>>(listOf())

    fun getAllCategories() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                allCategoriesList.emit(mainRepository.getAllCategories()
                    .filter { it.name.length <= 15 })
            }
        }
    }
}