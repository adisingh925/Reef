package app.android.damien.reef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.android.damien.reef.R
import app.android.damien.reef.databinding.FragmentLoginScreenBinding


class LoginScreen : Fragment() {

    private val binding by lazy {
        FragmentLoginScreenBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment





        return binding.root
    }
}