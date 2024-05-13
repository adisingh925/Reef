package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.CUSTOM_WIDGET_SINGLE_VALUE_TYPE_1_TABLE)
data class CustomWidgetSingleValueType1Model (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var givenName: String?,
    var value: Float,
    var unit: String?,
    var textColor: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(givenName)
        parcel.writeFloat(value)
        parcel.writeString(unit)
        parcel.writeInt(textColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomWidgetSingleValueType1Model> {
        override fun createFromParcel(parcel: Parcel): CustomWidgetSingleValueType1Model {
            return CustomWidgetSingleValueType1Model(parcel)
        }

        override fun newArray(size: Int): Array<CustomWidgetSingleValueType1Model?> {
            return arrayOfNulls(size)
        }
    }
}