package app.android.damien.reef.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.database_model.CustomWidgetTwoRectangleModel
import app.android.damien.reef.databinding.FragmentTwoRectangleViewPagerBinding
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel


class TwoRectangleViewPagerFragment : Fragment() {

    private val binding by lazy{
        FragmentTwoRectangleViewPagerBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    var topUnit = ""
    var topValue = 0.0f
    var bottomValue = 0.0f
    var bottomUnit = ""

    private lateinit var customWidgetTwoRectangleModel: CustomWidgetTwoRectangleModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if(arguments != null){
            Log.d("SingleValueType2ViewPagerFragment", arguments.toString())
            customWidgetTwoRectangleModel = requireArguments().getParcelable(Constants.CUSTOM_WIDGET_TWO_RECTANGLE)!!

            binding.previewCard.unit.setText(customWidgetTwoRectangleModel.topRectangleUnit)
            binding.previewCard.value.setText(customWidgetTwoRectangleModel.topRectangleValue.toString())
            binding.previewCard.unit2.setText(customWidgetTwoRectangleModel.bottomRectangleUnit)
            binding.previewCard.value2.setText(customWidgetTwoRectangleModel.bottomRectangleValue.toString())
        }

        binding.topRectangleUnitInput.addTextChangedListener {
            binding.previewCard.unit.text = it.toString()
        }

        binding.bottomRectangleUnitInput.addTextChangedListener {
            binding.previewCard.unit2.text = it.toString()
        }

        binding.topRectangleValueInput.addTextChangedListener {
            binding.previewCard.value.text = it.toString()
        }

        binding.bottomRectangleValueInput.addTextChangedListener {
            binding.previewCard.value2.text = it.toString()
        }

        binding.submit.setOnClickListener {
            widgetsViewModel.insertCustomWidgetTwoRectangle(
                CustomWidgetTwoRectangleModel(
                    0,
                    null,
                    null,
                    Data().millisToDateTime(System.currentTimeMillis()),
                    Data().millisToDateTime(System.currentTimeMillis()),
                    binding.topRectangleUnitInput.text.toString(),
                    binding.bottomRectangleUnitInput.text.toString(),
                    binding.topRectangleValueInput.text.toString().toFloat(),
                    binding.bottomRectangleValueInput.text.toString().toFloat(),
                    -1,
                    -1
                )
            )

            Toast.showSnackbar(binding.root,"Two Rectangle Custom Widget Added Successfully")
            findNavController().popBackStack()
        }

        return binding.root
    }
}