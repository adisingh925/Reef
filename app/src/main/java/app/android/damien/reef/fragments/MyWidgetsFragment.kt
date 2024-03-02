package app.android.damien.reef.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.android.damien.reef.R
import app.android.damien.reef.adapter.MyWidgetsParentAdapter
import app.android.damien.reef.databinding.FragmentMyWidgetsBinding
import app.android.damien.reef.model.MyWidgetsChildModel
import app.android.damien.reef.model.MyWidgetsParentModel
import app.android.damien.reef.utils.Constants


class MyWidgetsFragment : Fragment() {

    private val binding by lazy{
        FragmentMyWidgetsBinding.inflate(layoutInflater)
    }

    private val adapter by lazy{
        MyWidgetsParentAdapter(requireContext(), object : MyWidgetsParentAdapter.OnItemClickListener {
            override fun onItemClick(data: MyWidgetsParentModel) {
                Log.d("MyWidgetsFragment", "onItemClick: ${data.widgetType}")
            }
        })
    }

    private val recyclerView by lazy{
        binding.parentRecyclerView
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.addWidgets.setOnClickListener {
            findNavController().navigate(R.id.action_myWidgetsFragment_to_widgetTypeSelectionScreen)
        }

        initRecyclerview()

        return binding.root
    }

    private fun initRecyclerview(){
        val dummyData = ArrayList<MyWidgetsParentModel>()
        val childData = ArrayList<MyWidgetsChildModel>()
        val childData1 = ArrayList<MyWidgetsChildModel>()
        val childData2 = ArrayList<MyWidgetsChildModel>()
        val childData3 = ArrayList<MyWidgetsChildModel>()
        val childData4 = ArrayList<MyWidgetsChildModel>()
        val childData5 = ArrayList<MyWidgetsChildModel>()
        val childData6 = ArrayList<MyWidgetsChildModel>()

        childData.add(MyWidgetsChildModel(Constants.APEX_TWO_RECTANGLE_WIDGET,1))
        childData.add(MyWidgetsChildModel(Constants.APEX_TWO_RECTANGLE_WIDGET,1))

        childData1.add(MyWidgetsChildModel(Constants.APEX_CIRCLE_WIDGET,1))
        childData1.add(MyWidgetsChildModel(Constants.APEX_CIRCLE_WIDGET,1))

        childData2.add(MyWidgetsChildModel(Constants.APEX_FLASK_BACKGROUND_WIDGET,1))
        childData2.add(MyWidgetsChildModel(Constants.APEX_FLASK_BACKGROUND_WIDGET,1))

        childData3.add(MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,1))
        childData3.add(MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,1))

        childData4.add(MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,1))
        childData4.add(MyWidgetsChildModel(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,1))

        childData5.add(MyWidgetsChildModel(Constants.APEX_POWER_VALUE_WIDGET,1))
        childData5.add(MyWidgetsChildModel(Constants.APEX_POWER_VALUE_WIDGET,1))

        childData6.add(MyWidgetsChildModel(Constants.APEX_WATER_QUALITY_WIDGET,1))
        childData6.add(MyWidgetsChildModel(Constants.APEX_WATER_QUALITY_WIDGET,1))

        dummyData.add(MyWidgetsParentModel(Constants.APEX_TWO_RECTANGLE_WIDGET,childData))
        dummyData.add(MyWidgetsParentModel(Constants.APEX_CIRCLE_WIDGET,childData1))
        dummyData.add(MyWidgetsParentModel(Constants.APEX_FLASK_BACKGROUND_WIDGET,childData2))
        dummyData.add(MyWidgetsParentModel(Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET,childData3))
        dummyData.add(MyWidgetsParentModel(Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET,childData4))
        dummyData.add(MyWidgetsParentModel(Constants.APEX_POWER_VALUE_WIDGET,childData5))
        dummyData.add(MyWidgetsParentModel(Constants.APEX_WATER_QUALITY_WIDGET,childData6))

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.setData(dummyData)
    }
}