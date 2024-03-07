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
import app.android.damien.reef.adapter.ValuesAdapter
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.databinding.ApexSingleValueType1BottomSheetBinding
import app.android.damien.reef.databinding.ApexWaterQualityBottomSheetBinding
import app.android.damien.reef.databinding.FragmentEditApexSingleValueType1WidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONArray
import org.json.JSONObject
import yuku.ambilwarna.AmbilWarnaDialog


class EditApexSingleValueType1Widget : Fragment() {

    private val binding by lazy {
        FragmentEditApexSingleValueType1WidgetBinding.inflate(layoutInflater)
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
                value = JSONObject(apexData.getJSONObject(0).toString()).get(actualName).toString()
                    .toFloat()
                binding.flaskBackgroundWidgetEditLayout.value.text = value.toString()
            }
        })
    }

    private lateinit var apexSingleValueTypeOneWidget: ApexSingleValueTypeOneModel
    private lateinit var apexData: JSONArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        apexSingleValueTypeOneWidget = arguments?.getParcelable(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET)!!

        initApiData()
        initValuesRecyclerView()

        actualName = apexSingleValueTypeOneWidget.actualName.toString()
        givenName = apexSingleValueTypeOneWidget.givenName.toString()
        value = apexSingleValueTypeOneWidget.value
        unit = apexSingleValueTypeOneWidget.unit.toString()
        textColor = apexSingleValueTypeOneWidget.textColor

        binding.flaskBackgroundWidgetEditLayout.unit.text = unit
        binding.flaskBackgroundWidgetEditLayout.value.text = value.toString()

        if (givenName != "") {
            binding.flaskBackgroundWidgetEditLayout.heading.text = givenName
        } else {
            binding.flaskBackgroundWidgetEditLayout.heading.text = actualName
        }

        binding.flaskBackgroundWidgetEditLayout.value.setTextColor(textColor)
        binding.flaskBackgroundWidgetEditLayout.heading.setTextColor(textColor)
        binding.flaskBackgroundWidgetEditLayout.unit.setTextColor(textColor)

        binding.saveButton.setOnClickListener {
            apexSingleValueTypeOneWidget.actualName = actualName
            apexSingleValueTypeOneWidget.givenName = givenName
            apexSingleValueTypeOneWidget.value = value
            apexSingleValueTypeOneWidget.unit = unit
            apexSingleValueTypeOneWidget.textColor = textColor
            widgetsViewModel.updateApexSingleValueTypeOneWidget(apexSingleValueTypeOneWidget)

            Toast.showSnackbar(requireView(), "Apex Single Value Type 1 Widget Updated")
        }

        binding.deleteButton.setOnClickListener {
            SharedPreferences.write(
                Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,
                SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteApexSingleValueTypeOneWidget(apexSingleValueTypeOneWidget)
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