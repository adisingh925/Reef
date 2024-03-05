package app.android.damien.reef.database_model

import androidx.room.Entity
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET_TABLE)
data class ApexSingleValueTypeTwoModel(
    val id : String,
    val name : String,
    val value : String
)
