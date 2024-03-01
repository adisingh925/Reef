package app.android.damien.reef.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.databinding.FragmentMainLoginScreenBinding
import app.android.damien.reef.utils.Toast


class MainLoginScreen : Fragment() {

    private val binding by lazy {
        FragmentMainLoginScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        try {
            binding.loginBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.showSnackbar(binding.root, "Something went wrong!")
        }
        return binding.root
    }
}