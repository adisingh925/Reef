package app.android.damien.reef.database_model

import androidx.room.Entity
import app.android.damien.reef.utils.Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET_TABLE

@Entity(tableName = APEX_SINGLE_VALUE_TYPE_1_WIDGET_TABLE)
data class ApexSingleValueTypeOneModel(
    val id : String,
    val name : String,
    val value : String
)
