package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentAddWidgetScreenBinding
import app.android.damien.reef.utils.Constants
import kotlin.properties.Delegates


class AddWidgetScreen : Fragment() {

    private val binding by lazy {
        FragmentAddWidgetScreenBinding.inflate(layoutInflater)
    }

    private var widgetType by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        widgetType = arguments?.getInt("widgetType") ?: Constants.CUSTOM

        binding.addWidget.setOnClickListener {
            when (widgetType) {
                Constants.APEX -> {
                    // Add Apex widget
                }
                Constants.ALKATRONIC -> {
                    // Add Alkatronic widget
                }
                Constants.MASTERTRONIC -> {
                    // Add Mastertronic widget
                }
                Constants.CUSTOM -> {
                    findNavController().navigate(R.id.action_addWidgetScreen_to_customWidgetAddEditScreen)
                }
            }
        }

        return binding.root
    }
}