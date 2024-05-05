package app.android.damien.reef.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.databinding.FragmentApexSelectWidgetScreenBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ApexSelectWidgetScreen : Fragment() {

    private val binding by lazy {
        FragmentApexSelectWidgetScreenBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        CoroutineScope(Dispatchers.IO).launch {
            Data().getApexData(this)
        }

        binding.addWidgetBackButton.setOnClickListener {
            findNavController().popBackStack(R.id.widgetTypeSelectionScreen, false)
        }

        binding.apexFlaskBackgroundWidgets.itemSubheading.text = SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0).toString() + "/5 widgets added"
        binding.apexCircleWidgets.itemSubheading.text = SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0).toString() + "/5 widgets added"
        binding.apex2RectangleWidgets.itemSubheading.text = SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0).toString() + "/5 widgets added"
        binding.apexSingleValueType1.itemSubheading.text = SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 0).toString() + "/5 widgets added"
        binding.apexSingleValueType2.itemSubheading.text = SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0).toString() + "/5 widgets added"
        binding.apexPowerValuesWidgets.itemSubheading.text = SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET, 0).toString() + "/5 widgets added"
        binding.apexWaterQualityWidget.itemSubheading.text = SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0).toString() + "/5 widgets added"

        binding.apexFlaskBackgroundWidgets.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertApexFlaskBackgroundWidget(
                    ApexFlaskBackgroundWidgetModel(
                        0,
                        0.0f,
                        0.0f,
                        0.0f,
                        "NaN",
                        "",
                        "NaN",
                        "",
                        "NaN",
                        ""
                    )
                )
                SharedPreferences.write(Constants.APEX_FLASK_BACKGROUND_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexFlaskBackgroundWidgets.itemSubheading.text =
                SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.apexCircleWidgets.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertApexCircleWidget(
                    ApexCircleWidgetModel(
                        0,
                        0.0f,
                        0.0f,
                        0.0f,
                        "NaN",
                        "",
                        "NaN",
                        "",
                        "NaN",
                        ""
                    )
                )
                SharedPreferences.write(Constants.APEX_CIRCLE_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexCircleWidgets.itemSubheading.text =
                SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.apex2RectangleWidgets.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertApexTwoRectangleWidget(
                    ApexTwoRectangleWidgets(
                        0,
                        "NaN",
                        "NaN",
                        SharedPreferences.read("lastUpdatedApex", ""),
                        SharedPreferences.read("lastUpdatedApex", ""),
                        "Unit 1",
                        "Unit 2",
                        0.0f,
                        0.0f,
                        Color.parseColor("#cc7700"),
                        Color.parseColor("#cc7700")
                    )
                )
                SharedPreferences.write(Constants.APEX_TWO_RECTANGLE_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apex2RectangleWidgets.itemSubheading.text =
                SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.apexSingleValueType1.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertApexSingleValueTypeOneWidget(
                    ApexSingleValueTypeOneModel(
                        0,
                        "NaN",
                        "",
                        0.0f,
                        "Unit",
                        Color.parseColor("#ffffff")
                    )
                )
                SharedPreferences.write(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexSingleValueType1.itemSubheading.text =
                SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.apexSingleValueType2.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertApexSingleValueTypeTwoWidget(
                    ApexSingleValueTypeTwoModel(
                        0,
                        "NaN",
                        "",
                        0.0f,
                        "Unit",
                        Color.parseColor("#ffffff"),
                        Color.parseColor("#ffffff")
                    )
                )
                SharedPreferences.write(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexSingleValueType2.itemSubheading.text =
                SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.apexPowerValuesWidgets.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertApexPowerValuesWidget(ApexPowerValuesWidgetModel(
                    0,
                    0.0f,
                    0.0f,
                    0.0f,
                    "",
                    "",
                    ""
                ))
                SharedPreferences.write(Constants.APEX_POWER_VALUE_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexPowerValuesWidgets.itemSubheading.text =
                SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        binding.apexWaterQualityWidget.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertApexWaterQualityWidget(
                    ApexWaterQualityWidget(
                        0,
                        0.0f,
                        0.0f,
                        0.0f,
                        0.0f,
                        0.0f,
                        "NaN",
                        "",
                        "NaN",
                        "",
                        "NaN",
                        "",
                        "NaN",
                        "",
                        "NaN",
                        "",
                        "Unit 1",
                        "Unit 2",
                        "Unit 3",
                        "Unit 4",
                        "Unit 5"
                    )
                )
                SharedPreferences.write(Constants.APEX_WATER_QUALITY_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexWaterQualityWidget.itemSubheading.text =
                SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0)
                    .toString() + "/5 widgets added"
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack(R.id.widgetTypeSelectionScreen, false)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }
}