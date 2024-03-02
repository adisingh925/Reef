package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentApexSelectWidgetScreenBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast


class ApexSelectWidgetScreen : Fragment() {

    private val binding by lazy {
        FragmentApexSelectWidgetScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
                SharedPreferences.write(Constants.APEX_FLASK_BACKGROUND_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexFlaskBackgroundWidgets.itemSubheading.text = SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0).toString() + "/5 widgets added"
        }

        binding.apexCircleWidgets.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0)
            if (widgetCount in 0..4) {
                SharedPreferences.write(Constants.APEX_CIRCLE_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexCircleWidgets.itemSubheading.text = SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0).toString() + "/5 widgets added"
        }

        binding.apex2RectangleWidgets.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0)
            if (widgetCount in 0..4) {
                SharedPreferences.write(Constants.APEX_TWO_RECTANGLE_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apex2RectangleWidgets.itemSubheading.text = SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0).toString() + "/5 widgets added"
        }

        binding.apexSingleValueType1.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 0)
            if (widgetCount in 0..4) {
                SharedPreferences.write(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexSingleValueType1.itemSubheading.text = SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 0).toString() + "/5 widgets added"
        }

        binding.apexSingleValueType2.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0)
            if (widgetCount in 0..4) {
                SharedPreferences.write(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexSingleValueType2.itemSubheading.text = SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0).toString() + "/5 widgets added"
        }

        binding.apexPowerValuesWidgets.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET, 0)
            if (widgetCount in 0..4) {
                SharedPreferences.write(Constants.APEX_POWER_VALUE_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexPowerValuesWidgets.itemSubheading.text = SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET, 0).toString() + "/5 widgets added"
        }

        binding.apexWaterQualityWidget.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0)
            if (widgetCount in 0..4) {
                SharedPreferences.write(Constants.APEX_WATER_QUALITY_WIDGET, widgetCount + 1)
            } else {
                Toast.showSnackbar(binding.root, "You can only add 5 widgets")
            }

            binding.apexWaterQualityWidget.itemSubheading.text = SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0).toString() + "/5 widgets added"
        }



        return binding.root
    }
}