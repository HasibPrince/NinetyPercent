package com.mobile.ninetypercent.ui

import com.mobile.ninetypercent.data.*
import com.mobile.ninetypercent.data.model.Dress
import com.mobile.ninetypercent.domain.IDressUseCase

class FakeDressUseCase(
    private val dressList: List<Dress>,
    private val selectedFilters: Map<String, List<Value>>
) : IDressUseCase {
    override fun getFilteredDresses(): List<Dress> {
        return dressList.filter {
            selectedFilters[Style::class.simpleName]?.containsAll(it.styles) == true &&
                    selectedFilters[Material::class.simpleName]!![0] == it.material
        }
    }

    override fun getDressColorsWithSelected(dress: Dress): List<Pair<Value, Boolean>> {
        return dress.colors.map {
            Pair(
                it,
                selectedFilters[Colors::class.simpleName]?.contains(it) == true
            )
        }
    }

    override fun updateSortSelection(sortType: Sort) {

    }
}