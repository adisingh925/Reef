package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentWidgetTypeSelectionScreenBinding


class WidgetTypeSelectionScreen : Fragment() {

    private val binding by lazy {
        FragmentWidgetTypeSelectionScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding.apexWidgetsButton.setOnClickListener {
            findNavController().navigate(R.id.action_widgetTypeSelectionScreen_to_loginScreen)
        }

        binding.alkatronicWidgetsButton.setOnClickListener {
            findNavController().navigate(R.id.action_widgetTypeSelectionScreen_to_loginScreen)
        }

        binding.customWidgetsButton.setOnClickListener {
            findNavController().navigate(R.id.action_widgetTypeSelectionScreen_to_loginScreen)
        }

        binding.mastertronicWidgetsButton.setOnClickListener {
            findNavController().navigate(R.id.action_widgetTypeSelectionScreen_to_loginScreen)
        }

        return binding.root
    }
}