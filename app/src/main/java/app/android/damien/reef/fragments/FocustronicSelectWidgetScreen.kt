package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.android.damien.reef.databinding.FocustronicSelectWidgetScreenBinding
import app.android.damien.reef.utils.Constants
import kotlin.properties.Delegates


class FocustronicSelectWidgetScreen : Fragment() {

    private val binding by lazy {
        FocustronicSelectWidgetScreenBinding.inflate(layoutInflater)
    }

    private var widgetType by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        widgetType = arguments?.getInt("widgetType") ?: Constants.CUSTOM

        when (widgetType) {
            Constants.APEX -> {

            }

            Constants.FOCUSTRONIC -> {

            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}