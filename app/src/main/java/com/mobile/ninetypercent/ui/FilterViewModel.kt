package com.mobile.ninetypercent.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.domain.FilterUseCase

class FilterViewModel @ViewModelInject  constructor(private val filterUseCase: FilterUseCase) : ViewModel() {

    fun getSizeFilters(): List<Pair<Value,Boolean>> {
        return filterUseCase.getSizeFilters()
    }

    fun getShapeFilters(): List<Pair<Value,Boolean>> {
        return filterUseCase.getShapeFilters()
    }

    fun getStyleFilters(): List<Pair<Value, Boolean>> {
        return filterUseCase.getStyleFilters()
    }

    fun getColorFilters(): List<Pair<Value, Boolean>> {
        return filterUseCase.getColorFilters()
    }

    fun updateSelectedFilter(filter: Value, isSelected: Boolean) {
        filterUseCase.updateSelectedFilter(filter, isSelected)
    }

    fun getMaterialFilters(): List<Pair<Value, Boolean>> {
        return filterUseCase.getMaterialFilters()
    }
}