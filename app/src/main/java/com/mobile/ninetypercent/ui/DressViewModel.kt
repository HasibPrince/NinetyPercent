package com.mobile.ninetypercent.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.ninetypercent.R
import com.mobile.ninetypercent.data.Sort
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.data.model.Dress
import com.mobile.ninetypercent.domain.DressUseCase
import com.mobile.ninetypercent.ui.utils.Event
import timber.log.Timber

class DressViewModel @ViewModelInject constructor(private val dressUseCase: DressUseCase) : ViewModel() {

    private val _dressLiveData = MutableLiveData<List<Dress>>()
    val dressLiveData: LiveData<List<Dress>>
    get() = _dressLiveData

    private val _sortClickEventLiveData = MutableLiveData<Event<Any>>()
    val sortClickEventLiveData: LiveData<Event<Any>>
        get() = _sortClickEventLiveData

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

    fun onSortOptionSelected(optionId: Int) {
        when(optionId) {
            R.id.aToZ -> dressUseCase.updateSortSelection(Sort.A_TO_Z)
            R.id.zToA -> dressUseCase.updateSortSelection(Sort.Z_TO_A)
            R.id.lowToHight -> dressUseCase.updateSortSelection(Sort.LOW_TO_HIGH)
            R.id.highToLow -> dressUseCase.updateSortSelection(Sort.HIGH_TO_LOW)
        }
        updateDressLiveData()
    }

    fun onSortEventClicked() {
        _sortClickEventLiveData.value = Event(Any())
    }
}