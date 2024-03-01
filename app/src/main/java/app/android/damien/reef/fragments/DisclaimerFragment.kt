package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentDisclaimerBinding
import app.android.damien.reef.utils.Toast

class DisclaimerFragment : Fragment() {

    private val binding by lazy {
        FragmentDisclaimerBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        try {
            binding.getStartedButton.setOnClickListener {
                findNavController().navigate(R.id.action_disclaimerFragment_to_mainLoginScreen)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.showSnackbar(binding.root, "Something went wrong!")
        }

        return binding.root
    }
}