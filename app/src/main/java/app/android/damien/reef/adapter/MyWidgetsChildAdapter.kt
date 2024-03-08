package app.android.damien.reef.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.android.damien.reef.R
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.database_model.FocustronicOneElementWidgetModel
import app.android.damien.reef.database_model.FocustronicGridWidgetModel
import app.android.damien.reef.database_model.FocustronicSingleValueType1WidgetModel
import app.android.damien.reef.database_model.FocustronicSingleValueType2WidgetModel
import app.android.damien.reef.database_model.FocustronicTwoRectangleWidgetModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants

class MyWidgetsChildAdapter(
    private val context: Context,
    private val widgetType: String,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var setApexCircleWidgetData = emptyList<ApexCircleWidgetModel>()
    private var setApexFlaskBackgroundWidgetData = emptyList<ApexFlaskBackgroundWidgetModel>()
    private var setApexPowerValuesWidgetData = emptyList<ApexPowerValuesWidgetModel>()
    private var setApexTwoRectangleWidgetData = emptyList<ApexTwoRectangleWidgets>()
    private var setApexSingleValueTypeOneWidgetData = emptyList<ApexSingleValueTypeOneModel>()
    private var setApexSingleValueTypeTwoWidgetData = emptyList<ApexSingleValueTypeTwoModel>()
    private var setApexWaterQualityWidgetData = emptyList<ApexWaterQualityWidget>()

    private var setFocustronic1ElementWidgetData = emptyList<FocustronicOneElementWidgetModel>()
    private var setFocustronicGridWidgetData = emptyList<FocustronicGridWidgetModel>()
    private var setFocustronicSingleValueType1WidgetData = emptyList<FocustronicSingleValueType1WidgetModel>()
    private var setFocustronicSingleValueType2WidgetData = emptyList<FocustronicSingleValueType2WidgetModel>()
    private var setFocustronicTwoRectangleWidgetData = emptyList<FocustronicTwoRectangleWidgetModel>()

    interface OnItemClickListener {
        fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel)
        fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel)
        fun onApexCircleWidgetClick(data: ApexCircleWidgetModel)
        fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets)
        fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel)
        fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel)
        fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget)


        fun onFocustronic1ElementWidgetClick(data: FocustronicOneElementWidgetModel)
        fun onFocustronicGridWidgetClick(data: FocustronicGridWidgetModel)
        fun onFocustronicSingleValueType1WidgetClick(data: FocustronicSingleValueType1WidgetModel)
        fun onFocustronicSingleValueType2WidgetClick(data: FocustronicSingleValueType2WidgetModel)
        fun onFocustronicTwoRectangleWidgetClick(data: FocustronicTwoRectangleWidgetModel)
    }

    private inner class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            Log.d("MyWidgetsChildAdapter", "ViewHolder1: ")

        }
    }

    private inner class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val topRectangleTime = itemView.findViewById<TextView>(R.id.timestamp)
        val bottomRectangleTime = itemView.findViewById<TextView>(R.id.timestamp2)
        val topRectangleUnit = itemView.findViewById<TextView>(R.id.unit)
        val bottomRectangleUnit = itemView.findViewById<TextView>(R.id.unit2)
        val topRectangleValue = itemView.findViewById<TextView>(R.id.value)
        val bottomRectangleValue = itemView.findViewById<TextView>(R.id.value2)
        val topCard = itemView.findViewById<View>(R.id.card1)
        val bottomCard = itemView.findViewById<View>(R.id.card2)
        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder2: ")

            topRectangleTime.text =
                setApexTwoRectangleWidgetData[position].topRectangleUpdateTimeStamp
            bottomRectangleTime.text =
                setApexTwoRectangleWidgetData[position].bottomRectangleUpdateTimeStamp

            val topRectangleDrawable =
                context.resources.getDrawable(R.drawable.linear_layout_corner_radius)
            val topRectangleMutatedDrawable = topRectangleDrawable.mutate()
            topRectangleMutatedDrawable.setTint(setApexTwoRectangleWidgetData[position].topRectangleColor)

            topCard.background = topRectangleMutatedDrawable

            val bottomRectangleDrawable =
                context.resources.getDrawable(R.drawable.linear_layout_corner_radius)
            val bottomRectangleMutatedDrawable = bottomRectangleDrawable.mutate()
            bottomRectangleMutatedDrawable.setTint(setApexTwoRectangleWidgetData[position].bottomRectangleColor)

            bottomCard.background = bottomRectangleMutatedDrawable

            topRectangleUnit.text = setApexTwoRectangleWidgetData[position].topRectangleUnit
            bottomRectangleUnit.text = setApexTwoRectangleWidgetData[position].bottomRectangleUnit

            if (setApexTwoRectangleWidgetData[position].topRectangleActualName.isNullOrEmpty()) {
                topRectangleValue.text = "NaN"
            } else {
                topRectangleValue.text =
                    setApexTwoRectangleWidgetData[position].topRectangleValue.toString()
            }

            if (setApexTwoRectangleWidgetData[position].bottomRectangleActualName.isNullOrEmpty()) {
                bottomRectangleValue.text = "NaN"
            } else {
                bottomRectangleValue.text =
                    setApexTwoRectangleWidgetData[position].bottomRectangleValue.toString()
            }

            itemView.setOnClickListener {
                onItemClickListener.onApexTwoRectangleWidgetClick(setApexTwoRectangleWidgetData[position])
            }
        }
    }

    private inner class ViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val slot1Value = itemView.findViewById<TextView>(R.id.slot1value)
        val slot2Value = itemView.findViewById<TextView>(R.id.slot2value)
        val slot3Value = itemView.findViewById<TextView>(R.id.slot3value)
        val slot1name = itemView.findViewById<TextView>(R.id.slot1name)
        val slot2name = itemView.findViewById<TextView>(R.id.slot2name)
        val slot3name = itemView.findViewById<TextView>(R.id.slot3name)

        fun bind(position: Int) {
            Log.d("MyWidgetsChildAdapter", "ViewHolder3: ")
            if (setApexCircleWidgetData[position].slot1GivenName.isNullOrEmpty()) {
                slot1name.text = setApexCircleWidgetData[position].slot1ActualName
            } else {
                slot1name.text = setApexCircleWidgetData[position].slot1GivenName
            }

            if (setApexCircleWidgetData[position].slot2GivenName.isNullOrEmpty()) {
                slot2name.text = setApexCircleWidgetData[position].slot2ActualName
            } else {
                slot2name.text = setApexCircleWidgetData[position].slot2GivenName
            }

            if (setApexCircleWidgetData[position].slot3GivenName.isNullOrEmpty()) {
                slot3name.text = setApexCircleWidgetData[position].slot3ActualName
            } else {
                slot3name.text = setApexCircleWidgetData[position].slot3GivenName
            }

            slot1Value.text = setApexCircleWidgetData[position].slot1Value.toString()
            slot2Value.text = setApexCircleWidgetData[position].slot2Value.toString()
            slot3Value.text = setApexCircleWidgetData[position].slot3Value.toString()

            itemView.setOnClickListener {
                onItemClickListener.onApexCircleWidgetClick(setApexCircleWidgetData[position])
            }
        }
    }

    private inner class ViewHolder4(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val slot1Value = itemView.findViewById<TextView>(R.id.slot1value)
        val slot2Value = itemView.findViewById<TextView>(R.id.slot2value)
        val slot3Value = itemView.findViewById<TextView>(R.id.slot3value)
        val slot1name = itemView.findViewById<TextView>(R.id.slot1name)
        val slot2name = itemView.findViewById<TextView>(R.id.slot2name)
        val slot3name = itemView.findViewById<TextView>(R.id.slot3name)

        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder4: ")

            if (setApexFlaskBackgroundWidgetData[position].slot1GivenName.isNullOrEmpty()) {
                slot1name.text = setApexFlaskBackgroundWidgetData[position].slot1ActualName
            } else {
                slot1name.text = setApexFlaskBackgroundWidgetData[position].slot1GivenName
            }

            if (setApexFlaskBackgroundWidgetData[position].slot2GivenName.isNullOrEmpty()) {
                slot2name.text = setApexFlaskBackgroundWidgetData[position].slot2ActualName
            } else {
                slot2name.text = setApexFlaskBackgroundWidgetData[position].slot2GivenName
            }

            if (setApexFlaskBackgroundWidgetData[position].slot3GivenName.isNullOrEmpty()) {
                slot3name.text = setApexFlaskBackgroundWidgetData[position].slot3ActualName
            } else {
                slot3name.text = setApexFlaskBackgroundWidgetData[position].slot3GivenName
            }

            slot1Value.text = setApexFlaskBackgroundWidgetData[position].slot1Value.toString()
            slot2Value.text = setApexFlaskBackgroundWidgetData[position].slot2Value.toString()
            slot3Value.text = setApexFlaskBackgroundWidgetData[position].slot3Value.toString()

            itemView.setOnClickListener {
                onItemClickListener.onApexFlaskBackgroundWidgetClick(
                    setApexFlaskBackgroundWidgetData[position]
                )
            }
        }
    }

    private inner class ViewHolder5(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val value = itemView.findViewById<TextView>(R.id.value)
        val heading = itemView.findViewById<TextView>(R.id.heading)
        val unit = itemView.findViewById<TextView>(R.id.unit)

        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder5:")

            value.text = setApexSingleValueTypeOneWidgetData[position].value.toString()
            heading.text = setApexSingleValueTypeOneWidgetData[position].actualName
            unit.text = setApexSingleValueTypeOneWidgetData[position].unit

            value.setTextColor(setApexSingleValueTypeOneWidgetData[position].textColor)
            heading.setTextColor(setApexSingleValueTypeOneWidgetData[position].textColor)
            unit.setTextColor(setApexSingleValueTypeOneWidgetData[position].textColor)

            itemView.setOnClickListener {
                onItemClickListener.onApexSingleValueTypeOneWidgetClick(
                    setApexSingleValueTypeOneWidgetData[position]
                )
            }
        }
    }

    private inner class ViewHolder6(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val value = itemView.findViewById<TextView>(R.id.value)
        val heading = itemView.findViewById<TextView>(R.id.heading)
        val unit = itemView.findViewById<TextView>(R.id.unit)
        val innerLayout = itemView.findViewById<LinearLayout>(R.id.innerLayout)
        val timestamp = itemView.findViewById<TextView>(R.id.timestamp)
        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder6: ")

            value.text = setApexSingleValueTypeTwoWidgetData[position].value.toString()
            heading.text = setApexSingleValueTypeTwoWidgetData[position].actualName
            unit.text = setApexSingleValueTypeTwoWidgetData[position].unit

            value.setTextColor(setApexSingleValueTypeTwoWidgetData[position].textColor)
            heading.setTextColor(setApexSingleValueTypeTwoWidgetData[position].textColor)
            unit.setTextColor(setApexSingleValueTypeTwoWidgetData[position].textColor)
            timestamp.text = "${SharedPreferences.read("lastUpdatedApex", "")}"
            timestamp.setTextColor(setApexSingleValueTypeTwoWidgetData[position].textColor)

            val innerLayoutDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius_black_circular)
            val innerLayoutMutatedDrawable = innerLayoutDrawable?.mutate()
            if (innerLayoutMutatedDrawable is GradientDrawable) {
                innerLayoutMutatedDrawable.setStroke(3, setApexSingleValueTypeTwoWidgetData[position].ringColor) // Assuming 3dp width for the stroke
            }

            innerLayout.background = innerLayoutMutatedDrawable

            itemView.setOnClickListener {
                onItemClickListener.onApexSingleValueTypeTwoWidgetClick(
                    setApexSingleValueTypeTwoWidgetData[position]
                )
            }
        }
    }

    private inner class ViewHolder7(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val slot1Value = itemView.findViewById<TextView>(R.id.slot1value)
        val slot2Value = itemView.findViewById<TextView>(R.id.slot2value)
        val slot3Value = itemView.findViewById<TextView>(R.id.slot3value)
        val slot4Value = itemView.findViewById<TextView>(R.id.slot4value)
        val slot5Value = itemView.findViewById<TextView>(R.id.slot5value)

        val slot1name = itemView.findViewById<TextView>(R.id.slot1name)
        val slot2name = itemView.findViewById<TextView>(R.id.slot2name)
        val slot3name = itemView.findViewById<TextView>(R.id.slot3name)
        val slot4name = itemView.findViewById<TextView>(R.id.slot4name)
        val slot5name = itemView.findViewById<TextView>(R.id.slot5name)

        val slot1unit = itemView.findViewById<TextView>(R.id.slot1unit)
        val slot2unit = itemView.findViewById<TextView>(R.id.slot2unit)
        val slot3unit = itemView.findViewById<TextView>(R.id.slot3unit)
        val slot4unit = itemView.findViewById<TextView>(R.id.slot4unit)
        val slot5unit = itemView.findViewById<TextView>(R.id.slot5unit)

        val timestamp = itemView.findViewById<TextView>(R.id.timestamp)

        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder7: ")

            timestamp.text = "as of - ${SharedPreferences.read("lastUpdatedApex", "")}"

            if (setApexWaterQualityWidgetData[position].slot1GivenName.isNullOrEmpty()) {
                slot1name.text = setApexWaterQualityWidgetData[position].slot1ActualName
            } else {
                slot1name.text = setApexWaterQualityWidgetData[position].slot1GivenName
            }

            if (setApexWaterQualityWidgetData[position].slot2GivenName.isNullOrEmpty()) {
                slot2name.text = setApexWaterQualityWidgetData[position].slot2ActualName
            } else {
                slot2name.text = setApexWaterQualityWidgetData[position].slot2GivenName
            }

            if (setApexWaterQualityWidgetData[position].slot3GivenName.isNullOrEmpty()) {
                slot3name.text = setApexWaterQualityWidgetData[position].slot3ActualName
            } else {
                slot3name.text = setApexWaterQualityWidgetData[position].slot3GivenName
            }

            if (setApexWaterQualityWidgetData[position].slot4GivenName.isNullOrEmpty()) {
                slot4name.text = setApexWaterQualityWidgetData[position].slot4ActualName
            } else {
                slot4name.text = setApexWaterQualityWidgetData[position].slot4GivenName
            }

            if (setApexWaterQualityWidgetData[position].slot5GivenName.isNullOrEmpty()) {
                slot5name.text = setApexWaterQualityWidgetData[position].slot5ActualName
            } else {
                slot5name.text = setApexWaterQualityWidgetData[position].slot5GivenName
            }

            slot1Value.text = setApexWaterQualityWidgetData[position].slot1Value.toString()
            slot2Value.text = setApexWaterQualityWidgetData[position].slot2Value.toString()
            slot3Value.text = setApexWaterQualityWidgetData[position].slot3Value.toString()
            slot4Value.text = setApexWaterQualityWidgetData[position].slot4Value.toString()
            slot5Value.text = setApexWaterQualityWidgetData[position].slot5Value.toString()

            slot1unit.text = setApexWaterQualityWidgetData[position].slot1Unit
            slot2unit.text = setApexWaterQualityWidgetData[position].slot2Unit
            slot3unit.text = setApexWaterQualityWidgetData[position].slot3Unit
            slot4unit.text = setApexWaterQualityWidgetData[position].slot4Unit
            slot5unit.text = setApexWaterQualityWidgetData[position].slot5Unit

            itemView.setOnClickListener {
                onItemClickListener.onApexWaterQualityWidgetClick(setApexWaterQualityWidgetData[position])
            }
        }
    }

    private inner class ViewHolder8(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val value = itemView.findViewById<TextView>(R.id.value)
        val unit = itemView.findViewById<TextView>(R.id.unit)
        val heading = itemView.findViewById<TextView>(R.id.heading)
        val card = itemView.findViewById<View>(R.id.custom_widget_layout_card)

        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder8: ")

            unit.text = setFocustronic1ElementWidgetData[position].unit
            heading.text = setFocustronic1ElementWidgetData[position].actualName

            if(setFocustronic1ElementWidgetData[position].actualName.isNullOrEmpty()) {
                heading.text = "NaN"
                value.text = "NaN"
            } else {
                heading.text = setFocustronic1ElementWidgetData[position].actualName
                value.text = setFocustronic1ElementWidgetData[position].value.toString()
            }

            val topRectangleDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius)
            val topRectangleMutatedDrawable = topRectangleDrawable?.mutate()
            topRectangleMutatedDrawable?.setTint(setFocustronic1ElementWidgetData[position].backgroundColor)

            card.background = topRectangleMutatedDrawable

            itemView.setOnClickListener {
                onItemClickListener.onFocustronic1ElementWidgetClick(setFocustronic1ElementWidgetData[position])
            }

        }
    }

    private inner class ViewHolder9(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val topRectangleTime = itemView.findViewById<TextView>(R.id.timestamp)
        val bottomRectangleTime = itemView.findViewById<TextView>(R.id.timestamp2)
        val topRectangleUnit = itemView.findViewById<TextView>(R.id.unit)
        val bottomRectangleUnit = itemView.findViewById<TextView>(R.id.unit2)
        val topRectangleValue = itemView.findViewById<TextView>(R.id.value)
        val bottomRectangleValue = itemView.findViewById<TextView>(R.id.value2)
        val topCard = itemView.findViewById<View>(R.id.card1)
        val bottomCard = itemView.findViewById<View>(R.id.card2)

        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder9: ")

            topRectangleTime.text = SharedPreferences.read("lastUpdatedFocustronicAlkatronic", "")
            bottomRectangleTime.text = SharedPreferences.read("lastUpdatedFocustronicAlkatronic", "")

            val topRectangleDrawable = context.resources.getDrawable(R.drawable.linear_layout_corner_radius)
            val topRectangleMutatedDrawable = topRectangleDrawable.mutate()
            topRectangleMutatedDrawable.setTint(setFocustronicTwoRectangleWidgetData[position].topRectangleColor)

            topCard.background = topRectangleMutatedDrawable

            val bottomRectangleDrawable = context.resources.getDrawable(R.drawable.linear_layout_corner_radius)
            val bottomRectangleMutatedDrawable = bottomRectangleDrawable.mutate()
            bottomRectangleMutatedDrawable.setTint(setFocustronicTwoRectangleWidgetData[position].bottomRectangleColor)

            bottomCard.background = bottomRectangleMutatedDrawable

            topRectangleUnit.text = setFocustronicTwoRectangleWidgetData[position].topRectangleUnit
            bottomRectangleUnit.text = setFocustronicTwoRectangleWidgetData[position].bottomRectangleUnit

            if (setFocustronicTwoRectangleWidgetData[position].topRectangleActualName.isNullOrEmpty()) {
                topRectangleValue.text = "NaN"
            } else {
                topRectangleValue.text = setFocustronicTwoRectangleWidgetData[position].topRectangleValue.toString()
            }

            if (setFocustronicTwoRectangleWidgetData[position].bottomRectangleActualName.isNullOrEmpty()) {
                bottomRectangleValue.text = "NaN"
            } else {
                bottomRectangleValue.text = setFocustronicTwoRectangleWidgetData[position].bottomRectangleValue.toString()
            }

            itemView.setOnClickListener {
                onItemClickListener.onFocustronicTwoRectangleWidgetClick(setFocustronicTwoRectangleWidgetData[position])
            }
        }
    }

    private inner class ViewHolder10(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val value = itemView.findViewById<TextView>(R.id.value)
        val heading = itemView.findViewById<TextView>(R.id.heading)
        val unit = itemView.findViewById<TextView>(R.id.unit)

        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder10: ")

            if(!setFocustronicSingleValueType1WidgetData[position].actualName.isNullOrEmpty()) {
                if(setFocustronicSingleValueType1WidgetData[position].givenName.isNullOrEmpty()) {
                    heading.text = setFocustronicSingleValueType1WidgetData[position].actualName
                } else {
                    heading.text = setFocustronicSingleValueType1WidgetData[position].givenName
                }
                value.text = setFocustronicSingleValueType1WidgetData[position].value.toString()
            } else {
                value.text = "NaN"
                heading.text = "NaN"
            }

            unit.text = setFocustronicSingleValueType1WidgetData[position].unit

            value.setTextColor(setFocustronicSingleValueType1WidgetData[position].textColor)
            heading.setTextColor(setFocustronicSingleValueType1WidgetData[position].textColor)
            unit.setTextColor(setFocustronicSingleValueType1WidgetData[position].textColor)

            itemView.setOnClickListener {
                onItemClickListener.onFocustronicSingleValueType1WidgetClick(setFocustronicSingleValueType1WidgetData[position])
            }

        }
    }

    private inner class ViewHolder11(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val value = itemView.findViewById<TextView>(R.id.value)
        val heading = itemView.findViewById<TextView>(R.id.heading)
        val unit = itemView.findViewById<TextView>(R.id.unit)
        val innerLayout = itemView.findViewById<LinearLayout>(R.id.innerLayout)
        val timestamp = itemView.findViewById<TextView>(R.id.timestamp)

        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder11: ")

            unit.text = setFocustronicSingleValueType2WidgetData[position].unit
            timestamp.text = SharedPreferences.read("lastUpdatedFocustronicAlkatronic", "")

            if(!setFocustronicSingleValueType2WidgetData[position].actualName.isNullOrEmpty()) {
                if(setFocustronicSingleValueType2WidgetData[position].givenName.isNullOrEmpty()) {
                    heading.text = setFocustronicSingleValueType2WidgetData[position].actualName
                } else {
                    heading.text = setFocustronicSingleValueType2WidgetData[position].givenName
                }
                value.text = setFocustronicSingleValueType2WidgetData[position].value.toString()
            } else {
                value.text = "NaN"
                heading.text = "NaN"
            }

            value.setTextColor(setFocustronicSingleValueType2WidgetData[position].textColor)
            heading.setTextColor(setFocustronicSingleValueType2WidgetData[position].textColor)
            unit.setTextColor(setFocustronicSingleValueType2WidgetData[position].textColor)
            timestamp.setTextColor(setFocustronicSingleValueType2WidgetData[position].textColor)

            val innerLayoutDrawable = context?.resources?.getDrawable(R.drawable.linear_layout_corner_radius_black_circular)
            val innerLayoutMutatedDrawable = innerLayoutDrawable?.mutate()
            if (innerLayoutMutatedDrawable is GradientDrawable) {
                innerLayoutMutatedDrawable.setStroke(3, setFocustronicSingleValueType2WidgetData[position].ringColor) // Assuming 3dp width for the stroke
            }

            innerLayout.background = innerLayoutMutatedDrawable

            itemView.setOnClickListener {
                onItemClickListener.onFocustronicSingleValueType2WidgetClick(setFocustronicSingleValueType2WidgetData[position])
            }
        }
    }

    private inner class ViewHolder12(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

            Log.d("MyWidgetsChildAdapter", "ViewHolder12:")

            itemView.setOnClickListener {
                onItemClickListener.onFocustronicGridWidgetClick(setFocustronicGridWidgetData[position])
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        when (widgetType) {
            Constants.APEX_POWER_VALUE_WIDGET -> return 1
            Constants.APEX_TWO_RECTANGLE_WIDGET -> return 2
            Constants.APEX_CIRCLE_WIDGET -> return 3
            Constants.APEX_FLASK_BACKGROUND_WIDGET -> return 4
            Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET -> return 5
            Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET -> return 6
            Constants.APEX_WATER_QUALITY_WIDGET -> return 7
            Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET -> return 8
            Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET -> return 9
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET -> return 10
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET -> return 11
            Constants.FOCUSTRONIC_GRID_WIDGET -> return 12
        }

        Log.d("MyWidgetsChildAdapter", "getItemViewType: ${widgetType}")

        return 1
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            when (widgetType) {
                Constants.APEX_POWER_VALUE_WIDGET -> {
                    (holder as ViewHolder1).bind(position)
                }

                Constants.APEX_TWO_RECTANGLE_WIDGET -> {
                    (holder as ViewHolder2).bind(position)
                }

                Constants.APEX_CIRCLE_WIDGET -> {
                    (holder as ViewHolder3).bind(position)
                }

                Constants.APEX_FLASK_BACKGROUND_WIDGET -> {
                    (holder as ViewHolder4).bind(position)
                }

                Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET -> {
                    (holder as ViewHolder5).bind(position)
                }

                Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET -> {
                    (holder as ViewHolder6).bind(position)
                }

                Constants.APEX_WATER_QUALITY_WIDGET -> {
                    (holder as ViewHolder7).bind(position)
                }

                Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET -> {
                    (holder as ViewHolder8).bind(position)
                }

                Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET -> {
                    (holder as ViewHolder9).bind(position)
                }

                Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET -> {
                    (holder as ViewHolder10).bind(position)
                }

                Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET -> {
                    (holder as ViewHolder11).bind(position)
                }

                Constants.FOCUSTRONIC_GRID_WIDGET -> {
                    (holder as ViewHolder12).bind(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        Log.d("MyWidgetsChildAdapter", "onCreateViewHolder: $viewType")

        when (viewType) {
            1 -> return ViewHolder1(
                LayoutInflater.from(context).inflate(R.layout.power_value_widget, parent, false)
            )

            2 -> return ViewHolder2(
                LayoutInflater.from(context).inflate(R.layout.two_rectangle_widget, parent, false)
            )

            3 -> return ViewHolder3(
                LayoutInflater.from(context).inflate(R.layout.circle_widgets, parent, false)
            )

            4 -> return ViewHolder4(
                LayoutInflater.from(context)
                    .inflate(R.layout.flask_background_widget, parent, false)
            )

            5 -> return ViewHolder5(
                LayoutInflater.from(context)
                    .inflate(R.layout.single_value_type_1_apex, parent, false)
            )

            6 -> return ViewHolder6(
                LayoutInflater.from(context)
                    .inflate(R.layout.single_value_type_2_apex, parent, false)
            )

            7 -> return ViewHolder7(
                LayoutInflater.from(context).inflate(R.layout.water_quality_widget, parent, false)
            )

            8 -> return ViewHolder8(
                LayoutInflater.from(context).inflate(R.layout.custom_widget_layout, parent, false)
            )

            9 -> return ViewHolder9(
                LayoutInflater.from(context).inflate(R.layout.two_rectangle_widget, parent, false)
            )

            10 -> return ViewHolder10(
                LayoutInflater.from(context).inflate(R.layout.single_value_type_1, parent, false)
            )

            11 -> return ViewHolder11(
                LayoutInflater.from(context).inflate(R.layout.single_value_type_2, parent, false)
            )

            12 -> return ViewHolder12(
                LayoutInflater.from(context).inflate(R.layout.grid_widget, parent, false)
            )
        }

        return ViewHolder1(
            LayoutInflater.from(context).inflate(R.layout.power_value_widget, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (widgetType) {
            Constants.APEX_POWER_VALUE_WIDGET -> {
                (holder as ViewHolder1).bind(position)
            }

            Constants.APEX_TWO_RECTANGLE_WIDGET -> {
                (holder as ViewHolder2).bind(position)
            }

            Constants.APEX_CIRCLE_WIDGET -> {
                (holder as ViewHolder3).bind(position)
            }

            Constants.APEX_FLASK_BACKGROUND_WIDGET -> {
                (holder as ViewHolder4).bind(position)
            }

            Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET -> {
                (holder as ViewHolder5).bind(position)
            }

            Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET -> {
                (holder as ViewHolder6).bind(position)
            }

            Constants.APEX_WATER_QUALITY_WIDGET -> {
                (holder as ViewHolder7).bind(position)
            }

            Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET -> {
                (holder as ViewHolder8).bind(position)
            }

            Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET -> {
                (holder as ViewHolder9).bind(position)
            }

            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET -> {
                (holder as ViewHolder10).bind(position)
            }

            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET -> {
                (holder as ViewHolder11).bind(position)
            }

            Constants.FOCUSTRONIC_GRID_WIDGET -> {
                (holder as ViewHolder12).bind(position)
            }
        }
    }

    override fun getItemCount(): Int {
        when (widgetType) {
            Constants.APEX_POWER_VALUE_WIDGET -> return setApexPowerValuesWidgetData.size
            Constants.APEX_TWO_RECTANGLE_WIDGET -> return setApexTwoRectangleWidgetData.size
            Constants.APEX_CIRCLE_WIDGET -> return setApexCircleWidgetData.size
            Constants.APEX_FLASK_BACKGROUND_WIDGET -> return setApexFlaskBackgroundWidgetData.size
            Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET -> return setApexSingleValueTypeOneWidgetData.size
            Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET -> return setApexSingleValueTypeTwoWidgetData.size
            Constants.APEX_WATER_QUALITY_WIDGET -> return setApexWaterQualityWidgetData.size
            Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET -> return setFocustronic1ElementWidgetData.size
            Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET -> return setFocustronicTwoRectangleWidgetData.size
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET -> return setFocustronicSingleValueType1WidgetData.size
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET -> return setFocustronicSingleValueType2WidgetData.size
            Constants.FOCUSTRONIC_GRID_WIDGET -> return setFocustronicGridWidgetData.size
        }
        return setApexFlaskBackgroundWidgetData.size
    }

    fun setApexFlaskBackgroundWidgetData(widgets: List<ApexFlaskBackgroundWidgetModel>) {
        setApexFlaskBackgroundWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexPowerValuesWidgetData(widgets: List<ApexPowerValuesWidgetModel>) {
        setApexPowerValuesWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexCircleWidgetData(widgets: List<ApexCircleWidgetModel>) {
        setApexCircleWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexTwoRectangleWidgetData(widgets: List<ApexTwoRectangleWidgets>) {
        setApexTwoRectangleWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexSingleValueTypeOneWidgetData(widgets: List<ApexSingleValueTypeOneModel>) {
        setApexSingleValueTypeOneWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexSingleValueTypeTwoWidgetData(widgets: List<ApexSingleValueTypeTwoModel>) {
        setApexSingleValueTypeTwoWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexWaterQualityWidgetData(widgets: List<ApexWaterQualityWidget>) {
        setApexWaterQualityWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setFocustronic1ElementWidgetData(widgets: List<FocustronicOneElementWidgetModel>) {
        setFocustronic1ElementWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setFocustronicGridWidgetData(widgets: List<FocustronicGridWidgetModel>) {
        setFocustronicGridWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setFocustronicSingleValueType1WidgetData(widgets: List<FocustronicSingleValueType1WidgetModel>) {
        setFocustronicSingleValueType1WidgetData = widgets
        notifyDataSetChanged()
    }

    fun setFocustronicSingleValueType2WidgetData(widgets: List<FocustronicSingleValueType2WidgetModel>) {
        setFocustronicSingleValueType2WidgetData = widgets
        notifyDataSetChanged()
    }

    fun setFocustronicTwoRectangleWidgetData(widgets: List<FocustronicTwoRectangleWidgetModel>) {
        setFocustronicTwoRectangleWidgetData = widgets
        notifyDataSetChanged()
    }
}