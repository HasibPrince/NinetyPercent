package com.mobile.ninetypercent.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mobile.ninetypercent.R
import com.mobile.ninetypercent.databinding.DialogSortSelectionBinding
import com.mobile.ninetypercent.databinding.FragmentHomeBinding
import com.mobile.ninetypercent.ui.adapters.CategoryAdapter
import com.mobile.ninetypercent.ui.adapters.DressAdapter
import com.mobile.ninetypercent.ui.adapters.ItemDecoration
import com.mobile.ninetypercent.ui.utils.EventObserver
import com.mobile.ninetypercent.ui.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_sort_selection.view.*

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
        binding.filterViewModel = filterViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupStyleFilters()
        setupDressList()
        dressViewModel.init()

        filterViewModel.filterUpdateLiveData.observe(viewLifecycleOwner, EventObserver {
            dressViewModel.updateDressLiveData()
        })

        dressViewModel.sortClickEventLiveData.observe(viewLifecycleOwner, EventObserver {
            launchSortSelectionDialog()
        })
    }

    private fun setupStyleFilters() {
        categoryAdapter =
            CategoryAdapter(filterViewModel.getStyleFilters()) { value, isSelected ->
                filterViewModel.updateSelectedFilter(value, isSelected)
                categoryAdapter.categoryList = filterViewModel.getStyleFilters()
                categoryAdapter.notifyDataSetChanged()
            }

        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        binding.filterOptions.layoutManager = layoutManager
        binding.filterOptions.addItemDecoration(
            CategoryItemDecoration(
                ViewUtils.dpToPx(requireContext(), 30),
                ViewUtils.dpToPx(requireContext(), 15)
            )
        )

        binding.filterOptions.adapter = categoryAdapter
    }

    private fun setupDressList() {
        binding.dressList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.dressList.addItemDecoration(
            ItemDecoration(
                ViewUtils.dpToPx(requireContext(), 20),
                ViewUtils.dpToPx(requireContext(), 20)
            )
        )
        binding.dressList.adapter = DressAdapter(dressViewModel) {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_LONG).show()
        }
    }

    private fun launchSortSelectionDialog() {
        val sortSelectionDialog = MaterialAlertDialogBuilder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val sortSelectionDialogView = DialogSortSelectionBinding.inflate(inflater)

        val dialog = sortSelectionDialog.setView(sortSelectionDialogView.root)
            .setTitle("Sort")
            .show()

        sortSelectionDialogView.sortSelectRadio.setOnCheckedChangeListener { _, checkedId ->
            dressViewModel.onSortOptionSelected(checkedId)
            dialog.dismiss()
        }
    }
}

class CategoryItemDecoration(leftSpace: Int, bottomSpace: Int) : ItemDecoration(bottomSpace, leftSpace) {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if(parent.getChildAdapterPosition(view) == 0) {
            outRect.left = leftSpace / 2
            outRect.bottom = bottomSpace
            return
        }
        super.getItemOffsets(outRect, view, parent, state)
    }
}