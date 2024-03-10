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
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.FocustronicTwoRectangleWidgetModel
import app.android.damien.reef.databinding.ApexTwoRectangleWidgetBottomSheetBinding
import app.android.damien.reef.databinding.FragmentEditFocustronicDoubleRectangleWidgetBinding
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


class EditFocustronicDoubleRectangleWidget : Fragment() {

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    private val binding by lazy{
        FragmentEditFocustronicDoubleRectangleWidgetBinding.inflate(layoutInflater)
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

    private lateinit var focustronicTwoRectangleWidget: FocustronicTwoRectangleWidgetModel
    private lateinit var apexData: JSONArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        focustronicTwoRectangleWidget = arguments?.getParcelable(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET)!!

        initApiData()

        firstRectangleColor = focustronicTwoRectangleWidget.topRectangleColor
        secondRectangleColor = focustronicTwoRectangleWidget.bottomRectangleColor
        bottomRectangleValue = focustronicTwoRectangleWidget.bottomRectangleValue
        topRectangleValue = focustronicTwoRectangleWidget.topRectangleValue
        bottomRectangleActualName = focustronicTwoRectangleWidget.bottomRectangleActualName.toString()
        topRectangleActualName = focustronicTwoRectangleWidget.topRectangleActualName.toString()

        if (focustronicTwoRectangleWidget.topRectangleColor == 0) {
            binding.flaskTwoRectangleWidgetEditLayout.card1.setBackgroundColor(Color.parseColor("#cc7700"))
        } else {
            val topRectangleDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius)
            val topRectangleMutatedDrawable = topRectangleDrawable?.mutate()
            topRectangleMutatedDrawable?.setTint(firstRectangleColor)

            binding.flaskTwoRectangleWidgetEditLayout.card1.background = topRectangleMutatedDrawable
        }

        if (focustronicTwoRectangleWidget.bottomRectangleColor == 0) {
            binding.flaskTwoRectangleWidgetEditLayout.card2.setBackgroundColor(Color.parseColor("#cc7700"))
        } else {
            val topRectangleDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius)
            val topRectangleMutatedDrawable = topRectangleDrawable?.mutate()
            topRectangleMutatedDrawable?.setTint(secondRectangleColor)

            binding.flaskTwoRectangleWidgetEditLayout.card2.background = topRectangleMutatedDrawable
        }

        if (focustronicTwoRectangleWidget.topRectangleUnit.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.unit.text = "Unit 1"
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.unit.text =
                focustronicTwoRectangleWidget.topRectangleUnit
        }

        if (focustronicTwoRectangleWidget.bottomRectangleUnit.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.unit2.text = "Unit 2"
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.unit2.text =
                focustronicTwoRectangleWidget.bottomRectangleUnit
        }

        if (focustronicTwoRectangleWidget.topRectangleActualName.equals("NaN")) {
            binding.flaskTwoRectangleWidgetEditLayout.value.text = "0.0"
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.value.text = JSONObject(
                apexData.getJSONObject(0).toString()
            ).get(focustronicTwoRectangleWidget.topRectangleActualName).toString().toFloat()
                .toString()
        }

        if (focustronicTwoRectangleWidget.bottomRectangleActualName.equals("NaN")) {
            binding.flaskTwoRectangleWidgetEditLayout.value2.text = "0.0"
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.value2.text = JSONObject(
                apexData.getJSONObject(0).toString()
            ).get(focustronicTwoRectangleWidget.bottomRectangleActualName).toString().toFloat()
                .toString()
        }

        if (focustronicTwoRectangleWidget.topRectangleUpdateTimeStamp.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.timestamp.text =
                millisToDateTime(System.currentTimeMillis())
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.timestamp.text =
                focustronicTwoRectangleWidget.topRectangleUpdateTimeStamp
        }

        if (focustronicTwoRectangleWidget.bottomRectangleUpdateTimeStamp.isNullOrBlank()) {
            binding.flaskTwoRectangleWidgetEditLayout.timestamp2.text =
                millisToDateTime(System.currentTimeMillis())
        } else {
            binding.flaskTwoRectangleWidgetEditLayout.timestamp2.text =
                focustronicTwoRectangleWidget.bottomRectangleUpdateTimeStamp
        }

        binding.flaskTwoRectangleWidgetEditLayout.card1.setOnClickListener {
            if (!topRectangleActualName.contains("NaN")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexTwoRectangleWidgetBottomSheetBinding.inflate(layoutInflater)

                view.textInput.setText(binding.flaskTwoRectangleWidgetEditLayout.unit.text.toString())

                view.colorPickerButton.iconTint = ColorStateList.valueOf(firstRectangleColor)


                view.saveButton.setOnClickListener {
                    if (view.textInput.text.toString().isEmpty()) {
                        view.textInput.error = "Field is required"
                        return@setOnClickListener
                    }

                    val topRectangleDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius)
                    val topRectangleMutatedDrawable = topRectangleDrawable?.mutate()
                    topRectangleMutatedDrawable?.setTint(firstRectangleColor)

                    binding.flaskTwoRectangleWidgetEditLayout.card1.background = topRectangleMutatedDrawable

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
                                view.colorPickerButton.iconTint = ColorStateList.valueOf(firstRectangleColor)
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
            if (!bottomRectangleActualName.contains("NaN")) {
                val dialog = BottomSheetDialog(requireContext())
                val view = ApexTwoRectangleWidgetBottomSheetBinding.inflate(layoutInflater)

                view.textInput.setText(binding.flaskTwoRectangleWidgetEditLayout.unit2.text.toString())

                view.colorPickerButton.iconTint = ColorStateList.valueOf(secondRectangleColor)

                view.saveButton.setOnClickListener {
                    if (view.textInput.text.toString().isEmpty()) {
                        view.textInput.error = "Field is required"
                        return@setOnClickListener
                    }

                    val topRectangleDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius)
                    val topRectangleMutatedDrawable = topRectangleDrawable?.mutate()
                    topRectangleMutatedDrawable?.setTint(secondRectangleColor)

                    binding.flaskTwoRectangleWidgetEditLayout.card2.background = topRectangleMutatedDrawable

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
                                view.colorPickerButton.iconTint = ColorStateList.valueOf(secondRectangleColor)
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
                Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET,
                SharedPreferences.read(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET, 0) - 1
            )
            widgetsViewModel.deleteFocustronicDoubleRectangleWidget(focustronicTwoRectangleWidget)
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            focustronicTwoRectangleWidget.topRectangleUnit = binding.flaskTwoRectangleWidgetEditLayout.unit.text.toString()
            focustronicTwoRectangleWidget.bottomRectangleUnit = binding.flaskTwoRectangleWidgetEditLayout.unit2.text.toString()
            focustronicTwoRectangleWidget.topRectangleColor = firstRectangleColor
            focustronicTwoRectangleWidget.bottomRectangleColor = secondRectangleColor
            focustronicTwoRectangleWidget.topRectangleActualName = topRectangleActualName
            focustronicTwoRectangleWidget.bottomRectangleActualName = bottomRectangleActualName
            focustronicTwoRectangleWidget.topRectangleValue = topRectangleValue
            focustronicTwoRectangleWidget.bottomRectangleValue = bottomRectangleValue
            focustronicTwoRectangleWidget.topRectangleUpdateTimeStamp = SharedPreferences.read("lastUpdatedApex", "")
            focustronicTwoRectangleWidget.bottomRectangleUpdateTimeStamp = SharedPreferences.read("lastUpdatedApex", "")
            widgetsViewModel.updateFocustronicDoubleRectangleWidget(focustronicTwoRectangleWidget)
            Toast.showSnackbar(binding.root, "Focustronic Two Rectangle Background Widget Updated")
        }

        initValuesRecyclerView()
        
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