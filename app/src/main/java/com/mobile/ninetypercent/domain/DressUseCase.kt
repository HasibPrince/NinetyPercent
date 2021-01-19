package com.mobile.ninetypercent.domain

import com.mobile.ninetypercent.data.*
import com.mobile.ninetypercent.data.model.Dress
import javax.inject.Inject

class DressUseCase @Inject constructor() : IDressUseCase {
    override fun getFilteredDresses(): List<Dress> {
        return DressDao.filteredDresses
    }

    override fun getDressColorsWithSelected(dress: Dress): List<Pair<Value,Boolean>> {
        return dress.colors.map {
            Pair(it, SelectedFilters.selectedFilters[Colors::class.simpleName]?.contains(it) == true)
        }
    }

    override fun updateSortSelection(sortType: Sort) {
        DressDao.updateSelectedSort(sortType)
    }
}