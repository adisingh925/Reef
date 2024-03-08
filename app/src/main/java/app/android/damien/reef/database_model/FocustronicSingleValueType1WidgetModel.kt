package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET_TABLE)
data class FocustronicSingleValueType1WidgetModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var actualName: String?,
    var givenName: String?,
    var value: Float,
    var unit: String?,
    var textColor: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(actualName)
        parcel.writeString(givenName)
        parcel.writeFloat(value)
        parcel.writeString(unit)
        parcel.writeInt(textColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FocustronicSingleValueType1WidgetModel> {
        override fun createFromParcel(parcel: Parcel): FocustronicSingleValueType1WidgetModel {
            return FocustronicSingleValueType1WidgetModel(parcel)
        }

        override fun newArray(size: Int): Array<FocustronicSingleValueType1WidgetModel?> {
            return arrayOfNulls(size)
        }
    }
}
