package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentWelcomeScreenBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Toast


class WelcomeScreen : Fragment() {

    private val binding by lazy {
        FragmentWelcomeScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        try {
            if (SharedPreferences.read("email", "") != "" && SharedPreferences.read(
                    "nickname",
                    ""
                ) != ""
            ) {
                findNavController().navigate(R.id.myWidgetsFragment)
            }

            binding.welcomeScreenAddWidgetsButton.setOnClickListener {
                findNavController().navigate(R.id.action_welcomeScreen_to_disclaimerFragment)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.showSnackbar(binding.root, "Something went wrong!")
        }

        return binding.root
    }

}