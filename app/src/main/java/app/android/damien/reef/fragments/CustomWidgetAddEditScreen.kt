package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentCustomWidgetAddEditScreenBinding


class CustomWidgetAddEditScreen : Fragment() {

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

        return binding.root
    }
}