package app.android.damien.reef.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import app.android.damien.reef.adapter.PageAdapter
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.CustomWidgetModel
import app.android.damien.reef.database_model.CustomWidgetSingleValueType2Model
import app.android.damien.reef.databinding.FragmentCustomWidgetAddEditScreenBinding
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.viewmodel.WidgetsViewModel
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.util.Random

class CustomWidgetAddEditScreen : Fragment() {

    private val binding by lazy {
        FragmentCustomWidgetAddEditScreenBinding.inflate(layoutInflater)
    }

    private val viewPagerAdapter by lazy{
        PageAdapter(this.childFragmentManager)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding.viewPager.adapter = viewPagerAdapter

        binding.singleValueType2.layout.setOnClickListener {
            binding.viewPager.currentItem = 0
        }

        binding.twoRectangle.layout.setOnClickListener {
            binding.viewPager.currentItem = 1
        }

        binding.singleValueType1.layout.setOnClickListener {
            binding.viewPager.currentItem = 2
        }

        return binding.root
    }
}