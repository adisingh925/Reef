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
import app.android.damien.reef.adapter.MyWidgetsChildAdapter
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

    private val apexCircleWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val apexFlaskBackgroundWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val apexPowerValueWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val apexTwoRectangleWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val apexSingleValueType1Adapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val apexSingleValueType2Adapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val apexWaterQualityWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val focustronicOneElementWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val focustronicTwoRectangleWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val focustronicSingleValueTypeOneWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val focustronicSingleValueTypeTwoWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val focustronicGridWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsFragment", "Custom widget clicked")
                }
            })
    }

    private val apexCircleWidgetRecyclerView by lazy {
        binding.apexCircleWidgetRecyclerView
    }

    private val apexFlaskBackgroundWidgetRecyclerView by lazy {
        binding.apexFlaskBackgroundWidgetRecyclerView
    }

    private val apexPowerValueWidgetRecyclerView by lazy{
        binding.apexPowerValueWidgetRecyclerView
    }

    private val apexTwoRectangleWidgetRecyclerView by lazy{
        binding.apexTwoRectangleWidgetRecyclerView
    }

    private val apexSingleValueType1WidgetRecyclerView by lazy{
        binding.apexSingleValueType1WidgetRecyclerView
    }

    private val apexSingleValueType2WidgetRecyclerView by lazy{
        binding.apexSingleValueType2WidgetRecyclerView
    }

    private val apexWaterQualityWidgetRecyclerView by lazy{
        binding.apexWaterQualityWidgetRecyclerView
    }

    private val focustronicOneElementWidgetRecyclerView by lazy{
        binding.focustronic1ElementWidgetRecyclerView
    }

    private val focustronicTwoRectangleWidgetRecyclerView by lazy{
        binding.focustronic2RectangleWidgetRecyclerView
    }

    private val focustronicSingleValueTypeOneWidgetRecyclerView by lazy{
        binding.focustronicSingleValueType1WidgetRecyclerView
    }

    private val focustronicSingleValueTypeTwoWidgetRecyclerView by lazy{
        binding.focustronicSingleValueType2WidgetRecyclerView
    }

    private val focustronicGridWidgetRecyclerView by lazy{
        binding.focustronicGridWidgetRecyclerView
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.addWidgets.setOnClickListener {
            findNavController().navigate(R.id.action_myWidgetsFragment_to_widgetTypeSelectionScreen)
        }

        if(SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET,0) == 0){
            binding.apexCircleWidgetLayout.visibility = View.GONE
        }else{
            binding.apexCircleWidgetLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.APEX_CIRCLE_WIDGET),
                MyWidgetsChildModel(Constants.APEX_CIRCLE_WIDGET),
                MyWidgetsChildModel(Constants.APEX_CIRCLE_WIDGET)
            )
            initApexCircleRecyclerview(dummyData)
        }

        if(SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET,0) == 0) {
            binding.apexFlaskBackgroundLayout.visibility = View.GONE
        }else{
            binding.apexFlaskBackgroundLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.APEX_FLASK_BACKGROUND_WIDGET),
                MyWidgetsChildModel(Constants.APEX_FLASK_BACKGROUND_WIDGET),
                MyWidgetsChildModel(Constants.APEX_FLASK_BACKGROUND_WIDGET)
            )
            initApexFlaskBackgroundRecyclerview(dummyData)
        }

        if(SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET,0) == 0) {
            binding.apexPowerValueWidgetLayout.visibility = View.GONE
        }else{
            binding.apexPowerValueWidgetLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.APEX_POWER_VALUE_WIDGET),
                MyWidgetsChildModel(Constants.APEX_POWER_VALUE_WIDGET),
                MyWidgetsChildModel(Constants.APEX_POWER_VALUE_WIDGET)
            )
            initApexPowerValueWidgetAdapter(dummyData)
        }

        if(SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET,0) == 0) {
            binding.apexTwoRectangleWidgetLayout.visibility = View.GONE
        }else{
            binding.apexTwoRectangleWidgetLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.APEX_TWO_RECTANGLE_WIDGET),
                MyWidgetsChildModel(Constants.APEX_TWO_RECTANGLE_WIDGET),
                MyWidgetsChildModel(Constants.APEX_TWO_RECTANGLE_WIDGET)
            )
            initApexTwoRectangleWidgetAdapter(dummyData)
        }

        if(SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,0) == 0) {
            binding.apexSingleValueType1Layout.visibility = View.GONE
        }else{
            binding.apexSingleValueType1Layout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET),
                MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET),
                MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET)
            )
            initApexSingleValueType1WidgetAdapter(dummyData)
        }

        if(SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,0) == 0) {
            binding.apexSingleValueType2Layout.visibility = View.GONE
        }else{
            binding.apexSingleValueType2Layout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET),
                MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET),
                MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET)
            )
            initApexSingleValueType2WidgetAdapter(dummyData)
        }

        if(SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET,0) == 0) {
            binding.apexWaterQualityWidgetLayout.visibility = View.GONE
        }else{
            binding.apexWaterQualityWidgetLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.APEX_WATER_QUALITY_WIDGET),
                MyWidgetsChildModel(Constants.APEX_WATER_QUALITY_WIDGET),
                MyWidgetsChildModel(Constants.APEX_WATER_QUALITY_WIDGET)
            )
            initApexWaterQualityWidgetAdapter(dummyData)
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET,0) == 0) {
            binding.focustronic1ElementWidgetLayout.visibility = View.GONE
        }else{
            binding.focustronic1ElementWidgetLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET)
            )
            initFocustronicOneElementWidgetAdapter(dummyData)
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET,0) == 0) {
            binding.focustronic2RectangleWidgetLayout.visibility = View.GONE
        }else{
            binding.focustronic2RectangleWidgetLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET)
            )
            initFocustronicTwoRectangleWidgetAdapter(dummyData)
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,0) == 0) {
            binding.focustronicSingleValueType1WidgetLayout.visibility = View.GONE
        }else{
            binding.focustronicSingleValueType1WidgetLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET)
            )
            initFocustronicSingleValueTypeOneWidgetAdapter(dummyData)
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,0) == 0) {
            binding.focustronicSingleValueType2WidgetLayout.visibility = View.GONE
        }else{
            binding.focustronicSingleValueType2WidgetLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET)
            )
            initFocustronicSingleValueTypeTwoWidgetAdapter(dummyData)
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_GRID_WIDGET,0) == 0) {
            binding.focustronicGridWidgetLayout.visibility = View.GONE
        }else{
            binding.focustronicGridWidgetLayout.visibility = View.VISIBLE
            val dummyData = listOf(
                MyWidgetsChildModel(Constants.FOCUSTRONIC_GRID_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_GRID_WIDGET),
                MyWidgetsChildModel(Constants.FOCUSTRONIC_GRID_WIDGET)
            )
            initFocustronicGridWidgetAdapter(dummyData)
        }

        return binding.root
    }

    private fun initApexCircleRecyclerview(data : List<MyWidgetsChildModel>) {
        apexCircleWidgetRecyclerView.adapter = apexCircleWidgetAdapter
        apexCircleWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apexCircleWidgetAdapter.setData(data)
    }

    private fun initApexFlaskBackgroundRecyclerview(data : List<MyWidgetsChildModel>) {
        apexFlaskBackgroundWidgetRecyclerView.adapter = apexFlaskBackgroundWidgetAdapter
        apexFlaskBackgroundWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apexFlaskBackgroundWidgetAdapter.setData(data)
    }

    private fun initApexPowerValueWidgetAdapter(data : List<MyWidgetsChildModel>) {
        apexPowerValueWidgetRecyclerView.adapter = apexPowerValueWidgetAdapter
        apexPowerValueWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apexPowerValueWidgetAdapter.setData(data)
    }

    private fun initApexTwoRectangleWidgetAdapter(data : List<MyWidgetsChildModel>) {
        apexTwoRectangleWidgetRecyclerView.adapter = apexTwoRectangleWidgetAdapter
        apexTwoRectangleWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apexTwoRectangleWidgetAdapter.setData(data)
    }

    private fun initApexSingleValueType1WidgetAdapter(data : List<MyWidgetsChildModel>) {
        apexSingleValueType1WidgetRecyclerView.adapter = apexSingleValueType1Adapter
        apexSingleValueType1WidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apexSingleValueType1Adapter.setData(data)
    }

    private fun initApexSingleValueType2WidgetAdapter(data : List<MyWidgetsChildModel>) {
        apexSingleValueType2WidgetRecyclerView.adapter = apexSingleValueType2Adapter
        apexSingleValueType2WidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apexSingleValueType2Adapter.setData(data)
    }

    private fun initApexWaterQualityWidgetAdapter(data : List<MyWidgetsChildModel>) {
        apexWaterQualityWidgetRecyclerView.adapter = apexWaterQualityWidgetAdapter
        apexWaterQualityWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        apexWaterQualityWidgetAdapter.setData(data)
    }

    private fun initFocustronicGridWidgetAdapter(data : List<MyWidgetsChildModel>) {
        focustronicGridWidgetRecyclerView.adapter = focustronicGridWidgetAdapter
        focustronicGridWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        focustronicGridWidgetAdapter.setData(data)
    }

    private fun initFocustronicSingleValueTypeTwoWidgetAdapter(data : List<MyWidgetsChildModel>) {
        focustronicSingleValueTypeTwoWidgetRecyclerView.adapter = focustronicSingleValueTypeTwoWidgetAdapter
        focustronicSingleValueTypeTwoWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        focustronicSingleValueTypeTwoWidgetAdapter.setData(data)
    }

    private fun initFocustronicSingleValueTypeOneWidgetAdapter(data : List<MyWidgetsChildModel>) {
        focustronicSingleValueTypeOneWidgetRecyclerView.adapter = focustronicSingleValueTypeOneWidgetAdapter
        focustronicSingleValueTypeOneWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        focustronicSingleValueTypeOneWidgetAdapter.setData(data)
    }

    private fun initFocustronicTwoRectangleWidgetAdapter(data : List<MyWidgetsChildModel>) {
        focustronicTwoRectangleWidgetRecyclerView.adapter = focustronicTwoRectangleWidgetAdapter
        focustronicTwoRectangleWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        focustronicTwoRectangleWidgetAdapter.setData(data)
    }

    private fun initFocustronicOneElementWidgetAdapter(data : List<MyWidgetsChildModel>) {
        focustronicOneElementWidgetRecyclerView.adapter = focustronicOneElementWidgetAdapter
        focustronicOneElementWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        focustronicOneElementWidgetAdapter.setData(data)
    }
}