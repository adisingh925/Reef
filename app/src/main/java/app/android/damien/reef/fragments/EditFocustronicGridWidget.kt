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
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.database_model.FocustronicGridWidgetModel
import app.android.damien.reef.databinding.ApexWaterQualityBottomSheetBinding
import app.android.damien.reef.databinding.FragmentEditFocustronicGridWidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONArray
import org.json.JSONObject


class EditFocustronicGridWidget : Fragment() {

    private val binding by lazy {
        FragmentEditFocustronicGridWidgetBinding.inflate(layoutInflater)
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

    private lateinit var focustronicGridWidgetModel: FocustronicGridWidgetModel
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
    var slot6GivenName = ""
    var slot7GivenName = ""
    var slot8GivenName = ""
    var slot6ActualName = ""
    var slot7ActualName = ""
    var slot8ActualName = ""

    var slot1Value = 0.0f
    var slot2Value = 0.0f
    var slot3Value = 0.0f
    var slot4Value = 0.0f
    var slot5Value = 0.0f
    var slot6Value = 0.0f
    var slot7Value = 0.0f
    var slot8Value = 0.0f

    var slot1Unit = ""
    var slot2Unit = ""
    var slot3Unit = ""
    var slot4Unit = ""
    var slot5Unit = ""
    var slot6Unit = ""
    var slot7Unit = ""
    var slot8Unit = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        focustronicGridWidgetModel = arguments?.getParcelable(Constants.FOCUSTRONIC_GRID_WIDGET)!!

        slot1Unit = focustronicGridWidgetModel.slot1Unit.toString()
        slot2Unit = focustronicGridWidgetModel.slot2Unit.toString()
        slot3Unit = focustronicGridWidgetModel.slot3Unit.toString()
        slot4Unit = focustronicGridWidgetModel.slot4Unit.toString()
        slot5Unit = focustronicGridWidgetModel.slot5Unit.toString()
        slot6Unit = focustronicGridWidgetModel.slot6Unit.toString()
        slot7Unit = focustronicGridWidgetModel.slot7Unit.toString()
        slot8Unit = focustronicGridWidgetModel.slot8Unit.toString()

        slot1ActualName = focustronicGridWidgetModel.slot1ActualName.toString()
        slot1GivenName = focustronicGridWidgetModel.slot1GivenName.toString()
        slot2ActualName = focustronicGridWidgetModel.slot2ActualName.toString()
        slot2GivenName = focustronicGridWidgetModel.slot2GivenName.toString()
        slot3ActualName = focustronicGridWidgetModel.slot3ActualName.toString()
        slot3GivenName = focustronicGridWidgetModel.slot3GivenName.toString()
        slot4ActualName = focustronicGridWidgetModel.slot4ActualName.toString()
        slot4GivenName = focustronicGridWidgetModel.slot4GivenName.toString()
        slot5ActualName = focustronicGridWidgetModel.slot5ActualName.toString()
        slot5GivenName = focustronicGridWidgetModel.slot5GivenName.toString()
        slot6ActualName = focustronicGridWidgetModel.slot6ActualName.toString()
        slot6GivenName = focustronicGridWidgetModel.slot6GivenName.toString()
        slot7ActualName = focustronicGridWidgetModel.slot7ActualName.toString()
        slot7GivenName = focustronicGridWidgetModel.slot7GivenName.toString()
        slot8ActualName = focustronicGridWidgetModel.slot8ActualName.toString()
        slot8GivenName = focustronicGridWidgetModel.slot8GivenName.toString()

        slot1Value = focustronicGridWidgetModel.slot1Value
        slot2Value = focustronicGridWidgetModel.slot2Value
        slot3Value = focustronicGridWidgetModel.slot3Value
        slot4Value = focustronicGridWidgetModel.slot4Value
        slot5Value = focustronicGridWidgetModel.slot5Value
        slot6Value = focustronicGridWidgetModel.slot6Value
        slot7Value = focustronicGridWidgetModel.slot7Value
        slot8Value = focustronicGridWidgetModel.slot8Value


        if (focustronicGridWidgetModel.slot1GivenName.isNullOrEmpty()) {
            if(focustronicGridWidgetModel.slot1ActualName.equals("NaN")) {
                binding.slot1.text = "Slot 1"
            }else{
                binding.slot1.text = focustronicGridWidgetModel.slot1ActualName
            }
        } else {
            binding.flaskBackgroundWidgetEditLayout.name1.text = focustronicGridWidgetModel.slot1GivenName
            binding.slot1.text = focustronicGridWidgetModel.slot1GivenName
        }

        if (focustronicGridWidgetModel.slot2GivenName.isNullOrEmpty()) {
            if(focustronicGridWidgetModel.slot2ActualName.equals("NaN")) {
                binding.slot2.text = "Slot 2"
            }else{
                binding.slot2.text = focustronicGridWidgetModel.slot2ActualName
            }
        } else {
            binding.flaskBackgroundWidgetEditLayout.name2.text = focustronicGridWidgetModel.slot2GivenName
            binding.slot2.text = focustronicGridWidgetModel.slot2GivenName
        }

        if (focustronicGridWidgetModel.slot3GivenName.isNullOrEmpty()) {
            if(focustronicGridWidgetModel.slot3ActualName.equals("NaN")) {
                binding.slot3.text = "Slot 3"
            }else{
                binding.slot3.text = focustronicGridWidgetModel.slot3ActualName
            }
        } else {
            binding.flaskBackgroundWidgetEditLayout.name3.text = focustronicGridWidgetModel.slot3GivenName
            binding.slot3.text = focustronicGridWidgetModel.slot3GivenName
        }

        if (focustronicGridWidgetModel.slot4GivenName.isNullOrEmpty()) {
            if(focustronicGridWidgetModel.slot4ActualName.equals("NaN")) {
                binding.slot4.text = "Slot 4"
            }else{
                binding.slot4.text = focustronicGridWidgetModel.slot4ActualName
            }
        } else {
            binding.flaskBackgroundWidgetEditLayout.name4.text = focustronicGridWidgetModel.slot4GivenName
            binding.slot4.text = focustronicGridWidgetModel.slot4GivenName
        }

        if (focustronicGridWidgetModel.slot5GivenName.isNullOrEmpty()) {
            if(focustronicGridWidgetModel.slot5ActualName.equals("NaN")) {
                binding.slot5.text = "Slot 5"
            }else{
                binding.slot5.text = focustronicGridWidgetModel.slot5ActualName
            }
        } else {
            binding.flaskBackgroundWidgetEditLayout.name5.text = focustronicGridWidgetModel.slot5GivenName
            binding.slot5.text = focustronicGridWidgetModel.slot5GivenName
        }

        if (focustronicGridWidgetModel.slot6GivenName.isNullOrEmpty()) {
            if(focustronicGridWidgetModel.slot6ActualName.equals("NaN")) {
                binding.slot6.text = "Slot 6"
            }else{
                binding.slot6.text = focustronicGridWidgetModel.slot6ActualName
            }
        } else {
            binding.flaskBackgroundWidgetEditLayout.name6.text = focustronicGridWidgetModel.slot6GivenName
            binding.slot6.text = focustronicGridWidgetModel.slot6GivenName
        }

        if (focustronicGridWidgetModel.slot7GivenName.isNullOrEmpty()) {
            if(focustronicGridWidgetModel.slot7ActualName.equals("NaN")) {
                binding.slot7.text = "Slot 7"
            }else{
                binding.slot7.text = focustronicGridWidgetModel.slot7ActualName
            }
        } else {
            binding.flaskBackgroundWidgetEditLayout.name7.text = focustronicGridWidgetModel.slot7GivenName
            binding.slot7.text = focustronicGridWidgetModel.slot7GivenName
        }

        if (focustronicGridWidgetModel.slot8GivenName.isNullOrEmpty()) {
            if(focustronicGridWidgetModel.slot8ActualName.equals("NaN")) {
                binding.slot8.text = "Slot 8"
            }else{
                binding.slot8.text = focustronicGridWidgetModel.slot8ActualName
            }
        } else {
            binding.flaskBackgroundWidgetEditLayout.name8.text = focustronicGridWidgetModel.slot8GivenName
            binding.slot8.text = focustronicGridWidgetModel.slot8GivenName
        }

        binding.flaskBackgroundWidgetEditLayout.value1.text = focustronicGridWidgetModel.slot1Value.toString()
        binding.flaskBackgroundWidgetEditLayout.value2.text = focustronicGridWidgetModel.slot2Value.toString()
        binding.flaskBackgroundWidgetEditLayout.value3.text = focustronicGridWidgetModel.slot3Value.toString()
        binding.flaskBackgroundWidgetEditLayout.value4.text = focustronicGridWidgetModel.slot4Value.toString()
        binding.flaskBackgroundWidgetEditLayout.value5.text = focustronicGridWidgetModel.slot5Value.toString()
        binding.flaskBackgroundWidgetEditLayout.value6.text = focustronicGridWidgetModel.slot6Value.toString()
        binding.flaskBackgroundWidgetEditLayout.value7.text = focustronicGridWidgetModel.slot7Value.toString()
        binding.flaskBackgroundWidgetEditLayout.value8.text = focustronicGridWidgetModel.slot8Value.toString()

        binding.flaskBackgroundWidgetEditLayout.unit1.text = focustronicGridWidgetModel.slot1Unit
        binding.flaskBackgroundWidgetEditLayout.unit2.text = focustronicGridWidgetModel.slot2Unit
        binding.flaskBackgroundWidgetEditLayout.unit3.text = focustronicGridWidgetModel.slot3Unit
        binding.flaskBackgroundWidgetEditLayout.unit4.text = focustronicGridWidgetModel.slot4Unit
        binding.flaskBackgroundWidgetEditLayout.unit5.text = focustronicGridWidgetModel.slot5Unit
        binding.flaskBackgroundWidgetEditLayout.unit6.text = focustronicGridWidgetModel.slot6Unit
        binding.flaskBackgroundWidgetEditLayout.unit7.text = focustronicGridWidgetModel.slot7Unit
        binding.flaskBackgroundWidgetEditLayout.unit8.text = focustronicGridWidgetModel.slot8Unit

        initApiData()

        binding.slot1.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                slot1ActualName = it.toString()
                slot1GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.name1.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.name1.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.value1.text =
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
                binding.flaskBackgroundWidgetEditLayout.name2.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.name2.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.value2.text =
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
                binding.flaskBackgroundWidgetEditLayout.name3.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.name3.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.value3.text =
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
                binding.flaskBackgroundWidgetEditLayout.name4.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.name4.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.value4.text =
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
                binding.flaskBackgroundWidgetEditLayout.name5.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.name5.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.value5.text =
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

        binding.slot6.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                slot6ActualName = it.toString()
                slot6GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.name6.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.name6.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.value6.text =
                slot6ActualName?.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            slot6Value = slot6ActualName?.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot7.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                slot7ActualName = it.toString()
                slot7GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.name7.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.name7.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.value7.text =
                slot7ActualName?.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            slot7Value = slot7ActualName?.let { it1 ->
                JSONObject(apexData.getJSONObject(0).toString()).get(
                    it1
                ).toString().toFloat()
            }!!
        }

        binding.slot8.addTextChangedListener {
            if (JSONObject(apexData.getJSONObject(0).toString()).has(it.toString())) {
                slot8ActualName = it.toString()
                slot8GivenName = ""
                binding.flaskBackgroundWidgetEditLayout.name8.text = it.toString()
            } else {
                binding.flaskBackgroundWidgetEditLayout.name8.text = it.toString()
            }

            binding.flaskBackgroundWidgetEditLayout.value8.text =
                slot8ActualName?.let { it1 ->
                    JSONObject(apexData.getJSONObject(0).toString()).get(
                        it1
                    ).toString().toFloat().toString()
                }

            slot8Value = slot8ActualName?.let { it1 ->
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
                    binding.flaskBackgroundWidgetEditLayout.unit1.text =
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
                    binding.flaskBackgroundWidgetEditLayout.unit2.text =
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
                    binding.flaskBackgroundWidgetEditLayout.unit3.text =
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
                    binding.flaskBackgroundWidgetEditLayout.unit4.text =
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
                    binding.flaskBackgroundWidgetEditLayout.unit5.text =
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

        binding.slot6.setOnClickListener {
            if (!binding.slot6.text.contains("Slot")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexWaterQualityBottomSheetBinding.inflate(layoutInflater)

                view.slotInput.setText(binding.slot6.text.toString())
                view.unitInput.setText(slot6Unit)

                view.saveButton.setOnClickListener {
                    slot6GivenName = view.slotInput.text.toString()
                    binding.slot6.text = view.slotInput.text.toString()
                    slot6Unit = view.unitInput.text.toString()
                    binding.flaskBackgroundWidgetEditLayout.unit6.text =
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

        binding.slot7.setOnClickListener {
            if (!binding.slot7.text.contains("Slot")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexWaterQualityBottomSheetBinding.inflate(layoutInflater)

                view.slotInput.setText(binding.slot7.text.toString())
                view.unitInput.setText(slot7Unit)

                view.saveButton.setOnClickListener {
                    slot7GivenName = view.slotInput.text.toString()
                    binding.slot7.text = view.slotInput.text.toString()
                    slot7Unit = view.unitInput.text.toString()
                    binding.flaskBackgroundWidgetEditLayout.unit7.text =
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

        binding.slot8.setOnClickListener {
            if (!binding.slot8.text.contains("Slot")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexWaterQualityBottomSheetBinding.inflate(layoutInflater)

                view.slotInput.setText(binding.slot8.text.toString())
                view.unitInput.setText(slot8Unit)

                view.saveButton.setOnClickListener {
                    slot8GivenName = view.slotInput.text.toString()
                    binding.slot8.text = view.slotInput.text.toString()
                    slot8Unit = view.unitInput.text.toString()
                    binding.flaskBackgroundWidgetEditLayout.unit8.text =
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
        binding.slot6.setOnDragListener(dragListener)
        binding.slot7.setOnDragListener(dragListener)
        binding.slot8.setOnDragListener(dragListener)

        initValuesRecyclerView()

        binding.deleteButton.setOnClickListener {
            SharedPreferences.write(
                Constants.FOCUSTRONIC_GRID_WIDGET,
                SharedPreferences.read(Constants.FOCUSTRONIC_GRID_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteFocustronicGridWidget(focustronicGridWidgetModel)
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            focustronicGridWidgetModel.slot1ActualName = slot1ActualName
            focustronicGridWidgetModel.slot1GivenName = slot1GivenName
            focustronicGridWidgetModel.slot2ActualName = slot2ActualName
            focustronicGridWidgetModel.slot2GivenName = slot2GivenName
            focustronicGridWidgetModel.slot3ActualName = slot3ActualName
            focustronicGridWidgetModel.slot3GivenName = slot3GivenName
            focustronicGridWidgetModel.slot4ActualName = slot4ActualName
            focustronicGridWidgetModel.slot4GivenName = slot4GivenName
            focustronicGridWidgetModel.slot5ActualName = slot5ActualName
            focustronicGridWidgetModel.slot5GivenName = slot5GivenName
            focustronicGridWidgetModel.slot6ActualName = slot6ActualName
            focustronicGridWidgetModel.slot6GivenName = slot6GivenName
            focustronicGridWidgetModel.slot7ActualName = slot7ActualName
            focustronicGridWidgetModel.slot7GivenName = slot7GivenName
            focustronicGridWidgetModel.slot8ActualName = slot8ActualName
            focustronicGridWidgetModel.slot8GivenName = slot8GivenName

            focustronicGridWidgetModel.slot1Value = slot1Value
            focustronicGridWidgetModel.slot2Value = slot2Value
            focustronicGridWidgetModel.slot3Value = slot3Value
            focustronicGridWidgetModel.slot4Value = slot4Value
            focustronicGridWidgetModel.slot5Value = slot5Value
            focustronicGridWidgetModel.slot6Value = slot6Value
            focustronicGridWidgetModel.slot7Value = slot7Value
            focustronicGridWidgetModel.slot8Value = slot8Value

            focustronicGridWidgetModel.slot1Unit = slot1Unit
            focustronicGridWidgetModel.slot2Unit = slot2Unit
            focustronicGridWidgetModel.slot3Unit = slot3Unit
            focustronicGridWidgetModel.slot4Unit = slot4Unit
            focustronicGridWidgetModel.slot5Unit = slot5Unit
            focustronicGridWidgetModel.slot6Unit = slot6Unit
            focustronicGridWidgetModel.slot7Unit = slot7Unit
            focustronicGridWidgetModel.slot8Unit = slot8Unit

            widgetsViewModel.updateFocustronicGridWidget(focustronicGridWidgetModel)
            Toast.showSnackbar(binding.root, "Apex Water Quality Widget Updated")
        }

        return binding.root
    }

    private fun initApiData() {
        apexData = JSONArray(SharedPreferences.read("focustronicData", "").toString())
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