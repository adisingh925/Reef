package app.android.damien.reef.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.android.damien.reef.R
import app.android.damien.reef.adapter.SimpleListAdapter
import app.android.damien.reef.database_model.ApexGraphWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.databinding.ApexSingleValueType2BottomSheetBinding
import app.android.damien.reef.databinding.ApexTwoRectangleWidgetBottomSheetBinding
import app.android.damien.reef.databinding.FragmentEditGraphWidgetBinding
import app.android.damien.reef.databinding.GraphWidgetBottomSheetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONArray
import org.json.JSONObject
import yuku.ambilwarna.AmbilWarnaDialog


class EditGraphWidgetFragment : Fragment() {

    private val binding by lazy {
        FragmentEditGraphWidgetBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    private val recyclerView by lazy {
        binding.valuesRecyclerView
    }

    private val adapter by lazy {
        SimpleListAdapter(requireContext(), object : SimpleListAdapter.OnItemClickListener {
            override fun onItemClick(data: String) {
                actualName = data
                binding.flaskBackgroundWidgetEditLayout.heading.text = data
                value = JSONObject(apexData.getJSONObject(0).toString()).get(actualName).toString()
                    .toFloat()
                binding.flaskBackgroundWidgetEditLayout.value.text = value.toString()
            }
        })
    }

    private lateinit var apexGraphWidgetModel: ApexGraphWidgetModel
    private lateinit var apexData: JSONArray

    var unit = ""
    var actualName = ""
    var value = 0.0f
    var record = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        apexGraphWidgetModel = arguments?.getParcelable(Constants.APEX_GRAPH_WIDGET)!!

        initApiData()
        initValuesRecyclerView()

        actualName = apexGraphWidgetModel.actualName.toString()
        value = apexGraphWidgetModel.value
        unit = apexGraphWidgetModel.unit.toString()
        record = apexGraphWidgetModel.records.toString()

        binding.flaskBackgroundWidgetEditLayout.heading.text = actualName
        binding.flaskBackgroundWidgetEditLayout.unit.text = unit
        binding.flaskBackgroundWidgetEditLayout.value.text = value.toString()

        if (record.isNotBlank()) {
            val graph = record.split(",").map { it.toFloat() }

            val maxValue = graph.maxOrNull()
            val minValue = graph.minOrNull()

            binding.flaskBackgroundWidgetEditLayout.lowervalue.text = minValue.toString()
            binding.flaskBackgroundWidgetEditLayout.uppervalue.text = maxValue.toString()

            val entries = ArrayList<Entry>()
            graph.forEachIndexed { index, value ->
                entries.add(Entry((index + 1).toFloat(), value))
            }

            val dataSet = LineDataSet(entries, "Data Set")
            dataSet.color = Color.WHITE // Set color of the line to white
            dataSet.lineWidth = 5f // Increase the line width to 5 pixels
            dataSet.setDrawValues(false) // Hide values on points

            val lineData = LineData(dataSet)

            val chart = LineChart(context)
            chart.setBackgroundColor(Color.parseColor("#cc7700"))
            chart.data = lineData

            // Hide the description
            chart.description.isEnabled = false

            // Hide X and Y axis
            chart.axisLeft.isEnabled = false
            chart.axisRight.isEnabled = false
            chart.xAxis.isEnabled = false

            // Hide legend
            chart.legend.isEnabled = false

            chart.measure(
                View.MeasureSpec.makeMeasureSpec(500, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(500, View.MeasureSpec.EXACTLY)
            )
            chart.layout(0, 0, chart.measuredWidth, chart.measuredHeight)

            val chartBitmap = chart.getChartBitmap()

            binding.flaskBackgroundWidgetEditLayout.graph.setImageBitmap(chartBitmap)
        } else {
            binding.flaskBackgroundWidgetEditLayout.lowervalue.text = "0.0"
            binding.flaskBackgroundWidgetEditLayout.uppervalue.text = "0.0"
        }

        binding.flaskBackgroundWidgetEditLayout.unit.text = unit
        binding.flaskBackgroundWidgetEditLayout.value.text = value.toString()

        binding.saveButton.setOnClickListener {
            apexGraphWidgetModel.actualName = actualName
            apexGraphWidgetModel.value = value
            apexGraphWidgetModel.unit = unit
            apexGraphWidgetModel.records = value.toString()
            widgetsViewModel.updateApexGraphWidget(apexGraphWidgetModel)

            Toast.showSnackbar(requireView(), "Apex Single Value Type 2 Widget Updated")
            findNavController().popBackStack()
        }

        binding.deleteButton.setOnClickListener {
            SharedPreferences.write(
                Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,
                SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteApexGraphWidget(apexGraphWidgetModel)
            findNavController().popBackStack()
        }


        binding.flaskBackgroundWidgetEditLayout.layout.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = GraphWidgetBottomSheetBinding.inflate(layoutInflater)

            view.textInput.setText(binding.flaskBackgroundWidgetEditLayout.unit.text.toString())

            view.saveButton.setOnClickListener {
                if (view.textInput.text.toString().isEmpty()) {
                    view.textInput.error = "Field is required"
                    return@setOnClickListener
                }

                binding.flaskBackgroundWidgetEditLayout.unit.text = view.textInput.text.toString()
                unit = view.textInput.text.toString()

                dialog.dismiss()
            }

            dialog.setCancelable(true)
            dialog.setContentView(view.root)
            dialog.show()
        }

        return binding.root
    }

    private fun initApiData() {
        apexData = JSONArray(SharedPreferences.read("apexData", "").toString())
    }

    private fun initValuesRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.setData(getJsonKeys(apexData.getJSONObject(0)))
    }

    private fun getJsonKeys(jsonObject: JSONObject): List<String> {
        val keys = jsonObject.keys()
        val keyList = mutableListOf<String>()

        while (keys.hasNext()) {
            keyList.add(keys.next())
        }

        return keyList
    }
}