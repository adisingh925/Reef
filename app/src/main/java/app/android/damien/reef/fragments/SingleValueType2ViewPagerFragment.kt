package app.android.damien.reef.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import app.android.damien.reef.R
import app.android.damien.reef.database_model.CustomWidgetSingleValueType2Model
import app.android.damien.reef.databinding.FragmentSingleValueType2ViewPagerBinding
import app.android.damien.reef.utils.Constants
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
            Log.d("SingleValueType2ViewPagerFragment", arguments.toString())
            customWidgetSingleValueType2 = requireArguments().getParcelable(Constants.CUSTOM_WIDGET_SINGLE_VALUE_TYPE_2)!!

            binding.parameterInputField.setText(customWidgetSingleValueType2.givenName)
            binding.valueInputField.setText(customWidgetSingleValueType2.value.toString())
            binding.unitInputField.setText(customWidgetSingleValueType2.unit)
            textColor = customWidgetSingleValueType2.textColor
            ringColor = customWidgetSingleValueType2.ringColor
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

        binding.colorPicker.setOnClickListener {
            val colorPickerDialogue = AmbilWarnaDialog(context, textColor,
                object : AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onCancel(dialog: AmbilWarnaDialog) {

                    }

                    override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                        textColor = color
                        binding.colorPicker.iconTint = ColorStateList.valueOf(textColor)
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
                    }
                })
            colorPickerDialogue.show()
        }

        return binding.root
    }
}