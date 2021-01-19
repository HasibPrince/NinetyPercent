package com.mobile.ninetypercent.data

import com.mobile.ninetypercent.data.model.Dress

object DressDao {
    var filteredDresses = listOf<Dress>()
    private set

    init {
        filteredDresses = DressCollection.dressCollection
    }

    fun filterDressesWithFilter(): List<Dress> {
        filteredDresses = DressCollection.dressCollection.filter { dress ->
            val selectedFilters = SelectedFilters.selectedFilters.values.filter {
                    filter -> filter.isNotEmpty()
            }
            val totalFilter = selectedFilters.size
            var filterMatched: Int = 0

            val styleFilters = SelectedFilters.selectedFilters[Style::class.simpleName]
            styleFilters?.let {
                if (it.isNotEmpty() && dress.styles.containsAll(it)) filterMatched++
            }

            val colorFilters = SelectedFilters.selectedFilters[Colors::class.simpleName]
            colorFilters?.let {
                if (it.isNotEmpty() && dress.colors.containsAll(it)) filterMatched++
            }

            val sizeFilters = SelectedFilters.selectedFilters[Sizes::class.simpleName]
            sizeFilters?.let {
                if (it.isNotEmpty() && dress.sizes.containsAll(it)) filterMatched++
            }

            val materialFilters = SelectedFilters.selectedFilters[Material::class.simpleName]
            materialFilters?.let {
                it.forEach { material ->
                    if (it.isNotEmpty() && dress.material == material) filterMatched++
                }
            }

            totalFilter <= filterMatched
        }.toMutableList()
        return filteredDresses
    }




}