package com.mobile.ninetypercent.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.domain.FilterUseCase
import com.mobile.ninetypercent.ui.utils.Event

class FilterViewModel @ViewModelInject constructor(private val filterUseCase: FilterUseCase) :
    ViewModel() {

    private val _filterUpdateLiveData= MutableLiveData<Event<Any>>()
    val filterUpdateLiveData: LiveData<Event<Any>>
    get() = _filterUpdateLiveData

    fun getSizeFilters(): List<Pair<Value, Boolean>> {
        return filterUseCase.getSizeFilters()
    }

    fun getShapeFilters(): List<Pair<Value, Boolean>> {
        return filterUseCase.getShapeFilters()
    }

    fun getStyleFilters(): List<Pair<Value, Boolean>> {
        return filterUseCase.getStyleFilters()
    }

    fun getStyleFilterString(): List<String> {
        return filterUseCase.getStyleFiltersStrings()
    }

    fun getColorFilters(): List<Pair<Value, Boolean>> {
        return filterUseCase.getColorFilters()
    }

    fun updateSelectedFilter(filter: Value, isSelected: Boolean) {
        filterUseCase.updateSelectedFilter(filter, isSelected)
        _filterUpdateLiveData.value = Event(Any())
    }

    fun getMaterialFilters(): List<Pair<Value, Boolean>> {
        return filterUseCase.getMaterialFilters()
    }
}