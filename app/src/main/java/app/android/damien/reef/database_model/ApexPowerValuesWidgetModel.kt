package app.android.damien.reef.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants.APEX_POWER_VALUES_WIDGET_TABLE

@Entity(tableName = APEX_POWER_VALUES_WIDGET_TABLE)
data class ApexPowerValuesWidgetModel(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val slot1 : Float,
    val slot2 : Float,
    val slot3 : Float,
)
