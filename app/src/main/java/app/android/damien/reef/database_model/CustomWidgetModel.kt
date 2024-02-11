package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.CUSTOM_WIDGET_TABLE)
data class CustomWidgetModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val widgetType: Int,
    val createdAt: Long,
    val updatedAt: Long,
    val parameter: String?,
    val value: Float,
    val unit: String?,
    val color: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(widgetType)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
        parcel.writeString(parameter)
        parcel.writeFloat(value)
        parcel.writeString(unit)
        parcel.writeInt(color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomWidgetModel> {
        override fun createFromParcel(parcel: Parcel): CustomWidgetModel {
            return CustomWidgetModel(parcel)
        }

        override fun newArray(size: Int): Array<CustomWidgetModel?> {
            return arrayOfNulls(size)
        }
    }

}