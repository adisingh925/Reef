package app.android.damien.reef.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.android.damien.reef.R
import app.android.damien.reef.adapter.MyWidgetsChildAdapter
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.database_model.FocustronicOneElementWidgetModel
import app.android.damien.reef.database_model.FocustronicGridWidgetModel
import app.android.damien.reef.database_model.FocustronicSingleValueType1WidgetModel
import app.android.damien.reef.database_model.FocustronicSingleValueType2WidgetModel
import app.android.damien.reef.database_model.FocustronicTwoRectangleWidgetModel
import app.android.damien.reef.databinding.FragmentMyWidgetsBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.viewmodel.WidgetsViewModel


class MyWidgetsFragment : Fragment(), MyWidgetsChildAdapter.OnItemClickListener {

    private val binding by lazy {
        FragmentMyWidgetsBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    private val apexCircleWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_CIRCLE_WIDGET,
            this
        )
    }

    private val apexFlaskBackgroundWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_FLASK_BACKGROUND_WIDGET,
            this
        )
    }

    private val apexPowerValueWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_POWER_VALUE_WIDGET,
            this
        )
    }

    private val apexTwoRectangleWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_TWO_RECTANGLE_WIDGET,
            this
        )
    }

    private val apexSingleValueType1Adapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,
            this
        )
    }

    private val apexSingleValueType2Adapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,
            this
        )
    }

    private val apexWaterQualityWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.APEX_WATER_QUALITY_WIDGET,
            this
        )
    }

    private val focustronicOneElementWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET,
            this
        )
    }

    private val focustronicTwoRectangleWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET,
            this
        )
    }

    private val focustronicSingleValueTypeOneWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,
            this
        )
    }

    private val focustronicSingleValueTypeTwoWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,
            this
        )
    }

    private val focustronicGridWidgetAdapter by lazy {
        MyWidgetsChildAdapter(
            requireContext(),
            Constants.FOCUSTRONIC_GRID_WIDGET,
            this
        )
    }

    private val apexCircleWidgetRecyclerView by lazy {
        binding.apexCircleWidgetRecyclerView
    }

    private val apexFlaskBackgroundWidgetRecyclerView by lazy {
        binding.apexFlaskBackgroundWidgetRecyclerView
    }

    private val apexPowerValueWidgetRecyclerView by lazy {
        binding.apexPowerValueWidgetRecyclerView
    }

    private val apexTwoRectangleWidgetRecyclerView by lazy {
        binding.apexTwoRectangleWidgetRecyclerView
    }

    private val apexSingleValueType1WidgetRecyclerView by lazy {
        binding.apexSingleValueType1WidgetRecyclerView
    }

    private val apexSingleValueType2WidgetRecyclerView by lazy {
        binding.apexSingleValueType2WidgetRecyclerView
    }

    private val apexWaterQualityWidgetRecyclerView by lazy {
        binding.apexWaterQualityWidgetRecyclerView
    }

    private val focustronicOneElementWidgetRecyclerView by lazy {
        binding.focustronic1ElementWidgetRecyclerView
    }

    private val focustronicTwoRectangleWidgetRecyclerView by lazy {
        binding.focustronic2RectangleWidgetRecyclerView
    }

    private val focustronicSingleValueTypeOneWidgetRecyclerView by lazy {
        binding.focustronicSingleValueType1WidgetRecyclerView
    }

    private val focustronicSingleValueTypeTwoWidgetRecyclerView by lazy {
        binding.focustronicSingleValueType2WidgetRecyclerView
    }

    private val focustronicGridWidgetRecyclerView by lazy {
        binding.focustronicGridWidgetRecyclerView
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.addWidgets.setOnClickListener {
            findNavController().navigate(R.id.action_myWidgetsFragment_to_widgetTypeSelectionScreen)
        }

        if (SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0) == 0) {
            binding.apexCircleWidgetLayout.visibility = View.GONE
        } else {
            binding.apexCircleWidgetLayout.visibility = View.VISIBLE
            initApexCircleRecyclerview()
        }

        if (SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0) == 0) {
            binding.apexFlaskBackgroundLayout.visibility = View.GONE
        } else {
            binding.apexFlaskBackgroundLayout.visibility = View.VISIBLE
            initApexFlaskBackgroundRecyclerview()
        }

        if (SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET, 0) == 0) {
            binding.apexPowerValueWidgetLayout.visibility = View.GONE
        } else {
            binding.apexPowerValueWidgetLayout.visibility = View.VISIBLE
            initApexPowerValueWidgetAdapter()
        }

        if (SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0) == 0) {
            binding.apexTwoRectangleWidgetLayout.visibility = View.GONE
        } else {
            binding.apexTwoRectangleWidgetLayout.visibility = View.VISIBLE
            initApexTwoRectangleWidgetAdapter()
        }

        if (SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 0) == 0) {
            binding.apexSingleValueType1Layout.visibility = View.GONE
        } else {
            binding.apexSingleValueType1Layout.visibility = View.VISIBLE
            initApexSingleValueType1WidgetAdapter()
        }

        if (SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0) == 0) {
            binding.apexSingleValueType2Layout.visibility = View.GONE
        } else {
            binding.apexSingleValueType2Layout.visibility = View.VISIBLE
            initApexSingleValueType2WidgetAdapter()
        }

        if (SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0) == 0) {
            binding.apexWaterQualityWidgetLayout.visibility = View.GONE
        } else {
            binding.apexWaterQualityWidgetLayout.visibility = View.VISIBLE
            initApexWaterQualityWidgetAdapter()
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

        widgetsViewModel.focustronicOneElementWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Focustronic One Element Widgets: $it")
            focustronicOneElementWidgetAdapter.setFocustronic1ElementWidgetData(it)
        }

        widgetsViewModel.focustronicTwoRectangleWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Focustronic Two Rectangle Widgets: $it")
            focustronicTwoRectangleWidgetAdapter.setFocustronicTwoRectangleWidgetData(it)
        }

        widgetsViewModel.focustronicSingleValueTypeOneWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Focustronic Single Value Type 1 Widgets: $it")
            focustronicSingleValueTypeOneWidgetAdapter.setFocustronicSingleValueType1WidgetData(it)
        }

        widgetsViewModel.focustronicSingleValueTypeTwoWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Focustronic Single Value Type 2 Widgets: $it")
            focustronicSingleValueTypeTwoWidgetAdapter.setFocustronicSingleValueType2WidgetData(it)
        }

        widgetsViewModel.focustronicGridWidgets.observe(viewLifecycleOwner) {
            Log.d("MyWidgetsFragment", "Focustronic Grid Widgets: $it")
            focustronicGridWidgetAdapter.setFocustronicGridWidgetData(it)
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET,0) == 0) {
            binding.focustronic1ElementWidgetLayout.visibility = View.GONE
        }else{
            binding.focustronic1ElementWidgetLayout.visibility = View.VISIBLE
            initFocustronicOneElementWidgetAdapter()
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET,0) == 0) {
            binding.focustronic2RectangleWidgetLayout.visibility = View.GONE
        }else{
            binding.focustronic2RectangleWidgetLayout.visibility = View.VISIBLE
            initFocustronicTwoRectangleWidgetAdapter()
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,0) == 0) {
            binding.focustronicSingleValueType1WidgetLayout.visibility = View.GONE
        }else{
            binding.focustronicSingleValueType1WidgetLayout.visibility = View.VISIBLE
            initFocustronicSingleValueTypeOneWidgetAdapter()
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,0) == 0) {
            binding.focustronicSingleValueType2WidgetLayout.visibility = View.GONE
        }else{
            binding.focustronicSingleValueType2WidgetLayout.visibility = View.VISIBLE
            initFocustronicSingleValueTypeTwoWidgetAdapter()
        }

        if(SharedPreferences.read(Constants.FOCUSTRONIC_GRID_WIDGET,0) == 0) {
            binding.focustronicGridWidgetLayout.visibility = View.GONE
        }else{
            binding.focustronicGridWidgetLayout.visibility = View.VISIBLE
            initFocustronicGridWidgetAdapter()
        }

        return binding.root
    }

    private fun initApexCircleRecyclerview() {
        apexCircleWidgetRecyclerView.adapter = apexCircleWidgetAdapter
        apexCircleWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexFlaskBackgroundRecyclerview() {
        apexFlaskBackgroundWidgetRecyclerView.adapter = apexFlaskBackgroundWidgetAdapter
        apexFlaskBackgroundWidgetRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexPowerValueWidgetAdapter() {
        apexPowerValueWidgetRecyclerView.adapter = apexPowerValueWidgetAdapter
        apexPowerValueWidgetRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexTwoRectangleWidgetAdapter() {
        apexTwoRectangleWidgetRecyclerView.adapter = apexTwoRectangleWidgetAdapter
        apexTwoRectangleWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexSingleValueType1WidgetAdapter() {
        apexSingleValueType1WidgetRecyclerView.adapter = apexSingleValueType1Adapter
        apexSingleValueType1WidgetRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexSingleValueType2WidgetAdapter() {
        apexSingleValueType2WidgetRecyclerView.adapter = apexSingleValueType2Adapter
        apexSingleValueType2WidgetRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initApexWaterQualityWidgetAdapter() {
        apexWaterQualityWidgetRecyclerView.adapter = apexWaterQualityWidgetAdapter
        apexWaterQualityWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.APEX_FLASK_BACKGROUND_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editApexFlaskBackgroundWidget, bundle)
    }

    override fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.APEX_POWER_VALUE_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editApexPowerWidget, bundle)
    }

    override fun onApexCircleWidgetClick(data: ApexCircleWidgetModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.APEX_CIRCLE_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editApexCircleWidget, bundle)
    }

    override fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.APEX_TWO_RECTANGLE_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editApexTwoRectangleWidget, bundle)
    }

    override fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editApexSingleValueType1Widget, bundle)
    }

    override fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editApexSingleValueType2, bundle)
    }

    override fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.APEX_WATER_QUALITY_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editApexWaterQualityWidget, bundle)
    }

    override fun onFocustronic1ElementWidgetClick(data: FocustronicOneElementWidgetModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editFocustronicSingleElementWidget, bundle)
    }

    override fun onFocustronicGridWidgetClick(data: FocustronicGridWidgetModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.FOCUSTRONIC_GRID_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editFocustronicGridWidget, bundle)
    }

    override fun onFocustronicSingleValueType1WidgetClick(data: FocustronicSingleValueType1WidgetModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editFocustronicSingleValuetype1Widget, bundle)
    }

    override fun onFocustronicSingleValueType2WidgetClick(data: FocustronicSingleValueType2WidgetModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editFocustronicSingleValueType2Widget, bundle)
    }

    override fun onFocustronicTwoRectangleWidgetClick(data: FocustronicTwoRectangleWidgetModel) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET, data)
        findNavController().navigate(R.id.action_myWidgetsFragment_to_editFocustronicDoubleRectangleWidget, bundle)
    }

    private fun initFocustronicGridWidgetAdapter() {
        focustronicGridWidgetRecyclerView.adapter = focustronicGridWidgetAdapter
        focustronicGridWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initFocustronicSingleValueTypeTwoWidgetAdapter() {
        focustronicSingleValueTypeTwoWidgetRecyclerView.adapter = focustronicSingleValueTypeTwoWidgetAdapter
        focustronicSingleValueTypeTwoWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initFocustronicSingleValueTypeOneWidgetAdapter() {
        focustronicSingleValueTypeOneWidgetRecyclerView.adapter = focustronicSingleValueTypeOneWidgetAdapter
        focustronicSingleValueTypeOneWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initFocustronicTwoRectangleWidgetAdapter() {
        focustronicTwoRectangleWidgetRecyclerView.adapter = focustronicTwoRectangleWidgetAdapter
        focustronicTwoRectangleWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initFocustronicOneElementWidgetAdapter() {
        focustronicOneElementWidgetRecyclerView.adapter = focustronicOneElementWidgetAdapter
        focustronicOneElementWidgetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}