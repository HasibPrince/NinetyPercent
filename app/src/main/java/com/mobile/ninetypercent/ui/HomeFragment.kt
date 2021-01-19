package com.mobile.ninetypercent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mobile.ninetypercent.databinding.FragmentHomeBinding
import com.mobile.ninetypercent.ui.adapters.CategoryAdapter
import com.mobile.ninetypercent.ui.adapters.DressAdapter
import com.mobile.ninetypercent.ui.adapters.ItemDecoration
import com.mobile.ninetypercent.ui.utils.Event
import com.mobile.ninetypercent.ui.utils.EventObserver
import com.mobile.ninetypercent.ui.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryAdapter: CategoryAdapter

    private val filterViewModel: FilterViewModel by activityViewModels<FilterViewModel>()
    private val dressViewModel by viewModels<DressViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.dressViewModel = dressViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter =
            CategoryAdapter(filterViewModel.getStyleFilterString())
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        binding.filterOptions.layoutManager = layoutManager
        binding.filterOptions.adapter = categoryAdapter

        setupDressList()
        dressViewModel.init()

        filterViewModel.filterUpdateLiveData.observe(viewLifecycleOwner, EventObserver {
            dressViewModel.updateDressLiveData()
        })
    }

    private fun setupDressList() {
        binding.dressList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.dressList.addItemDecoration(
            ItemDecoration(
                ViewUtils.dpToPx(requireContext(), 20),
                ViewUtils.dpToPx(requireContext(), 20)
            )
        )
        binding.dressList.adapter = DressAdapter(dressViewModel)
    }
}