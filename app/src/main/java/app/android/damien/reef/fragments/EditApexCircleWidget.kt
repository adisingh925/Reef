package app.android.damien.reef.fragments

import android.content.ClipDescription
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.android.damien.reef.R
import app.android.damien.reef.adapter.ValuesAdapter
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.databinding.ApexFlaskBackgroundWidgetEditDialogBinding
import app.android.damien.reef.databinding.FragmentEditApexCircleWidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONArray
import org.json.JSONObject

class EditApexCircleWidget : Fragment() {

    private val binding by lazy{
        FragmentEditApexCircleWidgetBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    private val recyclerView by lazy {
        binding.valuesRecyclerView
    }

    private val adapter by lazy {
        ValuesAdapter(requireContext())
    }

    private lateinit var apexCircleWidget: ApexCircleWidgetModel
    private lateinit var apexData: JSONArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        apexCircleWidget = arguments?.getParcelable(Constants.APEX_CIRCLE_WIDGET)!!

        if (apexCircleWidget.slot1GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot1name.text = apexCircleWidget.slot1ActualName
            binding.slot1.text = apexCircleWidget.slot1ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot1name.text = apexCircleWidget.slot1GivenName
            binding.slot1.text = apexCircleWidget.slot1GivenName
        }

        if (apexCircleWidget.slot2GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot2name.text = apexCircleWidget.slot2ActualName
            binding.slot2.text = apexCircleWidget.slot2ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot2name.text = apexCircleWidget.slot2GivenName
            binding.slot2.text = apexCircleWidget.slot2GivenName
        }

        if (apexCircleWidget.slot3GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot3name.text = apexCircleWidget.slot3ActualName
            binding.slot3.text = apexCircleWidget.slot3ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot3name.text = apexCircleWidget.slot3GivenName
            binding.slot3.text = apexCircleWidget.slot3GivenName
        }

        binding.flaskBackgroundWidgetEditLayout.slot1value.text = apexCircleWidget.slot1Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot2value.text = apexCircleWidget.slot2Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot3value.text = apexCircleWidget.slot3Value.toString()

        initApiData()

        binding.slot1.addTextChangedListener {
            if(JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())){
                apexCircleWidget.slot1ActualName = it.toString()
                apexCircleWidget.slot1GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.slot1name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot1name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot1value.text =
                JSONObject(apexData.getJSONObject(0).toString()).get(apexCircleWidget.slot1ActualName).toString().toFloat()
                    .toString()

            apexCircleWidget.slot1Value = apexCircleWidget.slot1ActualName?.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot2.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                apexCircleWidget.slot2ActualName = it.toString()
                apexCircleWidget.slot2GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.slot2name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot2name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot2value.text =
                apexCircleWidget.slot2ActualName?.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            apexCircleWidget.slot2Value = apexCircleWidget.slot2ActualName?.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot3.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                apexCircleWidget.slot3ActualName = it.toString()
                apexCircleWidget.slot3GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.slot3name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot3name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot3value.text =
                apexCircleWidget.slot3ActualName?.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            apexCircleWidget.slot3Value = apexCircleWidget.slot3ActualName?.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot1.setOnClickListener {
            if (!binding.slot1.text.contains("Slot")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexFlaskBackgroundWidgetEditDialogBinding.inflate(layoutInflater)

                view.textInput.setText(binding.slot1.text.toString())

                view.saveButton.setOnClickListener {
                    if (view.textInput.text.toString().isEmpty()) {
                        view.textInput.error = "Field is required"
                        return@setOnClickListener
                    }

                    apexCircleWidget.slot1GivenName = view.textInput.text.toString()
                    binding.slot1.text = view.textInput.text.toString()
                    dialog.dismiss()
                }

                dialog.setCancelable(true)
                dialog.setContentView(view.root)
                dialog.show()
            } else {
                Toast.showSnackbar(binding.root, "Please add a value to the slot first")
            }
        }

        binding.slot2.setOnClickListener {
            if (!binding.slot2.text.contains("Slot")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexFlaskBackgroundWidgetEditDialogBinding.inflate(layoutInflater)

                view.textInput.setText(binding.slot2.text.toString())

                view.saveButton.setOnClickListener {
                    if (view.textInput.text.toString().isEmpty()) {
                        view.textInput.error = "Field is required"
                        return@setOnClickListener
                    }

                    apexCircleWidget.slot2GivenName = view.textInput.text.toString()
                    binding.slot2.text = view.textInput.text.toString()
                    dialog.dismiss()
                }

                dialog.setCancelable(true)
                dialog.setContentView(view.root)
                dialog.show()
            } else {
                Toast.showSnackbar(binding.root, "Please add a value to the slot first")
            }
        }

        binding.slot3.setOnClickListener {
            if (!binding.slot3.text.contains("Slot")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexFlaskBackgroundWidgetEditDialogBinding.inflate(layoutInflater)

                view.textInput.setText(binding.slot3.text.toString())

                view.saveButton.setOnClickListener {
                    if (view.textInput.text.toString().isEmpty()) {
                        view.textInput.error = "Field is required"
                        return@setOnClickListener
                    }

                    apexCircleWidget.slot3GivenName = view.textInput.text.toString()
                    binding.slot3.text = view.textInput.text.toString()
                    dialog.dismiss()
                }

                dialog.setCancelable(true)
                dialog.setContentView(view.root)
                dialog.show()
            } else {
                Toast.showSnackbar(binding.root, "Please add a value to the slot first")
            }
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

                    view?.invalidate()

                    val destination = view as TextView
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
            SharedPreferences.write(
                Constants.APEX_FLASK_BACKGROUND_WIDGET,
                SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteApexCircleWidget(apexCircleWidget)
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            widgetsViewModel.updateApexCircleWidget(apexCircleWidget)
            Toast.showSnackbar(binding.root, "Apex Flask Background Widget Updated")
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