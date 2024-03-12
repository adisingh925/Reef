package app.android.damien.reef.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.CUSTOM_WIDGET_TWO_RECTANGLE_TABLE)
data class CustomWidgetTwoRectangleModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var actualName: String?,
    var givenName: String?,
    var value: Float,
    var unit: String?,
    var textColor: Int
)
