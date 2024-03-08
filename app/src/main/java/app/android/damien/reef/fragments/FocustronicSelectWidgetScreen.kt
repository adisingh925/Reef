package app.android.damien.reef.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import app.android.damien.reef.database_model.FocustronicOneElementWidgetModel
import app.android.damien.reef.database_model.FocustronicGridWidgetModel
import app.android.damien.reef.database_model.FocustronicSingleValueType1WidgetModel
import app.android.damien.reef.database_model.FocustronicSingleValueType2WidgetModel
import app.android.damien.reef.database_model.FocustronicTwoRectangleWidgetModel
import app.android.damien.reef.databinding.FocustronicSelectWidgetScreenBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel


class FocustronicSelectWidgetScreen : Fragment() {

    private val binding by lazy {
        FocustronicSelectWidgetScreenBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.focustronic1ElementWidget.itemSubheading.text = SharedPreferences.read(
            Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET,
            0
        ).toString() + "/5 widgets added"

        binding.focustronicSingleValueType1.itemSubheading.text = SharedPreferences.read(
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,
            0
        ).toString() + "/5 widgets added"

        binding.focustronicSingleValueType2.itemSubheading.text = SharedPreferences.read(
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,
            0
        ).toString() + "/5 widgets added"

        binding.focustronic2RectangleWidget.itemSubheading.text = SharedPreferences.read(
            Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET,
            0
        ).toString() + "/5 widgets added"

        binding.focustronicGridWidget.itemSubheading.text = SharedPreferences.read(
            Constants.FOCUSTRONIC_GRID_WIDGET,
            0
        ).toString() + "/5 widgets added"

        binding.focustronicGridWidget.layout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.FOCUSTRONIC_GRID_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertFocustronicGridWidget(
                    FocustronicGridWidgetModel(
                        0,
                        "",
                        "",
                        0.0f,
                        "Unit",
                        0
                    )
                )
                SharedPreferences.write(Constants.FOCUSTRONIC_GRID_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }
            binding.focustronicGridWidget.itemSubheading.text =
                SharedPreferences.read(Constants.FOCUSTRONIC_GRID_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.focustronic1ElementWidget.layout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertFocustronicOneElementWidget(
                    FocustronicOneElementWidgetModel(
                        0,
                        "",
                        0.0f,
                        "Unit",
                        Color.parseColor("#cc7700")
                    )
                )
                SharedPreferences.write(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }
            binding.focustronic1ElementWidget.itemSubheading.text =
                SharedPreferences.read(Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.focustronicSingleValueType1.layout.setOnClickListener {
            val widgetCount =
                SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertFocustronicSingleValueTypeOneWidget(
                    FocustronicSingleValueType1WidgetModel(
                        0,
                        "",
                        "",
                        0.0f,
                        "Unit",
                        0
                    )
                )
                SharedPreferences.write(
                    Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET,
                    widgetCount + 1
                )
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }
            binding.focustronicSingleValueType1.itemSubheading.text =
                SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.focustronicSingleValueType2.layout.setOnClickListener {
            val widgetCount =
                SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertFocustronicSingleValueTypeTwoWidget(
                    FocustronicSingleValueType2WidgetModel(
                        0,
                        "",
                        "",
                        0.0f,
                        "Unit",
                        0,
                        0
                    )
                )
                SharedPreferences.write(
                    Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET,
                    widgetCount + 1
                )
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }
            binding.focustronicSingleValueType2.itemSubheading.text =
                SharedPreferences.read(Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.focustronic2RectangleWidget.layout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertFocustronicDoubleRectangleWidget(
                    FocustronicTwoRectangleWidgetModel(
                        0,
                        "",
                        "",
                        SharedPreferences.read("lastUpdatedApex", ""),
                        SharedPreferences.read("lastUpdatedApex", ""),
                        "Unit 1",
                        "Unit 2",
                        0f,
                        0f,
                        Color.parseColor("#cc7700"),
                        Color.parseColor("#cc7700")
                    )
                )
                SharedPreferences.write(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }
            binding.focustronic2RectangleWidget.itemSubheading.text =
                SharedPreferences.read(Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}