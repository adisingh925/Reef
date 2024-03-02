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

        if (SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0) != 0) {
            val childData = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0)) {
                childData.add(MyWidgetsChildModel(Constants.APEX_TWO_RECTANGLE_WIDGET, 1))
            }
            dummyData.add(MyWidgetsParentModel(Constants.APEX_TWO_RECTANGLE_WIDGET, childData))
        }

        if (SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0) != 0) {
            val childData1 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0)) {
                childData1.add(MyWidgetsChildModel(Constants.APEX_CIRCLE_WIDGET, 1))
            }
            dummyData.add(MyWidgetsParentModel(Constants.APEX_CIRCLE_WIDGET, childData1))
        }

        if (SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0) != 0) {
            val childData2 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0)) {
                childData2.add(MyWidgetsChildModel(Constants.APEX_FLASK_BACKGROUND_WIDGET, 1))
            }
            dummyData.add(MyWidgetsParentModel(Constants.APEX_FLASK_BACKGROUND_WIDGET, childData2))
        }

        if (SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 0) != 0) {
            val childData3 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(
                Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,
                0
            )) {
                childData3.add(MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 1))
            }
            dummyData.add(
                MyWidgetsParentModel(
                    Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,
                    childData3
                )
            )
        }

        if (SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0) != 0) {
            val childData4 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(
                Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,
                0
            )) {
                childData4.add(MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 1))
            }
            dummyData.add(
                MyWidgetsParentModel(
                    Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,
                    childData4
                )
            )
        }

        if (SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET, 0) != 0) {
            val childData5 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET, 0)) {
                childData5.add(MyWidgetsChildModel(Constants.APEX_POWER_VALUE_WIDGET, 1))
            }
            dummyData.add(MyWidgetsParentModel(Constants.APEX_POWER_VALUE_WIDGET, childData5))
        }

        if (SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0) != 0) {
            val childData6 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0)) {
                childData6.add(MyWidgetsChildModel(Constants.APEX_WATER_QUALITY_WIDGET, 1))
            }
            dummyData.add(MyWidgetsParentModel(Constants.APEX_WATER_QUALITY_WIDGET, childData6))
        }

        if (SharedPreferences.read(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET, 0) != 0) {
            val childData7 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET, 0)) {
                childData7.add(MyWidgetsChildModel(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET, 1))
            }
            dummyData.add(
                MyWidgetsParentModel(
                    Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET,
                    childData7
                )
            )
        }

        if (SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET, 0) != 0) {
            val childData8 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(
                Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,
                0
            )) {
                childData8.add(
                    MyWidgetsChildModel(
                        Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,
                        1
                    )
                )
            }
            dummyData.add(
                MyWidgetsParentModel(
                    Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,
                    childData8
                )
            )
        }

        if (SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET, 0) != 0) {
            val childData9 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(
                Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,
                0
            )) {
                childData9.add(
                    MyWidgetsChildModel(
                        Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,
                        1
                    )
                )
            }
            dummyData.add(
                MyWidgetsParentModel(
                    Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,
                    childData9
                )
            )
        }

        if (SharedPreferences.read(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET, 0) != 0) {
            val childData10 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(
                Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET,
                0
            )) {
                childData10.add(MyWidgetsChildModel(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET, 1))
            }
            dummyData.add(
                MyWidgetsParentModel(
                    Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET,
                    childData10
                )
            )
        }

        if (SharedPreferences.read(Constants.FOCUSTRONIC_GRID_WIDGET, 0) != 0) {
            val childData11 = ArrayList<MyWidgetsChildModel>()
            for (i in 0 until SharedPreferences.read(Constants.FOCUSTRONIC_GRID_WIDGET, 0)) {
                childData11.add(MyWidgetsChildModel(Constants.FOCUSTRONIC_GRID_WIDGET, 1))
            }
            dummyData.add(MyWidgetsParentModel(Constants.FOCUSTRONIC_GRID_WIDGET, childData11))
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.setData(dummyData)
    }
}