package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.android.damien.reef.R
import app.android.damien.reef.adapter.WidgetAdapter
import app.android.damien.reef.database_model.CustomWidgetModel
import app.android.damien.reef.databinding.FragmentAddWidgetScreenBinding
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.viewmodel.WidgetsViewModel
import kotlin.properties.Delegates


class AddWidgetScreen : Fragment(), WidgetAdapter.OnItemClickListener {

    private val binding by lazy {
        FragmentAddWidgetScreenBinding.inflate(layoutInflater)
    }

    private var widgetType by Delegates.notNull<Int>()

    private val recyclerView by lazy {
        binding.widgetRecyclerView
    }

    private val adapter by lazy {
        context?.let { WidgetAdapter(it, this) }
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        widgetType = arguments?.getInt("widgetType") ?: Constants.CUSTOM

        initRecyclerAdapter()

        widgetsViewModel.widgets.observe(viewLifecycleOwner) {
            adapter?.setData(it)
        }

        binding.addWidget.setOnClickListener {
            when (widgetType) {
                Constants.APEX -> {
                    // Add Apex widget
                }
                Constants.ALKATRONIC -> {
                    // Add Alkatronic widget
                }
                Constants.MASTERTRONIC -> {
                    // Add Mastertronic widget
                }
                Constants.CUSTOM -> {
                    findNavController().navigate(R.id.action_addWidgetScreen_to_customWidgetAddEditScreen)
                }
            }
        }

        return binding.root
    }

    private fun initRecyclerAdapter() {
        recyclerView.adapter = adapter
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = StaggeredGridLayoutManager(
            2, LinearLayoutManager.VERTICAL
        )
    }

    override fun onItemClick(data: CustomWidgetModel) {
        TODO("Not yet implemented")
    }
}