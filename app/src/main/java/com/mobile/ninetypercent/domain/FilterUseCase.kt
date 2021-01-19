package com.mobile.ninetypercent.domain

import com.mobile.ninetypercent.common.extensions.toJsonString
import com.mobile.ninetypercent.data.*
import com.mobile.ninetypercent.data.model.Dress
import timber.log.Timber
import javax.inject.Inject

class FilterUseCase @Inject constructor() {
    fun getSizeFilters(): List<Pair<Value,Boolean>> {
        return getFilters(Sizes::class.simpleName ?: ""){
             it.sizes
        }
    }

    fun getShapeFilters(): List<Pair<Value,Boolean>> {
        return getFilters(Shapes::class.simpleName ?: ""){
            listOf(it.shape)
        }
    }

    fun getStyleFilters(): List<Pair<Value, Boolean>> {
        return getFilters(Style::class.simpleName ?: ""){
            it.styles
        }
    }

    fun getStyleFiltersStrings(): List<String> {
        return getFilters(Style::class.simpleName ?: ""){
            it.styles
        }.map {
            it.first.value() as String
        }
    }

    fun getColorFilters(): List<Pair<Value, Boolean>> {
        return getFilters(Colors::class.simpleName ?: ""){
            it.colors
        }
    }

    fun getMaterialFilters(): List<Pair<Value, Boolean>> {
        return getFilters(Material::class.simpleName ?: ""){
            listOf(it.material)
        }
    }

    fun updateSelectedFilter(filter: Value, isSelected: Boolean) {
        SelectedFilters.updateSelectedFilter(filter, isSelected)
        val filteredDressesWithFilter = DressDao.filterDressesWithFilter()
        Timber.d("===> matched dresses ${filteredDressesWithFilter.toJsonString()}")
    }

    private fun getFilters(filterName: String, getFilters: (Dress) -> List<Value>): List<Pair<Value,Boolean>> {
        val selectedSizes = SelectedFilters.selectedFilters[filterName]
        val availableFilters = mutableSetOf<Value>()

        DressDao.filterDressesWithFilter().map { dress ->
            availableFilters.addAll(getFilters(dress))
        }
        return availableFilters.map {
            val isSelected = selectedSizes?.contains(it) ?: false
            Pair(it, isSelected)
        }
    }
}