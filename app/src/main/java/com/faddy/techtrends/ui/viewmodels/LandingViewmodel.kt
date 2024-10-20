package com.faddy.techtrends.ui.viewmodels

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faddy.techtrends.datastore.TtPref
import com.faddy.techtrends.datastore.copy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LandingViewmodel @Inject constructor(
    private val userPreferences: DataStore<TtPref>
) : ViewModel() {

    fun getIsTermsConditionChecked(): Flow<Boolean> =
        flow { emit(userPreferences.data.first().isTermsAgreed) }


    fun getIsMinimumTopicSelected(): Flow<Boolean> {
        return flow { emit(userPreferences.data.first().isMinimumTopicSelected) }
    }

    fun setTermsConditionChecked(value: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.e("TAG", "setTermsConditionChecked: $value")
                userPreferences.updateData { it.copy { this.isTermsAgreed = value } }
            }
        }
    }


}