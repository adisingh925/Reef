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
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.databinding.ApexSingleValueType1BottomSheetBinding
import app.android.damien.reef.databinding.ApexSingleValueType2BottomSheetBinding
import app.android.damien.reef.databinding.FragmentEditApexSingleValueType2Binding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONArray
import org.json.JSONObject
import yuku.ambilwarna.AmbilWarnaDialog


class EditApexSingleValueType2 : Fragment() {

    private val binding by lazy{
        FragmentEditApexSingleValueType2Binding.inflate(layoutInflater)
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

    private lateinit var apexSingleValueType2Widget: ApexSingleValueTypeTwoModel
    private lateinit var apexData: JSONArray

    var unit = ""
    var actualName = ""
    var givenName = ""
    var value = 0.0f
    var textColor = 0
    var ringColor = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        apexSingleValueType2Widget = arguments?.getParcelable(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET)!!

        initApiData()
        initValuesRecyclerView()

        actualName = apexSingleValueType2Widget.actualName.toString()
        givenName = apexSingleValueType2Widget.givenName.toString()
        value = apexSingleValueType2Widget.value
        unit = apexSingleValueType2Widget.unit.toString()
        textColor = apexSingleValueType2Widget.textColor
        ringColor = apexSingleValueType2Widget.ringColor

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
        binding.flaskBackgroundWidgetEditLayout.timestamp.setTextColor(textColor)
        binding.flaskBackgroundWidgetEditLayout.timestamp.text = SharedPreferences.read("lastUpdatedApex", "")

        val innerLayoutDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius_black_circular)
        val innerLayoutMutatedDrawable = innerLayoutDrawable?.mutate()
        if (innerLayoutMutatedDrawable is GradientDrawable) {
            innerLayoutMutatedDrawable.setStroke(3, ringColor) // Assuming 3dp width for the stroke
        }

        binding.flaskBackgroundWidgetEditLayout.innerLayout.background = innerLayoutMutatedDrawable

        binding.saveButton.setOnClickListener {
            apexSingleValueType2Widget.actualName = actualName
            apexSingleValueType2Widget.givenName = givenName
            apexSingleValueType2Widget.value = value
            apexSingleValueType2Widget.unit = unit
            apexSingleValueType2Widget.textColor = textColor
            apexSingleValueType2Widget.ringColor = ringColor
            widgetsViewModel.updateApexSingleValueTypeTwoWidget(apexSingleValueType2Widget)

            Toast.showSnackbar(requireView(), "Apex Single Value Type 2 Widget Updated")
            findNavController().popBackStack()
        }

        binding.deleteButton.setOnClickListener {
            SharedPreferences.write(
                Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,
                SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteApexSingleValueTypeTwoWidget(apexSingleValueType2Widget)
            findNavController().popBackStack()
        }

        binding.flaskBackgroundWidgetEditLayout.layout.setOnClickListener {

            val dialog = BottomSheetDialog(requireContext())
            val view = ApexSingleValueType2BottomSheetBinding.inflate(layoutInflater)

            view.nameInput.setText(binding.flaskBackgroundWidgetEditLayout.heading.text.toString())
            view.unitInput.setText(binding.flaskBackgroundWidgetEditLayout.unit.text.toString())
            view.textColorPicker.iconTint = ColorStateList.valueOf(textColor)
            view.ringColorPicker.iconTint = ColorStateList.valueOf(ringColor)

            view.saveButton.setOnClickListener {
                givenName = view.nameInput.text.toString()
                binding.flaskBackgroundWidgetEditLayout.heading.text = view.nameInput.text.toString()
                unit = view.unitInput.text.toString()
                binding.flaskBackgroundWidgetEditLayout.unit.text = view.unitInput.text.toString()
                binding.flaskBackgroundWidgetEditLayout.heading.setTextColor(textColor)
                binding.flaskBackgroundWidgetEditLayout.unit.setTextColor(textColor)
                binding.flaskBackgroundWidgetEditLayout.value.setTextColor(textColor)
                binding.flaskBackgroundWidgetEditLayout.timestamp.setTextColor(textColor)

                val innerLayoutDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius_black_circular)
                val innerLayoutMutatedDrawable = innerLayoutDrawable?.mutate()
                if (innerLayoutMutatedDrawable is GradientDrawable) {
                    innerLayoutMutatedDrawable.setStroke(3, ringColor) // Assuming 3dp width for the stroke
                }

                binding.flaskBackgroundWidgetEditLayout.innerLayout.background = innerLayoutMutatedDrawable

                dialog.dismiss()
            }

            view.textColorPicker.setOnClickListener {
                val colorPickerDialogue = AmbilWarnaDialog(context, textColor,
                    object : AmbilWarnaDialog.OnAmbilWarnaListener {
                        override fun onCancel(dialog: AmbilWarnaDialog) {

                        }

                        override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                            textColor = color
                            view.textColorPicker.iconTint = ColorStateList.valueOf(textColor)
                        }
                    })
                colorPickerDialogue.show()
            }

            view.ringColorPicker.setOnClickListener {
                val colorPickerDialogue = AmbilWarnaDialog(context, ringColor,
                    object : AmbilWarnaDialog.OnAmbilWarnaListener {
                        override fun onCancel(dialog: AmbilWarnaDialog) {

                        }

                        override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                            ringColor = color
                            view.ringColorPicker.iconTint = ColorStateList.valueOf(ringColor)
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