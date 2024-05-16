package app.android.damien.reef.fragments

import android.content.res.ColorStateList
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
import app.android.damien.reef.databinding.FragmentEditGraphWidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
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

        binding.flaskBackgroundWidgetEditLayout.unit.text = unit
        binding.flaskBackgroundWidgetEditLayout.value.text = value.toString()

        binding.saveButton.setOnClickListener {
            apexGraphWidgetModel.actualName = actualName
            apexGraphWidgetModel.value = value
            apexGraphWidgetModel.unit = unit
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