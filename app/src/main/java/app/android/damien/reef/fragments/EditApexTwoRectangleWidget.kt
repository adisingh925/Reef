package app.android.damien.reef.fragments

import android.content.res.ColorStateList
import android.graphics.Color
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
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.databinding.ApexFlaskBackgroundWidgetEditDialogBinding
import app.android.damien.reef.databinding.ApexTwoRectangleWidgetBottomSheetBinding
import app.android.damien.reef.databinding.FragmentEditApexFlaskBackgroundWidgetBinding
import app.android.damien.reef.databinding.FragmentEditApexTwoRectangleWidgetBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.json.JSONArray
import org.json.JSONObject
import yuku.ambilwarna.AmbilWarnaDialog
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EditApexTwoRectangleWidget : Fragment() {

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    private val firstRecyclerView by lazy {
        binding.firstRectangleValues
    }

    private val secondRecyclerView by lazy {
        binding.secondRectangleValues
    }

    var firstRectangleColor = Color.parseColor("#cc7700")
    var secondRectangleColor = Color.parseColor("#cc7700")

    var bottomRectangleValue = 0.0f
    var bottomRectangleActualName = ""

    var topRectangleValue = 0.0f
    var topRectangleActualName = ""

    private val topRectangleAdapter by lazy {
        SimpleListAdapter(requireContext(), object : SimpleListAdapter.OnItemClickListener {
            override fun onItemClick(data: String) {
                topRectangleActualName = data
                topRectangleValue = JSONObject(
                    apexData.getJSONObject(0).toString()
                ).get(data).toString().toFloat()
                binding.flaskTwoRectangleWidgetEditLayout.value.text = topRectangleValue.toString()
            }
        })
    }

    private val bottomRectangleAdapter by lazy {
        SimpleListAdapter(requireContext(), object : SimpleListAdapter.OnItemClickListener {
            override fun onItemClick(data: String) {
                bottomRectangleActualName = data
                bottomRectangleValue = JSONObject(
                    apexData.getJSONObject(0).toString()
                ).get(data).toString().toFloat()
                binding.flaskTwoRectangleWidgetEditLayout.value2.text = bottomRectangleValue.toString()
            }
        })
    }

    private fun millisToDateTime(millis: Long): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
        return dateTime.format(formatter)
    }

    private lateinit var apexTwoRectangleWidget: ApexTwoRectangleWidgets
    private lateinit var apexData: JSONArray

    private val binding by lazy {
        FragmentEditApexTwoRectangleWidgetBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        apexTwoRectangleWidget = arguments?.getParcelable(Constants.APEX_TWO_RECTANGLE_WIDGET)!!

        initApiData()

        firstRectangleColor = apexTwoRectangleWidget.topRectangleColor
        secondRectangleColor = apexTwoRectangleWidget.bottomRectangleColor
        bottomRectangleValue = apexTwoRectangleWidget.bottomRectangleValue
        topRectangleValue = apexTwoRectangleWidget.topRectangleValue
        bottomRectangleActualName = apexTwoRectangleWidget.bottomRectangleActualName.toString()
        topRectangleActualName = apexTwoRectangleWidget.topRectangleActualName.toString()

        if (apexTwoRectangleWidget.topRectangleColor == 0) {
            binding.flaskTwoRectangleWidgetEditLayout.card1.setBackgroundColor(Color.parseColor("#cc7700"))
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.card1.setBackgroundColor(
                apexTwoRectangleWidget.topRectangleColor
            )
        }

        if (apexTwoRectangleWidget.bottomRectangleColor == 0) {
            binding.flaskTwoRectangleWidgetEditLayout.card2.setBackgroundColor(Color.parseColor("#cc7700"))
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.card2.setBackgroundColor(
                apexTwoRectangleWidget.bottomRectangleColor
            )
        }

        if (apexTwoRectangleWidget.topRectangleUnit.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.unit.text = "Unit 1"
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.unit.text =
                apexTwoRectangleWidget.topRectangleUnit
        }

        if (apexTwoRectangleWidget.bottomRectangleUnit.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.unit2.text = "Unit 2"
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.unit2.text =
                apexTwoRectangleWidget.bottomRectangleUnit
        }

        if (apexTwoRectangleWidget.topRectangleActualName.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.value.text = "NaN"
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.value.text = JSONObject(
                apexData.getJSONObject(0).toString()
            ).get(apexTwoRectangleWidget.topRectangleActualName).toString().toFloat()
                .toString()
        }

        if (apexTwoRectangleWidget.bottomRectangleActualName.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.value2.text = "NaN"
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.value2.text = JSONObject(
                apexData.getJSONObject(0).toString()
            ).get(apexTwoRectangleWidget.bottomRectangleActualName).toString().toFloat()
                .toString()
        }

        if (apexTwoRectangleWidget.topRectangleUpdateTimeStamp.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.timestamp.text =
                millisToDateTime(System.currentTimeMillis())
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.timestamp.text =
                apexTwoRectangleWidget.topRectangleUpdateTimeStamp
        }

        if (apexTwoRectangleWidget.bottomRectangleUpdateTimeStamp.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.timestamp2.text =
                millisToDateTime(System.currentTimeMillis())
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.timestamp2.text =
                apexTwoRectangleWidget.bottomRectangleUpdateTimeStamp
        }

        binding.flaskTwoRectangleWidgetEditLayout.card1.setOnClickListener {
            if (!binding.flaskTwoRectangleWidgetEditLayout.value.text.contains("NaN")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexTwoRectangleWidgetBottomSheetBinding.inflate(layoutInflater)

                view.textInput.setText(binding.flaskTwoRectangleWidgetEditLayout.unit.text.toString())

                view.saveButton.setOnClickListener {
                    if (view.textInput.text.toString().isEmpty()) {
                        view.textInput.error = "Field is required"
                        return@setOnClickListener
                    }

                    binding.flaskTwoRectangleWidgetEditLayout.card1.setBackgroundColor(
                        firstRectangleColor
                    )

                    binding.flaskTwoRectangleWidgetEditLayout.unit.text = view.textInput.text.toString()

                    dialog.dismiss()
                }

                view.colorPickerButton.setOnClickListener {
                    val colorPickerDialogue = AmbilWarnaDialog(context, firstRectangleColor,
                        object : AmbilWarnaDialog.OnAmbilWarnaListener {
                            override fun onCancel(dialog: AmbilWarnaDialog) {

                            }

                            override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                                firstRectangleColor = color
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

        binding.flaskTwoRectangleWidgetEditLayout.card2.setOnClickListener {
            if (!binding.flaskTwoRectangleWidgetEditLayout.value2.text.contains("NaN")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexTwoRectangleWidgetBottomSheetBinding.inflate(layoutInflater)

                view.textInput.setText(binding.flaskTwoRectangleWidgetEditLayout.unit2.text.toString())

                view.saveButton.setOnClickListener {
                    if (view.textInput.text.toString().isEmpty()) {
                        view.textInput.error = "Field is required"
                        return@setOnClickListener
                    }

                    binding.flaskTwoRectangleWidgetEditLayout.card2.setBackgroundColor(
                        secondRectangleColor
                    )

                    binding.flaskTwoRectangleWidgetEditLayout.unit2.text = view.textInput.text.toString()

                    dialog.dismiss()
                }

                view.colorPickerButton.setOnClickListener {
                    val colorPickerDialogue = AmbilWarnaDialog(context, secondRectangleColor,
                        object : AmbilWarnaDialog.OnAmbilWarnaListener {
                            override fun onCancel(dialog: AmbilWarnaDialog) {

                            }

                            override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                                secondRectangleColor = color
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

        binding.deleteButton.setOnClickListener {
            SharedPreferences.write(
                Constants.APEX_TWO_RECTANGLE_WIDGET,
                SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteApexTwoRectangleWidget(apexTwoRectangleWidget)
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            apexTwoRectangleWidget.topRectangleUnit = binding.flaskTwoRectangleWidgetEditLayout.unit.text.toString()
            apexTwoRectangleWidget.bottomRectangleUnit = binding.flaskTwoRectangleWidgetEditLayout.unit2.text.toString()
            apexTwoRectangleWidget.topRectangleColor = firstRectangleColor
            apexTwoRectangleWidget.bottomRectangleColor = secondRectangleColor
            apexTwoRectangleWidget.topRectangleActualName = topRectangleActualName
            apexTwoRectangleWidget.bottomRectangleActualName = bottomRectangleActualName
            apexTwoRectangleWidget.topRectangleValue = topRectangleValue
            apexTwoRectangleWidget.bottomRectangleValue = bottomRectangleValue
            apexTwoRectangleWidget.topRectangleUpdateTimeStamp = SharedPreferences.read("lastUpdatedApex", "")
            apexTwoRectangleWidget.bottomRectangleUpdateTimeStamp = SharedPreferences.read("lastUpdatedApex", "")
            widgetsViewModel.updateApexTwoRectangleWidget(apexTwoRectangleWidget)
            Toast.showSnackbar(binding.root, "Apex Two Rectangle Background Widget Updated")
        }

        initValuesRecyclerView()

        return binding.root
    }

    private fun initApiData() {
        apexData = JSONArray(SharedPreferences.read("apexData", "").toString())
    }

    private fun initValuesRecyclerView() {
        firstRecyclerView.adapter = topRectangleAdapter
        firstRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        topRectangleAdapter.setData(getJsonKeys(apexData.getJSONObject(0)))

        secondRecyclerView.adapter = bottomRectangleAdapter
        secondRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        bottomRectangleAdapter.setData(getJsonKeys(apexData.getJSONObject(0)))
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