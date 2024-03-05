package app.android.damien.reef.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.APEX_FLASK_BACKGROUND_WIDGET_TABLE)
data class ApexFlaskBackgroundWidgetModel(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val slot1Value : Float,
    val slot2Value : Float,
    val slot3Value : Float,
    val slot1Name : String,
    val slot2Name : String,
    val slot3Name : String,
)
