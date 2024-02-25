package app.android.damien.reef.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.database_model.CustomWidgetModel
import app.android.damien.reef.databinding.FragmentCustomWidgetAddEditScreenBinding
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.viewmodel.WidgetsViewModel
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.util.Random


class CustomWidgetAddEditScreen : Fragment() {

    private var mDefaultColor = 0
    private val binding by lazy {
        FragmentCustomWidgetAddEditScreenBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val customWidgetModelObject = arguments?.getParcelable<CustomWidgetModel>("customWidgetModelObject")

        if(customWidgetModelObject != null){
            binding.parameterInputField.setText(customWidgetModelObject.parameter)
            binding.valueInputField.setText(customWidgetModelObject.value.toString())
            binding.unitInputField.setText(customWidgetModelObject.unit)
            mDefaultColor = customWidgetModelObject.color
            binding.previewCard.customWidgetLayoutCard.setCardBackgroundColor(mDefaultColor)
            binding.previewCard.parameter.text = customWidgetModelObject.parameter
            binding.previewCard.value.text = customWidgetModelObject.value.toString()
            binding.previewCard.unit.text = customWidgetModelObject.unit
        }else{
            generateRandomDarkColor()
        }

        binding.parameterInputField.doOnTextChanged { text, _, _, _ ->
            binding.previewCard.parameter.text = text
        }

        binding.valueInputField.doOnTextChanged { text, _, _, _ ->
            binding.previewCard.value.text = text
        }

        binding.unitInputField.doOnTextChanged { text, _, _, _ ->
            binding.previewCard.unit.text = text
        }

        binding.colorPicker.setOnClickListener {
            openColorPickerDialogue()
        }

        binding.submit.setOnClickListener {
            var x = 0

            if (!binding.parameterInputField.text.isNullOrBlank()) {
                x++
            } else {
                binding.parameterLayout.error = "Parameter is required"
            }

            if (!binding.valueInputField.text.isNullOrBlank()) {
                x++
            } else {
                binding.valueLayout.error = "Value is required"
            }

            if (!binding.unitInputField.text.isNullOrBlank()) {
                x++
            } else {
                binding.unitLayout.error = "Unit is required"
            }

            if (x == 3) {
                val parameter = binding.parameterInputField.text.toString()
                val value = binding.valueInputField.text.toString().toFloat()
                val unit = binding.unitInputField.text.toString()
                val color = mDefaultColor
                val createTime = System.currentTimeMillis()

                if(customWidgetModelObject != null){
                    widgetsViewModel.update(
                        CustomWidgetModel(
                            customWidgetModelObject.id,
                            Constants.CUSTOM,
                            createTime,
                            createTime,
                            parameter,
                            value,
                            unit,
                            color
                        )
                    )
                }else{
                    widgetsViewModel.insert(
                        CustomWidgetModel(
                            0,
                            Constants.CUSTOM,
                            createTime,
                            createTime,
                            parameter,
                            value,
                            unit,
                            color
                        )
                    )
                }

                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    private fun openColorPickerDialogue() {
        val colorPickerDialogue = AmbilWarnaDialog(context, mDefaultColor,
            object : OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog) {

                }

                override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                    mDefaultColor = color
                    binding.previewCard.customWidgetLayoutCard.setCardBackgroundColor(color)
                }
            })
        colorPickerDialogue.show()
    }

    private fun generateRandomDarkColor(){
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        binding.previewCard.customWidgetLayoutCard.setCardBackgroundColor(color)
        mDefaultColor = color
    }
}