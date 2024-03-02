package app.android.damien.reef.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.android.damien.reef.R
import app.android.damien.reef.adapter.MyWidgetsParentAdapter
import app.android.damien.reef.databinding.FragmentMyWidgetsBinding
import app.android.damien.reef.model.MyWidgetsChildModel
import app.android.damien.reef.model.MyWidgetsParentModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants


class MyWidgetsFragment : Fragment() {

    private val binding by lazy {
        FragmentMyWidgetsBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        MyWidgetsParentAdapter(
            requireContext(),
            object : MyWidgetsParentAdapter.OnItemClickListener {
                override fun onItemClick(data: MyWidgetsParentModel) {
                    Log.d("MyWidgetsFragment", "onItemClick: ${data.widgetType}")
                }
            })
    }

    private val recyclerView by lazy {
        binding.parentRecyclerView
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.addWidgets.setOnClickListener {
            findNavController().navigate(R.id.action_myWidgetsFragment_to_widgetTypeSelectionScreen)
        }

        initRecyclerview()

        return binding.root
    }

    private fun initRecyclerview() {
        val dummyData = ArrayList<MyWidgetsParentModel>()

        val apexCircleWidgets = ArrayList<MyWidgetsChildModel>()
        apexCircleWidgets.add(MyWidgetsChildModel(Constants.APEX_CIRCLE_WIDGET, 1))
        dummyData.add(MyWidgetsParentModel(Constants.APEX_CIRCLE_WIDGET, apexCircleWidgets))

        val apexWaterQualityWidgets = ArrayList<MyWidgetsChildModel>()
        apexWaterQualityWidgets.add(MyWidgetsChildModel(Constants.APEX_WATER_QUALITY_WIDGET, 1))
        dummyData.add(
            MyWidgetsParentModel(
                Constants.APEX_WATER_QUALITY_WIDGET,
                apexWaterQualityWidgets
            )
        )

        val apexPowerValueWidgets = ArrayList<MyWidgetsChildModel>()
        apexPowerValueWidgets.add(MyWidgetsChildModel(Constants.APEX_POWER_VALUE_WIDGET, 1))
        dummyData.add(
            MyWidgetsParentModel(
                Constants.APEX_POWER_VALUE_WIDGET,
                apexPowerValueWidgets
            )
        )

        val focustronicOneElementWidgets = ArrayList<MyWidgetsChildModel>()
        focustronicOneElementWidgets.add(MyWidgetsChildModel(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET, 1))
        dummyData.add(
            MyWidgetsParentModel(
                Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET,
                focustronicOneElementWidgets
            )
        )

        val focustronicTwoRectangleWidgets = ArrayList<MyWidgetsChildModel>()
        focustronicTwoRectangleWidgets.add(MyWidgetsChildModel(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET, 1))
        dummyData.add(
            MyWidgetsParentModel(
                Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET,
                focustronicTwoRectangleWidgets
            )
        )

        val focustronicSingleValueType1Widgets = ArrayList<MyWidgetsChildModel>()
        focustronicSingleValueType1Widgets.add(MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET, 1))
        dummyData.add(
            MyWidgetsParentModel(
                Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,
                focustronicSingleValueType1Widgets
            )
        )

        val focustronicGridWidgets = ArrayList<MyWidgetsChildModel>()
        focustronicGridWidgets.add(MyWidgetsChildModel(Constants.FOCUSTRONIC_GRID_WIDGET, 1))
        dummyData.add(
            MyWidgetsParentModel(
                Constants.FOCUSTRONIC_GRID_WIDGET,
                focustronicGridWidgets
            )
        )

//        val focustronicSingleValueType2Widgets = ArrayList<MyWidgetsChildModel>()
//        focustronicSingleValueType2Widgets.add(MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET, 1))
//        dummyData.add(
//            MyWidgetsParentModel(
//                Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,
//                focustronicSingleValueType2Widgets
//            )
//        )

        val apexTwoRectangleWidgets = ArrayList<MyWidgetsChildModel>()
        apexTwoRectangleWidgets.add(MyWidgetsChildModel(Constants.APEX_TWO_RECTANGLE_WIDGET, 1))
        dummyData.add(
            MyWidgetsParentModel(
                Constants.APEX_TWO_RECTANGLE_WIDGET,
                apexTwoRectangleWidgets
            )
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.setData(dummyData)
    }
}