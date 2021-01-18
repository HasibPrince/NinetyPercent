package com.mobile.ninetypercent.ui

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
import com.mobile.ninetypercent.ui.adapters.SizeAdapter
import com.mobile.ninetypercent.ui.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sizeAdapter: SizeAdapter
    lateinit var shapeAdapter: SizeAdapter
    lateinit var styleAdapter: SizeAdapter
    lateinit var materialAdapter: SizeAdapter
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
    }

    private fun setupSizeFilter() {
        sizeAdapter = SizeAdapter(filterViewModel.getSizeFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
//        binding.sizeOptions.layoutManager = GridLayoutManager(this, 2)
//        binding.sizeOptions.addItemDecoration(ItemDecoration(ViewUtils.dpToPx(this, 20), 0))
//        binding.sizeOptions.adapter = sizeAdapter

        setupRecyclerView(binding.sizeOptions, sizeAdapter, GridLayoutManager(this, 2),
            0, 20)
    }

    private fun setupShapeFilter() {
        shapeAdapter = SizeAdapter(filterViewModel.getShapeFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
//        binding.shapeOptions.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.shapeOptions.addItemDecoration(ItemDecoration(ViewUtils.dpToPx(this, 20), 0))
//        binding.shapeOptions.adapter = shapeAdapter
        setupRecyclerView(binding.shapeOptions, shapeAdapter, LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
            0, 20)
    }

    private fun setupMaterialFilter() {
        materialAdapter = SizeAdapter(filterViewModel.getMaterialFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
//        binding.materialOptions.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.materialOptions.addItemDecoration(ItemDecoration(ViewUtils.dpToPx(this, 20), 0))
//        binding.materialOptions.adapter = materialAdapter

        setupRecyclerView(binding.materialOptions, materialAdapter, LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
            0, 20)
    }

    private fun setupStyleFilter() {
        styleAdapter = SizeAdapter(filterViewModel.getStyleFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
//        binding.styleOptions.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.styleOptions.addItemDecoration(ItemDecoration(ViewUtils.dpToPx(this, 20), 0))
//        binding.styleOptions.adapter = styleAdapter

        setupRecyclerView(binding.styleOptions, styleAdapter, LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
            0, 20)

    }

    private fun setupColorFilter() {
        colorAdapter = ColorAdapter(filterViewModel.getColorFilters()) { filter, isSelected ->
            handleFilterSelection(filter, isSelected)
        }
        val flexboxLayoutManager = FlexboxLayoutManager(this)
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START

//        binding.colorOptions.layoutManager = flexboxLayoutManager
//        binding.colorOptions.addItemDecoration(
//            ItemDecoration(
//                ViewUtils.dpToPx(this, 20),
//                ViewUtils.dpToPx(this, 20)
//            )
//        )
//        binding.colorOptions.adapter = colorAdapter

        setupRecyclerView(binding.colorOptions, colorAdapter, flexboxLayoutManager, 20, 20)
    }

    private fun <V:RecyclerView.ViewHolder, T : RecyclerView.Adapter<V>> setupRecyclerView(
        recyclerView: RecyclerView,
        adapter: T,
        layoutManager: RecyclerView.LayoutManager,
        itemLeftMarginInDp: Int, itemBottomMarginInDp: Int
    ) {
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(ItemDecoration(
            ViewUtils.dpToPx(this, itemBottomMarginInDp),
            ViewUtils.dpToPx(this, itemLeftMarginInDp)
        ))
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
            shapeAdapter.sizeItems = filterViewModel.getShapeFilters()
            shapeAdapter.notifyDataSetChanged()

            styleAdapter.sizeItems = filterViewModel.getStyleFilters()
            styleAdapter.notifyDataSetChanged()

            sizeAdapter.sizeItems = filterViewModel.getSizeFilters()
            sizeAdapter.notifyDataSetChanged()

            colorAdapter.sizeItems = filterViewModel.getColorFilters()
            colorAdapter.notifyDataSetChanged()

            materialAdapter.sizeItems = filterViewModel.getMaterialFilters()
            materialAdapter.notifyDataSetChanged()
        }, 100)

    }
}

class ItemDecoration(private val bottomSpace: Int, private val leftSpace: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        outRect.left = space
//        outRect.right = space
        outRect.bottom = bottomSpace
        outRect.left = leftSpace
    }
}