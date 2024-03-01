package app.android.damien.reef.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.android.damien.reef.databinding.FragmentMainLoginScreenBinding


class MainLoginScreen : Fragment() {

    private val binding by lazy{
        FragmentMainLoginScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }
}