package app.android.damien.reef.database_model

import androidx.room.Entity
import app.android.damien.reef.utils.Constants.APEX_WATER_QUALITY_WIDGET_TABLE

@Entity(tableName = APEX_WATER_QUALITY_WIDGET_TABLE)
data class ApexWaterQualityWidget(
    val id : String,
    val name : String,
    val value : String
)
