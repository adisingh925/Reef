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
import app.android.damien.reef.database_model.FocustronicSingleValueType1WidgetModel
import app.android.damien.reef.databinding.FragmentEditApexPowerWidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.tabs.TabLayout
import org.json.JSONArray
import org.json.JSONObject


class EditApexPowerWidget : Fragment() {

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

    var ampsActualNamesList = mutableListOf<String>()
    var voltsActualNamesList = mutableListOf<String>()
    var wattsActualNamesList = mutableListOf<String>()

    private val adapter by lazy {
        SimpleListAdapter(requireContext(), object : SimpleListAdapter.OnItemClickListener {
            override fun onItemClick(data: String) {

            }
        })
    }

    private lateinit var focustronicSingleValueType1Widget: FocustronicSingleValueType1WidgetModel
    private lateinit var apexData: JSONArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initApiData()

        initValuesRecyclerView()

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        adapter.setData(ampsActualNamesList)
                    }

                    1 -> {
                        adapter.setData(voltsActualNamesList)
                    }

                    2 -> {
                        adapter.setData(wattsActualNamesList)
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
                ampsActualNamesList.add(key)
            } else if (key.endsWith("v")) {
                voltsActualNamesList.add(key)
            } else if (key.endsWith("w")) {
                wattsActualNamesList.add(key)
            }
        }
    }

    private fun initValuesRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.setData(wattsActualNamesList)
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