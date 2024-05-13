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
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.FocustronicOneElementWidgetModel
import app.android.damien.reef.databinding.ApexTwoRectangleWidgetBottomSheetBinding
import app.android.damien.reef.databinding.Focustronic1ElementWidgetBinding
import app.android.damien.reef.databinding.FragmentEditFocustronicSingleElementWidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONArray
import org.json.JSONObject
import yuku.ambilwarna.AmbilWarnaDialog


class EditFocustronicSingleElementWidget : Fragment() {

    private val binding by lazy {
        FragmentEditFocustronicSingleElementWidgetBinding.inflate(layoutInflater)
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

    private lateinit var focustronicSingleValueWidget: FocustronicOneElementWidgetModel
    private lateinit var apexData: JSONArray

    var unit = ""
    var actualName = ""
    var givenName = ""
    var value = 0.0f
    var backgroundColor = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        focustronicSingleValueWidget = arguments?.getParcelable(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET)!!

        unit = focustronicSingleValueWidget.unit.toString()
        actualName = focustronicSingleValueWidget.actualName.toString()
        value = focustronicSingleValueWidget.value
        backgroundColor = focustronicSingleValueWidget.backgroundColor

        val topRectangleDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius)
        val topRectangleMutatedDrawable = topRectangleDrawable?.mutate()
        topRectangleMutatedDrawable?.setTint(backgroundColor)

        binding.flaskBackgroundWidgetEditLayout.customWidgetLayoutCard.background = topRectangleMutatedDrawable

        binding.flaskBackgroundWidgetEditLayout.unit.text = unit

        if(focustronicSingleValueWidget.actualName.isNullOrEmpty()) {
            binding.flaskBackgroundWidgetEditLayout.heading.text = "NaN"
            binding.flaskBackgroundWidgetEditLayout.value.text = "NaN"
        } else {
            binding.flaskBackgroundWidgetEditLayout.heading.text = focustronicSingleValueWidget.actualName
            binding.flaskBackgroundWidgetEditLayout.value.text = focustronicSingleValueWidget.value.toString()
        }

        initApiData()

        initValuesRecyclerView()

        binding.saveButton.setOnClickListener {
            focustronicSingleValueWidget.unit = unit
            focustronicSingleValueWidget.value = value
            focustronicSingleValueWidget.actualName = actualName
            focustronicSingleValueWidget.backgroundColor = backgroundColor
            widgetsViewModel.updateFocustronicOneElementWidget(focustronicSingleValueWidget)
            Toast.showSnackbar(binding.root, "Focustronic Single Element Widget updated")
            findNavController().popBackStack()
        }

        binding.deleteButton.setOnClickListener {
            SharedPreferences.write(
                Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET,
                SharedPreferences.read(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteFocustronicOneElementWidget(focustronicSingleValueWidget)
            findNavController().popBackStack()
        }

        binding.flaskBackgroundWidgetEditLayout.customWidgetLayoutCard.setOnClickListener {
            if (!binding.flaskBackgroundWidgetEditLayout.heading.text.contains("NaN")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexTwoRectangleWidgetBottomSheetBinding.inflate(layoutInflater)

                view.textInput.setText(binding.flaskBackgroundWidgetEditLayout.unit.text.toString())

                view.colorPickerButton.iconTint = ColorStateList.valueOf(backgroundColor)

                view.saveButton.setOnClickListener {
                    if (view.textInput.text.toString().isEmpty()) {
                        view.textInput.error = "Field is required"
                        return@setOnClickListener
                    }

                    val topRectangleDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius)
                    val topRectangleMutatedDrawable = topRectangleDrawable?.mutate()
                    topRectangleMutatedDrawable?.setTint(backgroundColor)

                    binding.flaskBackgroundWidgetEditLayout.customWidgetLayoutCard.background = topRectangleMutatedDrawable
                    binding.flaskBackgroundWidgetEditLayout.unit.text = view.textInput.text.toString()
                    unit = view.textInput.text.toString()

                    dialog.dismiss()
                }

                view.colorPickerButton.setOnClickListener {
                    val colorPickerDialogue = AmbilWarnaDialog(context, backgroundColor,
                        object : AmbilWarnaDialog.OnAmbilWarnaListener {
                            override fun onCancel(dialog: AmbilWarnaDialog) {

                            }

                            override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                                backgroundColor = color
                                view.colorPickerButton.iconTint = ColorStateList.valueOf(backgroundColor)
                            }
                        })
                    colorPickerDialogue.show()
                }

                dialog.setCancelable(true)
                dialog.setContentView(view.root)
                dialog.show()
            } else {
                Toast.showSnackbar(binding.root, "Please select a value first")
            }
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