package com.mobile.ninetypercent.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobile.ninetypercent.data.*
import com.mobile.ninetypercent.data.model.Dress
import getOrAwaitValue
import org.hamcrest.Matchers.`is`
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class DressViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var dressViewModel: DressViewModel

    val dresses = listOf<Dress>(
        Dress("Maxi Dress", 90.00,
            listOf(Colors.BLACK, Colors.BLUE),
            listOf(Style.SHIRTS, Style.MAXI), listOf(Sizes.M, Sizes.XL),
            Shapes.DRESSES, Material.TENCEL ),

        Dress("Neck Dress", 90.00,
            listOf(Colors.BLACK, Colors.BLUE),
            listOf(Style.LONG_SLEEVE, Style.MINI), listOf(Sizes.S, Sizes.M),
            Shapes.DRESSES, Material.TENCEL ),

        Dress("Midi Dress", 75.00,
            listOf(Colors.PINK, Colors.GREY),
            listOf(Style.LONG_SLEEVE, Style.MINI), listOf(Sizes.L, Sizes.XL),
            Shapes.DRESSES, Material.COTTON )
    )

    val selectedFilters = mapOf<String, List<Value>>(
        Pair(Style::class.simpleName!!, listOf(Style.LONG_SLEEVE, Style.MINI)),
        Pair(Material::class.simpleName!!, listOf(Material.COTTON))
    )

    @Before
    fun before() {
        val dressUseCase = FakeDressUseCase(dresses, selectedFilters)
        dressViewModel = DressViewModel(dressUseCase)
        dressViewModel.init()
    }

    @Test
    fun getDressLiveData() {
        val filteredDresses = dressViewModel.dressLiveData.getOrAwaitValue()
        assertThat(filteredDresses.size, `is`(1))
        assertThat(filteredDresses[0].name, `is`("Midi Dress"))
    }

    @Test
    fun getDressColorsWithSelected(){
        val colors = dressViewModel.getDressColorsWithSelected(dresses[0])
        assertThat(colors[0].first.value(), `is`(Colors.BLACK.value()))
        assertThat(colors[1].first.value(), `is`(Colors.BLUE.value()))
    }
}