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

    private val binding by lazy {
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

    private var value1 = 0.0f
    private var value2 = 0.0f
    private var value3 = 0.0f

    private var actualName1 = ""
    private var actualName2 = ""
    private var actualName3 = ""

    private var givenName1 = ""
    private var givenName2 = ""
    private var givenName3 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        apexCircleWidget = arguments?.getParcelable(Constants.APEX_CIRCLE_WIDGET)!!

        value1 = apexCircleWidget.slot1Value
        value2 = apexCircleWidget.slot2Value
        value3 = apexCircleWidget.slot3Value

        actualName1 = apexCircleWidget.slot1ActualName.toString()
        actualName2 = apexCircleWidget.slot2ActualName.toString()
        actualName3 = apexCircleWidget.slot3ActualName.toString()

        if (apexCircleWidget.slot1GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot1name.text =
                apexCircleWidget.slot1ActualName
            binding.slot1.text = apexCircleWidget.slot1ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot1name.text = apexCircleWidget.slot1GivenName
            binding.slot1.text = apexCircleWidget.slot1GivenName
        }

        if (apexCircleWidget.slot2GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot2name.text =
                apexCircleWidget.slot2ActualName
            binding.slot2.text = apexCircleWidget.slot2ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot2name.text = apexCircleWidget.slot2GivenName
            binding.slot2.text = apexCircleWidget.slot2GivenName
        }

        if (apexCircleWidget.slot3GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot3name.text =
                apexCircleWidget.slot3ActualName
            binding.slot3.text = apexCircleWidget.slot3ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot3name.text = apexCircleWidget.slot3GivenName
            binding.slot3.text = apexCircleWidget.slot3GivenName
        }

        binding.flaskBackgroundWidgetEditLayout.slot1value.text =
            apexCircleWidget.slot1Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot2value.text =
            apexCircleWidget.slot2Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot3value.text =
            apexCircleWidget.slot3Value.toString()

        initApiData()

        binding.slot1.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                actualName1 = it.toString()
                givenName1 = ""
                binding.flaskBackgroundWidgetEditLayout.slot1name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot1name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot1value.text =
                JSONObject(apexData.getJSONObject(0).toString()).get(actualName1).toString()
                    .toFloat()
                    .toString()

            value1 = actualName1.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }
        }

        binding.slot2.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                actualName2 = it.toString()
                givenName2 = ""
                binding.flaskBackgroundWidgetEditLayout.slot2name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot2name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot2value.text =
                actualName2?.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            value2 = actualName2?.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot3.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                actualName3 = it.toString()
                givenName3 = ""
                binding.flaskBackgroundWidgetEditLayout.slot3name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot3name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot3value.text =
                actualName3.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            value3 = actualName3.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }
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

                    givenName1 = view.textInput.text.toString()
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

                    givenName2 = view.textInput.text.toString()
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

                    givenName3 = view.textInput.text.toString()
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
                Constants.APEX_CIRCLE_WIDGET,
                SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteApexCircleWidget(apexCircleWidget)
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            apexCircleWidget.slot1ActualName = actualName1
            apexCircleWidget.slot1GivenName = givenName1
            apexCircleWidget.slot1Value = value1
            apexCircleWidget.slot2ActualName = actualName2
            apexCircleWidget.slot2GivenName = givenName2
            apexCircleWidget.slot2Value = value2
            apexCircleWidget.slot3ActualName = actualName3
            apexCircleWidget.slot3GivenName = givenName3
            apexCircleWidget.slot3Value = value3
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