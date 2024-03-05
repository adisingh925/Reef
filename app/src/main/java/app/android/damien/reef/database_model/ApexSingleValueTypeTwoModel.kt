package app.android.damien.reef.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET_TABLE)
data class ApexSingleValueTypeTwoModel(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val name : String,
    val value : String
)
