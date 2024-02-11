package app.android.damien.reef.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.CUSTOM_WIDGET_TABLE)
data class CustomWidgetModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val widgetType : Int,
    val createdAt: Long,
    val updatedAt: Long,
    val parameter: String,
    val value: Float,
    val unit : String,
    val color : Int
)