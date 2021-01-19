package com.mobile.ninetypercent.domain

import com.mobile.ninetypercent.data.Colors
import com.mobile.ninetypercent.data.DressDao
import com.mobile.ninetypercent.data.SelectedFilters
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.data.model.Dress
import javax.inject.Inject

class DressUseCase @Inject constructor(){
    fun getFilteredDresses(): List<Dress> {
        return DressDao.filteredDresses
    }

    fun getDressColorsWithSelected(dress: Dress): List<Pair<Value,Boolean>> {
        return dress.colors.map {
            Pair(it, SelectedFilters.selectedFilters[Colors::class.simpleName]?.contains(it) == true)
        }
    }
}