package app.android.damien.reef.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants.APEX_TWO_RECTANGLE_WIDGET_TABLE

@Entity(tableName = APEX_TWO_RECTANGLE_WIDGET_TABLE)
data class ApexTwoRectangleWidgets(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val widgetName: String,
    val widgetType: String,
    val widgetColor: String,
    val widgetSize: String,
    val widgetPosition: String,
    val widgetData: String
)
