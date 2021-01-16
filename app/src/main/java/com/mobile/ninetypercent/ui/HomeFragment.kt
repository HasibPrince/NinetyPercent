package com.mobile.ninetypercent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mobile.ninetypercent.databinding.FragmentHomeBinding
import com.mobile.ninetypercent.ui.adapters.CategoryAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryAdapter: CategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter =
            CategoryAdapter(listOf("Camis", "Shirts", "Long Sleeve", "Maxi", "Midi", "Mini", "Knitted"))
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        binding.filterOptions.layoutManager = layoutManager
        binding.filterOptions.adapter = categoryAdapter
    }
}