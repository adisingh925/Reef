package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.CUSTOM_WIDGET_SINGLE_VALUE_TYPE_2_TABLE)
data class CustomWidgetSingleValueType2Model(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var givenName: String?,
    var value: Float,
    var unit: String?,
    var textColor: Int,
    var ringColor: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(givenName)
        parcel.writeFloat(value)
        parcel.writeString(unit)
        parcel.writeInt(textColor)
        parcel.writeInt(ringColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomWidgetSingleValueType2Model> {
        override fun createFromParcel(parcel: Parcel): CustomWidgetSingleValueType2Model {
            return CustomWidgetSingleValueType2Model(parcel)
        }

        override fun newArray(size: Int): Array<CustomWidgetSingleValueType2Model?> {
            return arrayOfNulls(size)
        }
    }
}
