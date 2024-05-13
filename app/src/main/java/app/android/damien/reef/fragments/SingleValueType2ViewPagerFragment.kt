package app.android.damien.reef.fragments

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.database_model.CustomWidgetSingleValueType2Model
import app.android.damien.reef.databinding.FragmentSingleValueType2ViewPagerBinding
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Toast
import app.android.damien.reef.viewmodel.WidgetsViewModel
import yuku.ambilwarna.AmbilWarnaDialog


class SingleValueType2ViewPagerFragment : Fragment() {

    private val binding by lazy{
        FragmentSingleValueType2ViewPagerBinding.inflate(layoutInflater)
    }

    private val widgetsViewModel by lazy {
        ViewModelProvider(this)[WidgetsViewModel::class.java]
    }

    var unit = ""
    var actualName = ""
    var givenName = ""
    var value = 0.0f
    var textColor = 0
    var ringColor = 0

    private lateinit var customWidgetSingleValueType2: CustomWidgetSingleValueType2Model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if(arguments != null){
            binding.delete.visibility = View.VISIBLE
            Log.d("SingleValueType2ViewPagerFragment", arguments.toString())
            customWidgetSingleValueType2 = requireArguments().getParcelable(Constants.CUSTOM_WIDGET_SINGLE_VALUE_TYPE_2)!!

            binding.parameterInputField.setText(customWidgetSingleValueType2.givenName)
            binding.valueInputField.setText(customWidgetSingleValueType2.value.toString())
            binding.unitInputField.setText(customWidgetSingleValueType2.unit)

            textColor = customWidgetSingleValueType2.textColor
            ringColor = customWidgetSingleValueType2.ringColor

            binding.colorPickerRing.iconTint = ColorStateList.valueOf(ringColor)
            binding.colorPicker.iconTint = ColorStateList.valueOf(textColor)

            binding.previewCard.value.setTextColor(textColor)
            binding.previewCard.heading.setTextColor(textColor)
            binding.previewCard.unit.setTextColor(textColor)
            binding.previewCard.timestamp.setTextColor(textColor)
            binding.previewCard.value.text = customWidgetSingleValueType2.value.toString()
            binding.previewCard.heading.text = customWidgetSingleValueType2.givenName
            binding.previewCard.unit.text = customWidgetSingleValueType2.unit

            val innerLayoutDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius_black_circular)
            val innerLayoutMutatedDrawable = innerLayoutDrawable?.mutate()
            if (innerLayoutMutatedDrawable is GradientDrawable) {
                innerLayoutMutatedDrawable.setStroke(7, ringColor) // Assuming 3dp width for the stroke
            }

            binding.previewCard.innerLayout.background = innerLayoutMutatedDrawable
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
                widgetsViewModel.updateCustomWidgetSingleValueType2(
                    CustomWidgetSingleValueType2Model(
                        customWidgetSingleValueType2.id,
                        binding.parameterInputField.text.toString(),
                        binding.valueInputField.text.toString().toFloat(),
                        binding.unitInputField.text.toString(),
                        textColor,
                        ringColor
                    )
                )
            }else{
                widgetsViewModel.insertCustomWidgetSingleValueType2(
                    CustomWidgetSingleValueType2Model(
                        0,
                        binding.parameterInputField.text.toString(),
                        binding.valueInputField.text.toString().toFloat(),
                        binding.unitInputField.text.toString(),
                        textColor,
                        ringColor
                    )
                )
            }

            Toast.showSnackbar(binding.root,"Single Value Type 2 Custom Widget Added Successfully")
            findNavController().popBackStack()
        }

        binding.delete.setOnClickListener {
            widgetsViewModel.deleteCustomWidgetSingleValueType2(customWidgetSingleValueType2)
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
                        binding.previewCard.timestamp.setTextColor(textColor)
                    }
                })
            colorPickerDialogue.show()
        }

        binding.colorPickerRing.setOnClickListener {
            val colorPickerDialogue = AmbilWarnaDialog(context, ringColor,
                object : AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onCancel(dialog: AmbilWarnaDialog) {

                    }

                    override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                        ringColor = color
                        binding.colorPickerRing.iconTint = ColorStateList.valueOf(ringColor)

                        val innerLayoutDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius_black_circular)
                        val innerLayoutMutatedDrawable = innerLayoutDrawable?.mutate()
                        if (innerLayoutMutatedDrawable is GradientDrawable) {
                            innerLayoutMutatedDrawable.setStroke(3, ringColor) // Assuming 3dp width for the stroke
                        }

                        binding.previewCard.innerLayout.background = innerLayoutMutatedDrawable
                    }
                })
            colorPickerDialogue.show()
        }

        return binding.root
    }
}