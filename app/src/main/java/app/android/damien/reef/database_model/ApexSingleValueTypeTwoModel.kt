package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET_TABLE)
data class ApexSingleValueTypeTwoModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var actualName: String?,
    var givenName: String?,
    var value: Float,
    var unit: String?,
    var textColor: Int,
    val ringColor: Int
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readInt(),
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
        parcel.writeInt(ringColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApexSingleValueTypeTwoModel> {
        override fun createFromParcel(parcel: Parcel): ApexSingleValueTypeTwoModel {
            return ApexSingleValueTypeTwoModel(parcel)
        }

        override fun newArray(size: Int): Array<ApexSingleValueTypeTwoModel?> {
            return arrayOfNulls(size)
        }
    }
}
