package app.android.damien.reef.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import app.android.damien.reef.databinding.FragmentCustomWidgetAddEditScreenBinding
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener

class CustomWidgetAddEditScreen : Fragment() {

    private var mDefaultColor = 0
    private val binding by lazy {
        FragmentCustomWidgetAddEditScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding.parameterInputField.doOnTextChanged { text, start, before, count ->
            binding.parameter.text = text
        }

        binding.valueInputField.doOnTextChanged { text, start, before, count ->
            binding.value.text = text
        }

        binding.unitInputField.doOnTextChanged { text, start, before, count ->
            binding.unit.text = text
        }

        binding.colorPicker.setOnClickListener {
            openColorPickerDialogue()
        }

        return binding.root
    }

    private fun openColorPickerDialogue() {
        // one is the context, second is default color,
        val colorPickerDialogue = AmbilWarnaDialog(context, mDefaultColor,
            object : OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog) {
                    // leave this function body as
                    // blank, as the dialog
                    // automatically closes when
                    // clicked on cancel button
                }

                override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                    // change the mDefaultColor to
                    // change the GFG text color as
                    // it is returned when the OK
                    // button is clicked from the
                    // color picker dialog
                    mDefaultColor = color

                    // now change the picked color
                    // preview box to mDefaultColor
                    binding.previewCard.setCardBackgroundColor(color)
                }
            })
        colorPickerDialogue.show()
    }
}