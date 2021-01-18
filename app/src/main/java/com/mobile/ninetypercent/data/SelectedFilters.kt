package com.mobile.ninetypercent.data

import com.mobile.ninetypercent.common.extensions.toJsonString
import timber.log.Timber

object SelectedFilters {
    val selectedFilters = mutableMapOf<String, MutableList<Value>>()

    fun updateSelectedFilter(filterOption: Value, selected: Boolean) {
        when (filterOption) {
            is Shapes -> {
                updateSelectedFilter(Shapes::class.simpleName ?: "", filterOption, selected)
            }
            is Style -> {
                updateSelectedFilter(Style::class.simpleName ?: "", filterOption, selected)
            }
            is Sizes -> {
                updateSelectedFilter(Sizes::class.simpleName ?: "", filterOption, selected)
            }
            is Colors -> {
                updateSelectedFilter(Colors::class.simpleName ?: "", filterOption, selected)
            }
            is Material -> {
                updateSelectedFilter(Material::class.simpleName ?: "", filterOption, selected)
            }
        }
    }

    private fun updateSelectedFilter(key: String, filterOption: Value, isSelected: Boolean) {
        if (selectedFilters.containsKey(key)) {
            if(isSelected) {
                selectedFilters[key]?.add(filterOption)
            } else {
                selectedFilters[key]?.remove(filterOption)
            }
        } else {
            val filterList = mutableListOf<Value>()
            filterList.add(filterOption)
            selectedFilters[key] = filterList
        }

        Timber.d("===> selected filters ${selectedFilters.toJsonString()}")
    }
}