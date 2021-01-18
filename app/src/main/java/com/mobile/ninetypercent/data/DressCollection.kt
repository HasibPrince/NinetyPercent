package com.mobile.ninetypercent.data

import com.mobile.ninetypercent.data.model.Dress

object DressCollection {
    val dressCollection = mutableListOf<Dress>(
        Dress("Tencel™ Rib Maxi Dress", "£90.00",
            listOf(Colors.BLACK, Colors.BLUE),
            listOf(Style.LONG_SLEEVE, Style.MAXI), listOf(Sizes.M, Sizes.XL),
            Shapes.DRESSES, Material.TENCEL ),

        Dress("Organic Cotton Rib Roll Neck Dress", "£90.00",
            listOf(Colors.BLACK, Colors.BLUE),
            listOf(Style.LONG_SLEEVE, Style.MINI), listOf(Sizes.S, Sizes.M),
            Shapes.DRESSES, Material.COTTON ),

        Dress("Organic Cotton Rib Scoop Neck Midi Dress", "£75.00",
            listOf(Colors.PINK, Colors.GREY),
            listOf(Style.LONG_SLEEVE, Style.MIDI), listOf(Sizes.L, Sizes.XL),
            Shapes.DRESSES, Material.COTTON ),

        Dress("Tencel™ drawcord waist maxi dress", "£90.00",
            listOf(Colors.BLACK, Colors.RED),
            listOf(Style.LONG_SLEEVE, Style.MAXI), listOf(Sizes.XL, Sizes.XXS),
            Shapes.DRESSES, Material.TENCEL ),

        Dress("Tencel™ fitted cami dress", "£70.00",
            listOf(Colors.BLACK, Colors.BLUE),
            listOf(Style.CAMIS, Style.MIDI), listOf(Sizes.M, Sizes.XS),
            Shapes.DRESSES, Material.TENCEL ),

        Dress("Tencel™ sleepwear cami dress", "£90.00",
            listOf(Colors.BLACK, Colors.YELLOW),
            listOf(Style.CAMIS, Style.LONG_SLEEVE), listOf(Sizes.XXS, Sizes.XL),
            Shapes.SLEEPWEAR, Material.COTTON )
    )
}