package app.android.damien.reef.database_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.APEX_GRAPH_WIDGET_TABLE)
data class ApexGraphWidgetModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val actualName: String?,
    val givenName: String?,
    val value: Float,
    val unit: String?,
    val textColor: Int
)