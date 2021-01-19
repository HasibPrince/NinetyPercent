package com.mobile.ninetypercent.domain

import com.mobile.ninetypercent.data.Sort
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.data.model.Dress

interface IDressUseCase {
    fun getFilteredDresses(): List<Dress>
    fun getDressColorsWithSelected(dress: Dress): List<Pair<Value, Boolean>>
    fun updateSortSelection(sortType: Sort)
}