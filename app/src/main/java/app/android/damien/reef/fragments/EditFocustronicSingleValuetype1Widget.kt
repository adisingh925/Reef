package app.android.damien.reef.fragments

import android.content.res.ColorStateList
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
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.FocustronicSingleValueType1WidgetModel
import app.android.damien.reef.databinding.ApexSingleValueType1BottomSheetBinding
import app.android.damien.reef.databinding.FragmentEditFocustronicSingleValuetype1WidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONArray
import org.json.JSONObject
import yuku.ambilwarna.AmbilWarnaDialog


class EditFocustronicSingleValuetype1Widget : Fragment() {
    
    private val binding by lazy{
        FragmentEditFocustronicSingleValuetype1WidgetBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    private val recyclerView by lazy {
        binding.valuesRecyclerView
    }

    var unit = ""
    var actualName = ""
    var givenName = ""
    var value = 0.0f
    var textColor = 0

    private val adapter by lazy {
        SimpleListAdapter(requireContext(), object : SimpleListAdapter.OnItemClickListener {
            override fun onItemClick(data: String) {
                actualName = data
                binding.flaskBackgroundWidgetEditLayout.heading.text = data
                value = JSONObject(apexData.getJSONObject(0).toString()).get(actualName).toString().toFloat()
                binding.flaskBackgroundWidgetEditLayout.value.text = value.toString()
            }
        })
    }

    private lateinit var focustronicSingleValueType1Widget: FocustronicSingleValueType1WidgetModel
    private lateinit var apexData: JSONArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        focustronicSingleValueType1Widget = arguments?.getParcelable(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET)!!

        initApiData()
        initValuesRecyclerView()

        actualName = focustronicSingleValueType1Widget.actualName.toString()
        givenName = focustronicSingleValueType1Widget.givenName.toString()
        value = focustronicSingleValueType1Widget.value
        unit = focustronicSingleValueType1Widget.unit.toString()
        textColor = focustronicSingleValueType1Widget.textColor

        binding.flaskBackgroundWidgetEditLayout.unit.text = unit

        if(!focustronicSingleValueType1Widget.actualName.isNullOrEmpty()) {
            if(focustronicSingleValueType1Widget.givenName.isNullOrEmpty()) {
                binding.flaskBackgroundWidgetEditLayout.heading.text = focustronicSingleValueType1Widget.actualName
            } else {
                binding.flaskBackgroundWidgetEditLayout.heading.text = focustronicSingleValueType1Widget.givenName
            }
            binding.flaskBackgroundWidgetEditLayout.value.text = focustronicSingleValueType1Widget.value.toString()
        } else {
            binding.flaskBackgroundWidgetEditLayout.value.text = "NaN"
            binding.flaskBackgroundWidgetEditLayout.heading.text = "NaN"
        }

        binding.flaskBackgroundWidgetEditLayout.value.setTextColor(textColor)
        binding.flaskBackgroundWidgetEditLayout.heading.setTextColor(textColor)
        binding.flaskBackgroundWidgetEditLayout.unit.setTextColor(textColor)

        binding.saveButton.setOnClickListener {
            focustronicSingleValueType1Widget.actualName = actualName
            focustronicSingleValueType1Widget.givenName = givenName
            focustronicSingleValueType1Widget.value = value
            focustronicSingleValueType1Widget.unit = unit
            focustronicSingleValueType1Widget.textColor = textColor
            widgetsViewModel.updateFocustronicSingleValueTypeOneWidget(focustronicSingleValueType1Widget)

            Toast.showSnackbar(requireView(), "Apex Single Value Type 1 Widget Updated")
        }

        binding.deleteButton.setOnClickListener {
            SharedPreferences.write(
                Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,
                SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteFocustronicSingleValueTypeOneWidget(focustronicSingleValueType1Widget)
            findNavController().popBackStack()
        }

        binding.flaskBackgroundWidgetEditLayout.layout.setOnClickListener {

            val dialog = BottomSheetDialog(requireContext())
            val view = ApexSingleValueType1BottomSheetBinding.inflate(layoutInflater)

            view.nameInput.setText(binding.flaskBackgroundWidgetEditLayout.heading.text.toString())
            view.unitInput.setText(binding.flaskBackgroundWidgetEditLayout.unit.text.toString())
            view.colorPickerButton.iconTint = ColorStateList.valueOf(textColor)

            view.saveButton.setOnClickListener {
                givenName = view.nameInput.text.toString()
                binding.flaskBackgroundWidgetEditLayout.heading.text = view.nameInput.text.toString()
                unit = view.unitInput.text.toString()
                binding.flaskBackgroundWidgetEditLayout.unit.text = view.unitInput.text.toString()
                binding.flaskBackgroundWidgetEditLayout.heading.setTextColor(textColor)
                binding.flaskBackgroundWidgetEditLayout.unit.setTextColor(textColor)
                binding.flaskBackgroundWidgetEditLayout.value.setTextColor(textColor)

                dialog.dismiss()
            }

            view.colorPickerButton.setOnClickListener {
                val colorPickerDialogue = AmbilWarnaDialog(context, textColor,
                    object : AmbilWarnaDialog.OnAmbilWarnaListener {
                        override fun onCancel(dialog: AmbilWarnaDialog) {

                        }

                        override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                            textColor = color
                            view.colorPickerButton.iconTint = ColorStateList.valueOf(textColor)
                        }
                    })
                colorPickerDialogue.show()
            }

            dialog.setCancelable(true)
            dialog.setContentView(view.root)
            dialog.show()
        }
        
        return binding.root
    }

    private fun initApiData() {
        apexData = JSONObject(SharedPreferences.read("focustronicAlkatronicData", "").toString()).getJSONArray("data")
        apexData.getJSONObject(0).remove("record_time")
        val tempData = JSONObject(SharedPreferences.read("focustronicMastertronicData", "").toString()).getJSONArray("data")
        tempData.getJSONObject(0).remove("record_time")
        val keys = tempData.getJSONObject(0).keys()
        while (keys.hasNext()) {
            val key = keys.next() as String
            val value = tempData.getJSONObject(0).getString(key)
            apexData.getJSONObject(0).put(key, value)
        }
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