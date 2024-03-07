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
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.databinding.ApexFlaskBackgroundWidgetEditDialogBinding
import app.android.damien.reef.databinding.ApexWaterQualityBottomSheetBinding
import app.android.damien.reef.databinding.FragmentEditApexCircleWidgetBinding
import app.android.damien.reef.databinding.FragmentEditApexWaterQualityWidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONArray
import org.json.JSONObject


class EditApexWaterQualityWidget : Fragment() {

    private val binding by lazy {
        FragmentEditApexWaterQualityWidgetBinding.inflate(layoutInflater)
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

    private lateinit var apexWaterQualityWidget: ApexWaterQualityWidget
    private lateinit var apexData: JSONArray

    var slot1ActualName = ""
    var slot1GivenName = ""
    var slot2ActualName = ""
    var slot2GivenName = ""
    var slot3ActualName = ""
    var slot3GivenName = ""
    var slot4ActualName = ""
    var slot4GivenName = ""
    var slot5ActualName = ""
    var slot5GivenName = ""

    var slot1Value = 0.0f
    var slot2Value = 0.0f
    var slot3Value = 0.0f
    var slot4Value = 0.0f
    var slot5Value = 0.0f

    var slot1Unit = ""
    var slot2Unit = ""
    var slot3Unit = ""
    var slot4Unit = ""
    var slot5Unit = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        apexWaterQualityWidget = arguments?.getParcelable(Constants.APEX_WATER_QUALITY_WIDGET)!!

        slot1Unit = apexWaterQualityWidget.slot1Unit.toString()
        slot2Unit = apexWaterQualityWidget.slot2Unit.toString()
        slot3Unit = apexWaterQualityWidget.slot3Unit.toString()
        slot4Unit = apexWaterQualityWidget.slot4Unit.toString()
        slot5Unit = apexWaterQualityWidget.slot5Unit.toString()

        slot1ActualName = apexWaterQualityWidget.slot1ActualName.toString()
        slot1GivenName = apexWaterQualityWidget.slot1GivenName.toString()
        slot2ActualName = apexWaterQualityWidget.slot2ActualName.toString()
        slot2GivenName = apexWaterQualityWidget.slot2GivenName.toString()
        slot3ActualName = apexWaterQualityWidget.slot3ActualName.toString()
        slot3GivenName = apexWaterQualityWidget.slot3GivenName.toString()
        slot4ActualName = apexWaterQualityWidget.slot4ActualName.toString()
        slot4GivenName = apexWaterQualityWidget.slot4GivenName.toString()
        slot5ActualName = apexWaterQualityWidget.slot5ActualName.toString()
        slot5GivenName = apexWaterQualityWidget.slot5GivenName.toString()


        slot1Value = apexWaterQualityWidget.slot1Value
        slot2Value = apexWaterQualityWidget.slot2Value
        slot3Value = apexWaterQualityWidget.slot3Value
        slot4Value = apexWaterQualityWidget.slot4Value
        slot5Value = apexWaterQualityWidget.slot5Value


        if (apexWaterQualityWidget.slot1GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot1name.text =
                apexWaterQualityWidget.slot1ActualName
            binding.slot1.text = apexWaterQualityWidget.slot1ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot1name.text =
                apexWaterQualityWidget.slot1GivenName
            binding.slot1.text = apexWaterQualityWidget.slot1GivenName
        }

        if (apexWaterQualityWidget.slot2GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot2name.text =
                apexWaterQualityWidget.slot2ActualName
            binding.slot2.text = apexWaterQualityWidget.slot2ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot2name.text =
                apexWaterQualityWidget.slot2GivenName
            binding.slot2.text = apexWaterQualityWidget.slot2GivenName
        }

        if (apexWaterQualityWidget.slot3GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot3name.text =
                apexWaterQualityWidget.slot3ActualName
            binding.slot3.text = apexWaterQualityWidget.slot3ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot3name.text =
                apexWaterQualityWidget.slot3GivenName
            binding.slot3.text = apexWaterQualityWidget.slot3GivenName
        }

        if (apexWaterQualityWidget.slot4GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot4name.text =
                apexWaterQualityWidget.slot4ActualName
            binding.slot4.text = apexWaterQualityWidget.slot4ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot4name.text =
                apexWaterQualityWidget.slot4GivenName
            binding.slot4.text = apexWaterQualityWidget.slot4GivenName
        }

        if (apexWaterQualityWidget.slot5GivenName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.slot5name.text =
                apexWaterQualityWidget.slot5ActualName
            binding.slot5.text = apexWaterQualityWidget.slot5ActualName
        } else {
            binding.flaskBackgroundWidgetEditLayout.slot5name.text =
                apexWaterQualityWidget.slot5GivenName
            binding.slot5.text = apexWaterQualityWidget.slot5GivenName
        }

        binding.flaskBackgroundWidgetEditLayout.slot1value.text =
            apexWaterQualityWidget.slot1Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot2value.text =
            apexWaterQualityWidget.slot2Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot3value.text =
            apexWaterQualityWidget.slot3Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot4value.text =
            apexWaterQualityWidget.slot4Value.toString()
        binding.flaskBackgroundWidgetEditLayout.slot5value.text =
            apexWaterQualityWidget.slot5Value.toString()

        binding.flaskBackgroundWidgetEditLayout.slot1unit.text = apexWaterQualityWidget.slot1Unit
        binding.flaskBackgroundWidgetEditLayout.slot2unit.text = apexWaterQualityWidget.slot2Unit
        binding.flaskBackgroundWidgetEditLayout.slot3unit.text = apexWaterQualityWidget.slot3Unit
        binding.flaskBackgroundWidgetEditLayout.slot4unit.text = apexWaterQualityWidget.slot4Unit
        binding.flaskBackgroundWidgetEditLayout.slot5unit.text = apexWaterQualityWidget.slot5Unit

        initApiData()

        binding.slot1.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                slot1ActualName = it.toString()
                slot1GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.slot1name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot1name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot1value.text =
                JSONObject(apexData.getJSONObject(0).toString()).get(slot1ActualName).toString()
                    .toFloat()
                    .toString()

            slot1Value = slot1ActualName.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }
        }

        binding.slot2.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                slot2ActualName = it.toString()
                slot2GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.slot2name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot2name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot2value.text =
                slot2ActualName.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            slot2Value = slot2ActualName.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot3.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                slot3ActualName = it.toString()
                slot3GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.slot3name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot3name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot3value.text =
                slot3ActualName?.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            slot3Value = slot3ActualName?.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot4.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                slot4ActualName = it.toString()
                slot4GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.slot4name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot4name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot4value.text =
                slot4ActualName?.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            slot4Value = slot4ActualName?.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot5.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                slot5ActualName = it.toString()
                slot5GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.slot5name.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.slot5name.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.slot5value.text =
                slot5ActualName?.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            slot5Value = slot5ActualName?.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot1.setOnClickListener {
            if (!binding.slot1.text.contains("Slot")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexWaterQualityBottomSheetBinding.inflate(layoutInflater)

                view.slotInput.setText(binding.slot1.text.toString())
                view.unitInput.setText(slot1Unit)

                view.saveButton.setOnClickListener {
                    slot1GivenName = view.slotInput.text.toString()
                    binding.slot1.text = view.slotInput.text.toString()
                    slot1Unit = view.unitInput.text.toString()
                    binding.flaskBackgroundWidgetEditLayout.slot1unit.text =
                        view.unitInput.text.toString()
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
                val view = ApexWaterQualityBottomSheetBinding.inflate(layoutInflater)

                view.slotInput.setText(binding.slot2.text.toString())
                view.unitInput.setText(slot2Unit)

                view.saveButton.setOnClickListener {
                    slot2GivenName = view.slotInput.text.toString()
                    binding.slot2.text = view.slotInput.text.toString()
                    slot2Unit = view.unitInput.text.toString()
                    binding.flaskBackgroundWidgetEditLayout.slot2unit.text =
                        view.unitInput.text.toString()
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
                val view = ApexWaterQualityBottomSheetBinding.inflate(layoutInflater)

                view.slotInput.setText(binding.slot3.text.toString())
                view.unitInput.setText(slot3Unit)

                view.saveButton.setOnClickListener {
                    slot3GivenName = view.slotInput.text.toString()
                    binding.slot3.text = view.slotInput.text.toString()
                    slot3Unit = view.unitInput.text.toString()
                    binding.flaskBackgroundWidgetEditLayout.slot3unit.text =
                        view.unitInput.text.toString()
                    dialog.dismiss()
                }

                dialog.setCancelable(true)
                dialog.setContentView(view.root)
                dialog.show()
            } else {
                Toast.showSnackbar(binding.root, "Please add a value to the slot first")
            }
        }

        binding.slot4.setOnClickListener {
            if (!binding.slot4.text.contains("Slot")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexWaterQualityBottomSheetBinding.inflate(layoutInflater)

                view.slotInput.setText(binding.slot4.text.toString())
                view.unitInput.setText(slot4Unit)

                view.saveButton.setOnClickListener {
                    slot4GivenName = view.slotInput.text.toString()
                    binding.slot4.text = view.slotInput.text.toString()
                    slot4Unit = view.unitInput.text.toString()
                    binding.flaskBackgroundWidgetEditLayout.slot4unit.text =
                        view.unitInput.text.toString()
                    dialog.dismiss()
                }

                dialog.setCancelable(true)
                dialog.setContentView(view.root)
                dialog.show()
            } else {
                Toast.showSnackbar(binding.root, "Please add a value to the slot first")
            }
        }

        binding.slot5.setOnClickListener {
            if (!binding.slot5.text.contains("Slot")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexWaterQualityBottomSheetBinding.inflate(layoutInflater)

                view.slotInput.setText(binding.slot5.text.toString())
                view.unitInput.setText(slot5Unit)

                view.saveButton.setOnClickListener {
                    slot5GivenName = view.slotInput.text.toString()
                    binding.slot5.text = view.slotInput.text.toString()
                    slot5Unit = view.unitInput.text.toString()
                    binding.flaskBackgroundWidgetEditLayout.slot5unit.text =
                        view.unitInput.text.toString()

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
        binding.slot4.setOnDragListener(dragListener)
        binding.slot5.setOnDragListener(dragListener)

        initValuesRecyclerView()

        binding.deleteButton.setOnClickListener {
            SharedPreferences.write(
                Constants.APEX_WATER_QUALITY_WIDGET,
                SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteApexWaterQualityWidget(apexWaterQualityWidget)
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            apexWaterQualityWidget.slot1ActualName = slot1ActualName
            apexWaterQualityWidget.slot1GivenName = slot1GivenName
            apexWaterQualityWidget.slot2ActualName = slot2ActualName
            apexWaterQualityWidget.slot2GivenName = slot2GivenName
            apexWaterQualityWidget.slot3ActualName = slot3ActualName
            apexWaterQualityWidget.slot3GivenName = slot3GivenName
            apexWaterQualityWidget.slot4ActualName = slot4ActualName
            apexWaterQualityWidget.slot4GivenName = slot4GivenName
            apexWaterQualityWidget.slot5ActualName = slot5ActualName
            apexWaterQualityWidget.slot5GivenName = slot5GivenName

            apexWaterQualityWidget.slot1Value = slot1Value
            apexWaterQualityWidget.slot2Value = slot2Value
            apexWaterQualityWidget.slot3Value = slot3Value
            apexWaterQualityWidget.slot4Value = slot4Value
            apexWaterQualityWidget.slot5Value = slot5Value

            apexWaterQualityWidget.slot1Unit = slot1Unit
            apexWaterQualityWidget.slot2Unit = slot2Unit
            apexWaterQualityWidget.slot3Unit = slot3Unit
            apexWaterQualityWidget.slot4Unit = slot4Unit
            apexWaterQualityWidget.slot5Unit = slot5Unit

            widgetsViewModel.updateApexWaterQualityWidget(apexWaterQualityWidget)
            Toast.showSnackbar(binding.root, "Apex Water Quality Widget Updated")
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