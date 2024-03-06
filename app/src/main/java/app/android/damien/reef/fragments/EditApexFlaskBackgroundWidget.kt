package app.android.damien.reef.fragments

import android.content.ClipDescription
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.android.damien.reef.R
import app.android.damien.reef.adapter.ValuesAdapter
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.databinding.FragmentEditApexFlaskBackgroundWidgetBinding
import app.android.damien.reef.model.ApexApiResponse
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject


class EditApexFlaskBackgroundWidget : Fragment() {

    private val binding by lazy {
        FragmentEditApexFlaskBackgroundWidgetBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    private val recyclerView by lazy{
        binding.valuesRecyclerView
    }

    private val adapter by lazy{
        ValuesAdapter(requireContext())
    }

    private lateinit var apexFlaskBackgroundWidget: ApexFlaskBackgroundWidgetModel
    private lateinit var apexData: JSONArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        apexFlaskBackgroundWidget = arguments?.getParcelable(Constants.APEX_FLASK_BACKGROUND_WIDGET)!!

        binding.flaskBackgroundWidgetEditLayout.slot1text.text = apexFlaskBackgroundWidget.slot1Name
        binding.flaskBackgroundWidgetEditLayout.slot2text.text = apexFlaskBackgroundWidget.slot2Name
        binding.flaskBackgroundWidgetEditLayout.slot3text.text = apexFlaskBackgroundWidget.slot3Name
        binding.flaskBackgroundWidgetEditLayout.slot1value.text = apexFlaskBackgroundWidget.slot1Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot2value.text = apexFlaskBackgroundWidget.slot2Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot3value.text = apexFlaskBackgroundWidget.slot3Value.toString()

        initApiData()

        binding.slot1.addTextChangedListener {
            binding.flaskBackgroundWidgetEditLayout.slot1value.text = it.toString()
            binding.flaskBackgroundWidgetEditLayout.slot1text.text = JSONObject(apexData.getJSONObject(0).toString()).get(it.toString()).toString()
        }

        binding.slot2.addTextChangedListener {
            binding.flaskBackgroundWidgetEditLayout.slot2value.text = it.toString()
            binding.flaskBackgroundWidgetEditLayout.slot2text.text = JSONObject(apexData.getJSONObject(0).toString()).get(it.toString()).toString()
        }

        binding.slot3.addTextChangedListener {
            binding.flaskBackgroundWidgetEditLayout.slot3value.text = it.toString()
            binding.flaskBackgroundWidgetEditLayout.slot3text.text = JSONObject(apexData.getJSONObject(0).toString()).get(it.toString()).toString()
        }

        val dragListener = View.OnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                    view.invalidate()
                    true
                }

                DragEvent.ACTION_DRAG_LOCATION -> {
                    true
                }

                DragEvent.ACTION_DRAG_EXITED -> {
                    view.invalidate()
                    true
                }

                DragEvent.ACTION_DROP -> {
                    val item = event.clipData.getItemAt(0)
                    val dragData = item.text
                    Log.d("TAG", "onCreateView: $dragData")

                    view?.invalidate()

                    val destination = view as TextView
                    Log.d("TAG", "onCreateView: ${destination.id}")
                    destination.text = dragData

                    true
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    view.invalidate()
                    true
                }

                else -> {
                    false
                }
            }
        }

        binding.valuesRecyclerView.setOnDragListener(dragListener)
        binding.slot1.setOnDragListener(dragListener)
        binding.slot2.setOnDragListener(dragListener)
        binding.slot3.setOnDragListener(dragListener)

        initValuesRecyclerView()

        binding.deleteButton.setOnClickListener {
            widgetsViewModel.deleteApexFlaskBackgroundWidget(apexFlaskBackgroundWidget)
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            widgetsViewModel.updateApexFlaskBackgroundWidget(apexFlaskBackgroundWidget)
        }

        return binding.root
    }

    private fun initApiData(){
        Log.d("TAG", "initApiData: ${SharedPreferences.read("apexData","")}")
        apexData = JSONArray(SharedPreferences.read("apexData","").toString())
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