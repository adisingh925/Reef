package app.android.damien.reef.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentWidgetTypeSelectionScreenBinding
import app.android.damien.reef.utils.Constants

class WidgetTypeSelectionScreen : Fragment() {

    private val binding by lazy {
        FragmentWidgetTypeSelectionScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding.widgetSelectionBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.apexWidgetsButton.setOnClickListener {
            if(isCredentialsExist(Constants.APEX)){
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_apexSelectWidgetScreen,
                    getBundle(Constants.APEX)
                )
            }else{
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_loginScreen,
                    getBundle(Constants.APEX)
                )
            }
        }

        binding.customWidgetsButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_widgetTypeSelectionScreen_to_customWidgetAddEditScreen,
                getBundle(Constants.CUSTOM)
            )
        }

        binding.focustronicWidgetsButton.setOnClickListener {
            if(isCredentialsExist(Constants.FOCUSTRONIC)){
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_focustronicSelectWidgetScreen,
                    getBundle(Constants.FOCUSTRONIC)
                )
            }else{
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_loginScreen,
                    getBundle(Constants.FOCUSTRONIC)
                )
            }
        }

        return binding.root
    }

    private fun getBundle(value: Int): Bundle {
        return Bundle().apply {
            putInt("widgetType", value)
        }
    }

    private fun isCredentialsExist(widgetType : Int) : Boolean{
        return !(app.android.damien.reef.storage.SharedPreferences.read(widgetType.toString() + "email", "") == "" &&
                app.android.damien.reef.storage.SharedPreferences.read(widgetType.toString() + "password", "") == "")
    }
}