package app.android.damien.reef.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.CUSTOM_WIDGET_SINGLE_VALUE_TYPE_1_TABLE)
data class CustomWidgetSingleValueType1Model (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var actualName: String?,
    var givenName: String?,
    var value: Float,
    var unit: String?,
    var textColor: Int
)