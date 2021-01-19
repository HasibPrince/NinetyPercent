package com.mobile.ninetypercent.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.data.model.Dress
import com.mobile.ninetypercent.domain.DressUseCase
import timber.log.Timber

class DressViewModel @ViewModelInject constructor(private val dressUseCase: DressUseCase) : ViewModel() {

    private val _dressLiveData = MutableLiveData<List<Dress>>()
    val dressLiveData: LiveData<List<Dress>>
    get() = _dressLiveData

    fun init() {
        updateDressLiveData()
        Timber.d("===> dresslivedata initialized ${_dressLiveData.value?.size}")
    }

    fun updateDressLiveData() {
        _dressLiveData.value = dressUseCase.getFilteredDresses()
    }

    fun getDressColorsWithSelected(dress: Dress): List<Pair<Value, Boolean>> {
        return dressUseCase.getDressColorsWithSelected(dress)
    }
}