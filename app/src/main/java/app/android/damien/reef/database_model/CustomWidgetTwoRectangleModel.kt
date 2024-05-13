package app.android.damien.reef.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.CUSTOM_WIDGET_TWO_RECTANGLE_TABLE)
data class CustomWidgetTwoRectangleModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var topRectangleActualName: String?,
    var bottomRectangleActualName: String?,
    var topRectangleUpdateTimeStamp: String?,
    var bottomRectangleUpdateTimeStamp: String?,
    var topRectangleUnit: String?,
    var bottomRectangleUnit: String?,
    var topRectangleValue : Float,
    var bottomRectangleValue : Float,
    var topRectangleColor: Int,
    var bottomRectangleColor: Int
)
