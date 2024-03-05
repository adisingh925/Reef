package app.android.damien.reef.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.android.damien.reef.R
import app.android.damien.reef.adapter.MyWidgetsChildAdapter
import app.android.damien.reef.adapter.MyWidgetsParentAdapter
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.databinding.FragmentMyWidgetsBinding
import app.android.damien.reef.model.MyWidgetsChildModel
import app.android.damien.reef.model.MyWidgetsParentModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.viewmodel.WidgetsViewModel


class MyWidgetsFragment : Fragment() {

    private val binding by lazy {
        FragmentMyWidgetsBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy{
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    private val apexCircleWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_CIRCLE_WIDGET,
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexCircleWidgetClick(data: ApexCircleWidgetModel) {
                    Log.d("MyWidgetsFragment", data.toString())
                }

                override fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget) {
                    TODO("Not yet implemented")
                }
            })
    }

    private val apexFlaskBackgroundWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_FLASK_BACKGROUND_WIDGET,
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel) {
                    findNavController().navigate(R.id.action_myWidgetsFragment_to_editApexFlaskBackgroundWidget)
                }

                override fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexCircleWidgetClick(data: ApexCircleWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget) {
                    TODO("Not yet implemented")
                }
            })
    }

    private val apexPowerValueWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_POWER_VALUE_WIDGET,
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel) {
                    Log.d("MyWidgetsFragment", data.toString())
                }

                override fun onApexCircleWidgetClick(data: ApexCircleWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget) {
                    TODO("Not yet implemented")
                }

            })
    }

    private val apexTwoRectangleWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_TWO_RECTANGLE_WIDGET,
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexCircleWidgetClick(data: ApexCircleWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets) {
                    Log.d("MyWidgetsFragment", data.toString())
                }

                override fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget) {
                    TODO("Not yet implemented")
                }

            })
    }

    private val apexSingleValueType1Adapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexCircleWidgetClick(data: ApexCircleWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel) {
                    Log.d("MyWidgetsFragment", data.toString())
                }

                override fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget) {
                    TODO("Not yet implemented")
                }
            })
    }

    private val apexSingleValueType2Adapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexCircleWidgetClick(data: ApexCircleWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel) {
                    Log.d("MyWidgetsFragment", data.toString())
                }

                override fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget) {
                    TODO("Not yet implemented")
                }
            })
    }

    private val apexWaterQualityWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_WATER_QUALITY_WIDGET,
            object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexCircleWidgetClick(data: ApexCircleWidgetModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel) {
                    TODO("Not yet implemented")
                }

                override fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget) {
                    Log.d("MyWidgetsFragment", data.toString())
                }
            })
    }
//
//    private val focustronicOneElementWidgetAdapter by lazy {
//        MyWidgetsChildAdapter(
//            requireContext(),
//            object : MyWidgetsChildAdapter.OnItemClickListener {
//                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
//                    Log.d("MyWidgetsFragment", "Custom widget clicked")
//                }
//            })
//    }
//
//    private val focustronicTwoRectangleWidgetAdapter by lazy {
//        MyWidgetsChildAdapter(
//            requireContext(),
//            object : MyWidgetsChildAdapter.OnItemClickListener {
//                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
//                    Log.d("MyWidgetsFragment", "Custom widget clicked")
//                }
//            })
//    }
//
//    private val focustronicSingleValueTypeOneWidgetAdapter by lazy {
//        MyWidgetsChildAdapter(
//            requireContext(),
//            object : MyWidgetsChildAdapter.OnItemClickListener {
//                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
//                    Log.d("MyWidgetsFragment", "Custom widget clicked")
//                }
//            })
//    }
//
//    private val focustronicSingleValueTypeTwoWidgetAdapter by lazy {
//        MyWidgetsChildAdapter(
//            requireContext(),
//            object : MyWidgetsChildAdapter.OnItemClickListener {
//                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
//                    Log.d("MyWidgetsFragment", "Custom widget clicked")
//                }
//            })
//    }
//
//    private val focustronicGridWidgetAdapter by lazy {
//        MyWidgetsChildAdapter(
//            requireContext(),
//            object : MyWidgetsChildAdapter.OnItemClickListener {
//                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
//                    Log.d("MyWidgetsFragment", "Custom widget clicked")
//                }
//            })
//    }

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

        widgetsViewModel.apexFlaskBackgroundWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Apex Flask Background Widgets: $it")
            apexFlaskBackgroundWidgetAdapter.setApexFlaskBackgroundWidgetData(it)
        }

        widgetsViewModel.apexPowerValuesWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Apex Power Values Widgets: $it")
            apexPowerValueWidgetAdapter.setApexPowerValuesWidgetData(it)
        }

        widgetsViewModel.apexCircleWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Apex Circle Widgets: $it")
            apexCircleWidgetAdapter.setApexCircleWidgetData(it)
        }

        widgetsViewModel.apexTwoRectangleWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Apex Two Rectangle Widgets: $it")
            apexTwoRectangleWidgetAdapter.setApexTwoRectangleWidgetData(it)
        }

        widgetsViewModel.apexSingleValueTypeOneWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Apex Single Value Type 1 Widgets: $it")
            apexSingleValueType1Adapter.setApexSingleValueTypeOneWidgetData(it)
        }

        widgetsViewModel.apexSingleValueTypeTwoWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Apex Single Value Type 2 Widgets: $it")
            apexSingleValueType2Adapter.setApexSingleValueTypeTwoWidgetData(it)
        }

        widgetsViewModel.apexWaterQualityWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Apex Water Quality Widgets: $it")
            apexWaterQualityWidgetAdapter.setApexWaterQualityWidgetData(it)
        }

        if(SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET,0) == 0){
            binding.apexCircleWidgetLayout.visibility = View.GONE
        }else{
            binding.apexCircleWidgetLayout.visibility = View.VISIBLE
            initApexCircleRecyclerview()
        }

        if(SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET,0) == 0) {
            binding.apexFlaskBackgroundLayout.visibility = View.GONE
        }else{
            binding.apexFlaskBackgroundLayout.visibility = View.VISIBLE
            initApexFlaskBackgroundRecyclerview()
        }

        if(SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET,0) == 0) {
            binding.apexPowerValueWidgetLayout.visibility = View.GONE
        }else{
            binding.apexPowerValueWidgetLayout.visibility = View.VISIBLE
            initApexPowerValueWidgetAdapter()
        }

        if(SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET,0) == 0) {
            binding.apexTwoRectangleWidgetLayout.visibility = View.GONE
        }else{
            binding.apexTwoRectangleWidgetLayout.visibility = View.VISIBLE
            initApexTwoRectangleWidgetAdapter()
        }

        if(SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,0) == 0) {
            binding.apexSingleValueType1Layout.visibility = View.GONE
        }else{
            binding.apexSingleValueType1Layout.visibility = View.VISIBLE
            initApexSingleValueType1WidgetAdapter()
        }

        if(SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,0) == 0) {
            binding.apexSingleValueType2Layout.visibility = View.GONE
        }else{
            binding.apexSingleValueType2Layout.visibility = View.VISIBLE
            initApexSingleValueType2WidgetAdapter()
        }

        if(SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET,0) == 0) {
            binding.apexWaterQualityWidgetLayout.visibility = View.GONE
        }else{
            binding.apexWaterQualityWidgetLayout.visibility = View.VISIBLE
            initApexWaterQualityWidgetAdapter()
        }
//
//        if(SharedPreferences.read(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET,0) == 0) {
//            binding.focustronic1ElementWidgetLayout.visibility = View.GONE
//        }else{
//            binding.focustronic1ElementWidgetLayout.visibility = View.VISIBLE
//            val dummyData = listOf(
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET)
//            )
//            initFocustronicOneElementWidgetAdapter(dummyData)
//        }
//
//        if(SharedPreferences.read(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET,0) == 0) {
//            binding.focustronic2RectangleWidgetLayout.visibility = View.GONE
//        }else{
//            binding.focustronic2RectangleWidgetLayout.visibility = View.VISIBLE
//            val dummyData = listOf(
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET)
//            )
//            initFocustronicTwoRectangleWidgetAdapter(dummyData)
//        }
//
//        if(SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,0) == 0) {
//            binding.focustronicSingleValueType1WidgetLayout.visibility = View.GONE
//        }else{
//            binding.focustronicSingleValueType1WidgetLayout.visibility = View.VISIBLE
//            val dummyData = listOf(
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET)
//            )
//            initFocustronicSingleValueTypeOneWidgetAdapter(dummyData)
//        }
//
//        if(SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,0) == 0) {
//            binding.focustronicSingleValueType2WidgetLayout.visibility = View.GONE
//        }else{
//            binding.focustronicSingleValueType2WidgetLayout.visibility = View.VISIBLE
//            val dummyData = listOf(
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET)
//            )
//            initFocustronicSingleValueTypeTwoWidgetAdapter(dummyData)
//        }
//
//        if(SharedPreferences.read(Constants.FOCUSTRONIC_GRID_WIDGET,0) == 0) {
//            binding.focustronicGridWidgetLayout.visibility = View.GONE
//        }else{
//            binding.focustronicGridWidgetLayout.visibility = View.VISIBLE
//            val dummyData = listOf(
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_GRID_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_GRID_WIDGET),
//                MyWidgetsChildModel(Constants.FOCUSTRONIC_GRID_WIDGET)
//            )
//            initFocustronicGridWidgetAdapter(dummyData)
//        }

        return binding.root
    }

    private fun initApexCircleRecyclerview() {
        apexCircleWidgetRecyclerView.adapter = apexCircleWidgetAdapter
        apexCircleWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexFlaskBackgroundRecyclerview() {
        apexFlaskBackgroundWidgetRecyclerView.adapter = apexFlaskBackgroundWidgetAdapter
        apexFlaskBackgroundWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexPowerValueWidgetAdapter() {
        apexPowerValueWidgetRecyclerView.adapter = apexPowerValueWidgetAdapter
        apexPowerValueWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexTwoRectangleWidgetAdapter() {
        apexTwoRectangleWidgetRecyclerView.adapter = apexTwoRectangleWidgetAdapter
        apexTwoRectangleWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexSingleValueType1WidgetAdapter() {
        apexSingleValueType1WidgetRecyclerView.adapter = apexSingleValueType1Adapter
        apexSingleValueType1WidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexSingleValueType2WidgetAdapter() {
        apexSingleValueType2WidgetRecyclerView.adapter = apexSingleValueType2Adapter
        apexSingleValueType2WidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexWaterQualityWidgetAdapter() {
        apexWaterQualityWidgetRecyclerView.adapter = apexWaterQualityWidgetAdapter
        apexWaterQualityWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

//    private fun initFocustronicGridWidgetAdapter(data : List<MyWidgetsChildModel>) {
//        focustronicGridWidgetRecyclerView.adapter = focustronicGridWidgetAdapter
//        focustronicGridWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        focustronicGridWidgetAdapter.setData(data)
//    }
//
//    private fun initFocustronicSingleValueTypeTwoWidgetAdapter(data : List<MyWidgetsChildModel>) {
//        focustronicSingleValueTypeTwoWidgetRecyclerView.adapter = focustronicSingleValueTypeTwoWidgetAdapter
//        focustronicSingleValueTypeTwoWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        focustronicSingleValueTypeTwoWidgetAdapter.setData(data)
//    }
//
//    private fun initFocustronicSingleValueTypeOneWidgetAdapter(data : List<MyWidgetsChildModel>) {
//        focustronicSingleValueTypeOneWidgetRecyclerView.adapter = focustronicSingleValueTypeOneWidgetAdapter
//        focustronicSingleValueTypeOneWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        focustronicSingleValueTypeOneWidgetAdapter.setData(data)
//    }
//
//    private fun initFocustronicTwoRectangleWidgetAdapter(data : List<MyWidgetsChildModel>) {
//        focustronicTwoRectangleWidgetRecyclerView.adapter = focustronicTwoRectangleWidgetAdapter
//        focustronicTwoRectangleWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        focustronicTwoRectangleWidgetAdapter.setData(data)
//    }
//
//    private fun initFocustronicOneElementWidgetAdapter(data : List<MyWidgetsChildModel>) {
//        focustronicOneElementWidgetRecyclerView.adapter = focustronicOneElementWidgetAdapter
//        focustronicOneElementWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        focustronicOneElementWidgetAdapter.setData(data)
//    }
}