package dev.experimental.techtrends.ui.viewmodels

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.experimental.techtrends.datastore.TtPref
import dev.experimental.techtrends.datastore.copy
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
                userPreferences.updateData { it.copy { this.isTermsAgreed = value } }
            }
        }
    }


}