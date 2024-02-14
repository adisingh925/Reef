package app.android.damien.reef.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.MainActivity
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

        binding.apexWidgetsButton.setOnClickListener {
            if(isCredentialsExist(Constants.APEX)){
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_addWidgetScreen,
                    getBundle(Constants.ALKATRONIC)
                )
            }else{
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_loginScreen,
                    getBundle(Constants.ALKATRONIC)
                )
            }
        }

        binding.alkatronicWidgetsButton.setOnClickListener {
            if(isCredentialsExist(Constants.ALKATRONIC)){
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_addWidgetScreen,
                    getBundle(Constants.ALKATRONIC)
                )
            }else{
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_loginScreen,
                    getBundle(Constants.ALKATRONIC)
                )
            }
        }

        binding.customWidgetsButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_widgetTypeSelectionScreen_to_addWidgetScreen,
                getBundle(Constants.CUSTOM)
            )
        }

        binding.mastertronicWidgetsButton.setOnClickListener {
            if(isCredentialsExist(Constants.MASTERTRONIC)){
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_addWidgetScreen,
                    getBundle(Constants.ALKATRONIC)
                )
            }else{
                findNavController().navigate(
                    R.id.action_widgetTypeSelectionScreen_to_loginScreen,
                    getBundle(Constants.MASTERTRONIC)
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