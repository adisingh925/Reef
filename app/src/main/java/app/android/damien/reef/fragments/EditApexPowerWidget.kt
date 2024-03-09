package app.android.damien.reef.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.android.damien.reef.R
import app.android.damien.reef.adapter.SimpleListAdapter
import app.android.damien.reef.adapter.SimpleListAdapter2
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.FocustronicSingleValueType1WidgetModel
import app.android.damien.reef.databinding.FragmentEditApexPowerWidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.tabs.TabLayout
import org.json.JSONArray
import org.json.JSONObject

class EditApexPowerWidget : Fragment(), SimpleListAdapter2.OnItemClickListener {

    private val binding by lazy {
        FragmentEditApexPowerWidgetBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    private val recyclerView by lazy {
        binding.valuesRecyclerView
    }

    var ampsValue = 0.0f
    var voltsValue = 0.0f
    var wattsValue = 0.0f

    var ampsActualNamesList = mutableListOf<Pair<String, Int>>()
    var voltsActualNamesList = mutableListOf<Pair<String, Int>>()
    var wattsActualNamesList = mutableListOf<Pair<String, Int>>()

    private val adapter by lazy {
        SimpleListAdapter2(requireContext(), this)
    }

    private lateinit var apexPowerValueWidget: ApexPowerValuesWidgetModel
    private lateinit var apexData: JSONArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        apexPowerValueWidget = arguments?.getParcelable(Constants.APEX_POWER_VALUE_WIDGET)!!

        initApiData()

        initValuesRecyclerView()

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        adapter.setData(wattsActualNamesList)
                        adapter.notifyDataSetChanged()
                    }

                    1 -> {
                        adapter.setData(ampsActualNamesList)
                        adapter.notifyDataSetChanged()
                    }

                    2 -> {
                        adapter.setData(voltsActualNamesList)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        return binding.root
    }

    private fun initApiData() {
        apexData = JSONArray(SharedPreferences.read("apexData", "").toString())

        val keys1 = apexData.getJSONObject(0).keys()
        while (keys1.hasNext()) {
            val key = keys1.next() as String
            if (key.endsWith("a")) {
                if (apexPowerValueWidget.slot1SelectedValues?.contains(key) == true) {
                    ampsActualNamesList.add(Pair(key, 1))
                } else {
                    ampsActualNamesList.add(Pair(key, 0))
                }
            } else if (key.endsWith("v")) {
                if (apexPowerValueWidget.slot1SelectedValues?.contains(key) == true) {
                    voltsActualNamesList.add(Pair(key, 1))
                } else {
                    voltsActualNamesList.add(Pair(key, 0))
                }
            } else if (key.endsWith("w")) {
                if (apexPowerValueWidget.slot1SelectedValues?.contains(key) == true) {
                    wattsActualNamesList.add(Pair(key, 1))
                } else {
                    wattsActualNamesList.add(Pair(key, 0))
                }
            }
        }
    }

    private fun initValuesRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.setData(wattsActualNamesList)
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(data: Pair<String, Int>, position: Int) {
        Log.d("TAG", "onItemClick: $data $position")
        if (data.second == 0) {
            // If the second value is 0, toggle it to 1
            when {
                data.first.endsWith("w") -> {
                    wattsActualNamesList[position] = Pair(data.first, 1)
                    wattsValue += JSONObject(apexData.getJSONObject(0).toString()).get(data.first).toString().toFloat()
                    adapter.setData(wattsActualNamesList)
                    adapter.notifyItemRangeChanged(position, 1)
                }
                data.first.endsWith("v") -> {
                    voltsActualNamesList[position] = Pair(data.first, 1)
                    voltsValue += JSONObject(apexData.getJSONObject(0).toString()).get(data.first).toString().toFloat()
                    adapter.setData(voltsActualNamesList)
                    adapter.notifyItemRangeChanged(position, 1)
                }
                data.first.endsWith("a") -> {
                    ampsActualNamesList[position] = Pair(data.first, 1)
                    ampsValue += JSONObject(apexData.getJSONObject(0).toString()).get(data.first).toString().toFloat()
                    adapter.setData(ampsActualNamesList)
                    adapter.notifyItemRangeChanged(position, 1)
                }
            }
        } else {
            // If the second value is 1, toggle it to 0
            when {
                data.first.endsWith("w") -> {
                    wattsActualNamesList[position] = Pair(data.first, 0)
                    wattsValue -= JSONObject(apexData.getJSONObject(0).toString()).get(data.first).toString().toFloat()
                    adapter.setData(wattsActualNamesList)
                    adapter.notifyItemRangeChanged(position, 1)
                }
                data.first.endsWith("v") -> {
                    voltsActualNamesList[position] = Pair(data.first, 0)
                    voltsValue -= JSONObject(apexData.getJSONObject(0).toString()).get(data.first).toString().toFloat()
                    adapter.setData(voltsActualNamesList)
                    adapter.notifyItemRangeChanged(position, 1)
                }
                data.first.endsWith("a") -> {
                    ampsActualNamesList[position] = Pair(data.first, 0)
                    ampsValue -= JSONObject(apexData.getJSONObject(0).toString()).get(data.first).toString().toFloat()
                    adapter.setData(ampsActualNamesList)
                    adapter.notifyItemRangeChanged(position, 1)
                }
            }
        }

        binding.flaskBackgroundWidgetEditLayout.value1.text = wattsValue.toString()
        binding.flaskBackgroundWidgetEditLayout.value2.text = ampsValue.toString()
        binding.flaskBackgroundWidgetEditLayout.value3.text = voltsValue.toString()
    }
}