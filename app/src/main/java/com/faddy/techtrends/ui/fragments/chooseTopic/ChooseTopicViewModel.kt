package com.faddy.techtrends.ui.fragments.chooseTopic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faddy.techtrends.core.MainRepository
import com.faddy.techtrends.models.newModels.CategoryModel
import com.faddy.techtrends.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseTopicViewModel @Inject constructor(
    private val mainRepository: MainRepository, private val sessionManager: SessionManager
) : ViewModel() {
    var allCategoriesList = MutableLiveData<List<CategoryModel>>()
}