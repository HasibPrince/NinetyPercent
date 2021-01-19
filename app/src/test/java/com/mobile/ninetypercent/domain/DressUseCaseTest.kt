package com.mobile.ninetypercent.domain

import com.mobile.ninetypercent.data.*
import org.hamcrest.Matchers.`is`
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DressUseCaseTest {

    private val selectedFilters = mutableMapOf(
        Pair(Style::class.simpleName!!, mutableListOf<Value>(Style.LONG_SLEEVE, Style.MINI)),
        Pair(Material::class.simpleName!!, mutableListOf<Value>(Material.COTTON))
    )
    private lateinit var dressUseCase: DressUseCase

    @Before
    fun before() {
        SelectedFilters.selectedFilters = selectedFilters
        dressUseCase = DressUseCase()
        DressDao.filterDressesWithFilter()
    }

    @Test
    fun getFilteredDresses() {
        val dresses = dressUseCase.getFilteredDresses()
        assertThat(dresses[0].name , `is`("Organic Cotton Rib Roll Neck Dress"))
    }

    @Test
    fun getDressColorsWithSelected() {
        val colors = dressUseCase.getDressColorsWithSelected(DressDao.filteredDresses[0])
        assertThat(colors[0].first.value(), `is`(Colors.BLACK.value()))
        assertThat(colors[1].first.value(), `is`(Colors.BLUE.value()))
    }
}