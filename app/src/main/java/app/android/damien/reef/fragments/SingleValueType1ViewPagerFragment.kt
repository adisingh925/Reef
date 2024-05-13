package app.android.damien.reef.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.database_model.CustomWidgetSingleValueType1Model
import app.android.damien.reef.databinding.FragmentSingleValueType1ViewPagerBinding
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import yuku.ambilwarna.AmbilWarnaDialog


class SingleValueType1ViewPagerFragment : Fragment() {

    private val binding by lazy{
        FragmentSingleValueType1ViewPagerBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    var unit = ""
    var givenName = ""
    var value = 0.0f
    var textColor = 0

    private lateinit var customWidgetSingleValueType1: CustomWidgetSingleValueType1Model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        if(arguments != null){
            Log.d("SingleValueType2ViewPagerFragment", arguments.toString())
            customWidgetSingleValueType1 = requireArguments().getParcelable(Constants.CUSTOM_WIDGET_SINGLE_VALUE_TYPE_1)!!

            binding.parameterInputField.setText(customWidgetSingleValueType1.givenName)
            binding.valueInputField.setText(customWidgetSingleValueType1.value.toString())
            binding.unitInputField.setText(customWidgetSingleValueType1.unit)

            textColor = customWidgetSingleValueType1.textColor
            binding.colorPicker.iconTint = ColorStateList.valueOf(textColor)

            binding.previewCard.value.setTextColor(textColor)
            binding.previewCard.heading.setTextColor(textColor)
            binding.previewCard.unit.setTextColor(textColor)

            binding.previewCard.value.text = binding.valueInputField.text.toString()
            binding.previewCard.heading.text = binding.parameterInputField.text.toString()
            binding.previewCard.unit.text = binding.unitInputField.text.toString()
        }

        binding.valueInputField.addTextChangedListener {
            binding.previewCard.value.text = it.toString()
        }

        binding.parameterInputField.addTextChangedListener {
            binding.previewCard.heading.text = it.toString()
        }

        binding.unitInputField.addTextChangedListener {
            binding.previewCard.unit.text = it.toString()
        }

        binding.submit.setOnClickListener {
            if(arguments != null){
                widgetsViewModel.updateCustomWidgetSingleValueType1(
                    CustomWidgetSingleValueType1Model(
                        customWidgetSingleValueType1.id,
                        binding.parameterInputField.text.toString(),
                        binding.valueInputField.text.toString().toFloat(),
                        binding.unitInputField.text.toString(),
                        textColor,
                    )
                )
                Toast.showSnackbar(binding.root,"Single Value Type 1 Custom Widget Updated Successfully")
                findNavController().popBackStack()
                return@setOnClickListener
            }

            widgetsViewModel.insertCustomWidgetSingleValueType1(
                CustomWidgetSingleValueType1Model(
                    0,
                    binding.parameterInputField.text.toString(),
                    binding.valueInputField.text.toString().toFloat(),
                    binding.unitInputField.text.toString(),
                    textColor,
                )
            )

            Toast.showSnackbar(binding.root,"Single Value Type 1 Custom Widget Added Successfully")
            findNavController().popBackStack()
        }

        binding.delete.setOnClickListener {
            widgetsViewModel.deleteCustomWidgetSingleValueType1(customWidgetSingleValueType1)
            findNavController().popBackStack()
        }

        binding.colorPicker.setOnClickListener {
            val colorPickerDialogue = AmbilWarnaDialog(context, textColor,
                object : AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onCancel(dialog: AmbilWarnaDialog) {

                    }

                    override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                        textColor = color
                        binding.colorPicker.iconTint = ColorStateList.valueOf(textColor)
                        binding.previewCard.value.setTextColor(textColor)
                        binding.previewCard.heading.setTextColor(textColor)
                        binding.previewCard.unit.setTextColor(textColor)
                    }
                })
            colorPickerDialogue.show()
        }

        return binding.root
    }
}