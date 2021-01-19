package com.mobile.ninetypercent.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mobile.ninetypercent.R
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.databinding.ActivityMainBinding
import com.mobile.ninetypercent.ui.adapters.ColorAdapter
import com.mobile.ninetypercent.ui.adapters.ItemDecoration
import com.mobile.ninetypercent.ui.adapters.FilterAdapter
import com.mobile.ninetypercent.ui.utils.EventObserver
import com.mobile.ninetypercent.ui.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var filterAdapter: FilterAdapter
    lateinit var shapeAdapter: FilterAdapter
    lateinit var styleAdapter: FilterAdapter
    lateinit var materialAdapter: FilterAdapter
    lateinit var colorAdapter: ColorAdapter
    private val filterViewModel: FilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        setupSizeFilter()
        setupShapeFilter()
        setupStyleFilter()
        setupColorFilter()
        setupMaterialFilter()

        filterViewModel.filterClickEvent.observe(this, EventObserver {
            openDrawer()
        })

        binding.menu.setOnClickListener {
            handleDrawer()
        }
    }

    private fun handleDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer()
        } else {
            openDrawer()
        }
    }

    private fun closeDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.START, true)
    }

    private fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START, true)
    }

    private fun setupSizeFilter() {
        filterAdapter = FilterAdapter(filterViewModel.getSizeFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
        setupRecyclerView(
            binding.sizeOptions, filterAdapter, GridLayoutManager(this, 2),
            0, 20
        )
    }

    private fun setupShapeFilter() {
        shapeAdapter = FilterAdapter(filterViewModel.getShapeFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
        setupRecyclerView(
            binding.shapeOptions,
            shapeAdapter,
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
            0,
            20
        )
    }

    private fun setupMaterialFilter() {
        materialAdapter = FilterAdapter(filterViewModel.getMaterialFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
        setupRecyclerView(
            binding.materialOptions,
            materialAdapter,
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
            0,
            20
        )
    }

    private fun setupStyleFilter() {
        styleAdapter = FilterAdapter(filterViewModel.getStyleFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
        setupRecyclerView(
            binding.styleOptions,
            styleAdapter,
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
            0,
            20
        )

    }

    private fun setupColorFilter() {
        colorAdapter = ColorAdapter(filterViewModel.getColorFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
        val flexboxLayoutManager = FlexboxLayoutManager(this)
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START

        setupRecyclerView(binding.colorOptions, colorAdapter, flexboxLayoutManager, 20, 20)
    }

    private fun <V : RecyclerView.ViewHolder, T : RecyclerView.Adapter<V>> setupRecyclerView(
        recyclerView: RecyclerView,
        adapter: T,
        layoutManager: RecyclerView.LayoutManager,
        itemLeftMarginInDp: Int, itemBottomMarginInDp: Int
    ) {
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            ItemDecoration(
                ViewUtils.dpToPx(this, itemBottomMarginInDp),
                ViewUtils.dpToPx(this, itemLeftMarginInDp)
            )
        )
        recyclerView.adapter = adapter
    }

    private fun handleFilterSelection(
        filter: Value,
        isSelected: Boolean
    ) {
        filterViewModel.updateSelectedFilter(filter, isSelected)
        updateAllFilters()
    }

    private fun updateAllFilters() {
        Handler(Looper.getMainLooper()).postDelayed({
            shapeAdapter.filterItems = filterViewModel.getShapeFilters()
            shapeAdapter.notifyDataSetChanged()

            styleAdapter.filterItems = filterViewModel.getStyleFilters()
            styleAdapter.notifyDataSetChanged()

            filterAdapter.filterItems = filterViewModel.getSizeFilters()
            filterAdapter.notifyDataSetChanged()

            colorAdapter.sizeItems = filterViewModel.getColorFilters()
            colorAdapter.notifyDataSetChanged()

            materialAdapter.filterItems = filterViewModel.getMaterialFilters()
            materialAdapter.notifyDataSetChanged()
        }, 100)

    }
}