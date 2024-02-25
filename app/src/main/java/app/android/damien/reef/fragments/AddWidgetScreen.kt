package app.android.damien.reef.fragments

import android.os.Bundle
import android.util.Log
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

        when (widgetType) {
            Constants.APEX -> {
                // Add Apex widget
            }

            Constants.FOCUSTRONIC -> {
                // Add Focustronic widget
            }

            Constants.CUSTOM -> {
                binding.addWidget.setOnClickListener {
                    findNavController().navigate(R.id.action_addWidgetScreen_to_customWidgetAddEditScreen)
                }

                initRecyclerAdapter()

                widgetsViewModel.widgets.observe(viewLifecycleOwner) {
                    adapter?.setData(it)
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

    override fun onCustomWidgetClick(data: CustomWidgetModel) {
        Log.d("AddWidgetScreen", "customWidgetClicked: $data")
        findNavController().navigate(
            R.id.action_addWidgetScreen_to_customWidgetAddEditScreen,
            getCustomWidgetBundle(data)
        )
    }

    private fun getCustomWidgetBundle(customWidgetModelObject: CustomWidgetModel): Bundle {
        val bundle = Bundle()
        bundle.putParcelable("customWidgetModelObject", customWidgetModelObject)
        return bundle
    }
}