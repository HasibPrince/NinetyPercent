package com.mobile.ninetypercent.data.model

import com.mobile.ninetypercent.data.*

data class Dress(
    val name: String, val price: String, val colors: List<Colors>,
    val styles: List<Style>, val sizes: List<Sizes>,
    val shape: Shapes, val material: Material
)