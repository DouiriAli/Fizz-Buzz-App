package com.rss.fizzbuzzapp.firstscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rss.fizzbuzzapp.mapper.FizzBuzzUiMapper
import com.rss.fizzbuzzapp.model.FizzBuzzUiModel
import com.rss.fizzbuzzapp.util.Resource

class FizzBuzzFirstViewModel(private val uiMapper: FizzBuzzUiMapper) : ViewModel() {

    private val _isFormValid = MutableLiveData<Resource<FizzBuzzUiModel>?>()
    val isFormValid: LiveData<Resource<FizzBuzzUiModel>?>
        get() = _isFormValid

    /**
     * Check the params :
     * if one of the parameters is empty we return an error
     * else we map the params to a [FizzBuzzUiModel]
     */
    fun createUiModel(int1: String, int2: String, limit: String, str1: String, str2: String) {
        if (int1.isEmpty() || int2.isEmpty() || limit.isEmpty() || str1.isEmpty() || str2.isEmpty()) {
            _isFormValid.value = Resource.error("", null)
        } else {
            _isFormValid.value = Resource.success(uiMapper.toUiModel(int1, int2, limit, str1, str2))
        }
    }

    /**
     * Clear all live data
     */
    fun clearLiveData(){
        _isFormValid.value = null
    }

}