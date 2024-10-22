package com.faddy.techtrends.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faddy.techtrends.core.MainRepository
import com.faddy.techtrends.models.custom.FavCompanyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    val favList = MutableStateFlow<List<FavCompanyItem>>(listOf())

    fun getAllFeed() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = mainRepository.getAllFeedItem()
                val tempFavList = mutableListOf<FavCompanyItem>()

                response.groupBy { it.companyName }.forEach { (companyName, companyList) ->
                    companyList.firstOrNull()?.let { company ->
                        tempFavList.add(
                            FavCompanyItem(
                                companyName = companyName,
                                companyDesc = company.companyDescription,
                                articleCount = companyList.size.toString(),
                                companyLogo = company.companyLogoUrl
                            )
                        )
                    }
                }
                favList.emit(tempFavList)
            }
        }
    }
}