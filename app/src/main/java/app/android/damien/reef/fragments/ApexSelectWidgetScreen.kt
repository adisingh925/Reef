package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.databinding.FragmentApexSelectWidgetScreenBinding
import app.android.damien.reef.model.ApexApiResponse
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApexSelectWidgetScreen : Fragment() {

    private val binding by lazy {
        FragmentApexSelectWidgetScreenBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getApexApiResponse()

        binding.addWidgetBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.apexFlaskBackgroundWidgets.itemSubheading.text =
            SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0)
                .toString() + "/5 widgets added"
        binding.apexCircleWidgets.itemSubheading.text =
            SharedPreferences.read(Constants.APEX_CIRCLE_WIDGET, 0).toString() + "/5 widgets added"
        binding.apex2RectangleWidgets.itemSubheading.text =
            SharedPreferences.read(Constants.APEX_TWO_RECTANGLE_WIDGET, 0)
                .toString() + "/5 widgets added"
        binding.apexSingleValueType1.itemSubheading.text =
            SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET, 0)
                .toString() + "/5 widgets added"
        binding.apexSingleValueType2.itemSubheading.text =
            SharedPreferences.read(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET, 0)
                .toString() + "/5 widgets added"
        binding.apexPowerValuesWidgets.itemSubheading.text =
            SharedPreferences.read(Constants.APEX_POWER_VALUE_WIDGET, 0)
                .toString() + "/5 widgets added"
        binding.apexWaterQualityWidget.itemSubheading.text =
            SharedPreferences.read(Constants.APEX_WATER_QUALITY_WIDGET, 0)
                .toString() + "/5 widgets added"

        binding.apexFlaskBackgroundWidgets.flaskConstraintLayout.setOnClickListener {
            val widgetCount = SharedPreferences.read(Constants.APEX_FLASK_BACKGROUND_WIDGET, 0)
            if (widgetCount in 0..4) {
                widgetsViewModel.insertApexFlaskBackgroundWidget(
                    ApexFlaskBackgroundWidgetModel(
                        0,
                        0f,
                        0f,
                        0f,
                        "Slot 1",
                        "",
                        "Slot 2",
                        "",
                        "Slot 3",
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
                        0f,
                        0f,
                        0f,
                        "Slot 1",
                        "",
                        "Slot 2",
                        "",
                        "Slot 3",
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

    private fun getApexApiResponse() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.apiService.getApexData(
                SharedPreferences.read(
                    Constants.APEX.toString() + "nickname",
                    ""
                ).toString()
            )

            response.enqueue(object : Callback<ApexApiResponse> {
                override fun onResponse(
                    call: Call<ApexApiResponse>,
                    response: Response<ApexApiResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            val gson = Gson()
                            val jsonData = gson.toJson(data)
                            SharedPreferences.write("apexData", jsonData)
                        }
                    }
                }

                override fun onFailure(call: Call<ApexApiResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}