package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.APEX_GRAPH_WIDGET_TABLE)
data class ApexGraphWidgetModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val actualName: String?,
    val value: String,
    val unit: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString().toString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(actualName)
        parcel.writeString(value)
        parcel.writeString(unit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApexGraphWidgetModel> {
        override fun createFromParcel(parcel: Parcel): ApexGraphWidgetModel {
            return ApexGraphWidgetModel(parcel)
        }

        override fun newArray(size: Int): Array<ApexGraphWidgetModel?> {
            return arrayOfNulls(size)
        }
    }
}