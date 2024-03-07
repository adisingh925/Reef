package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants.APEX_TWO_RECTANGLE_WIDGET_TABLE

@Entity(tableName = APEX_TWO_RECTANGLE_WIDGET_TABLE)
data class ApexTwoRectangleWidgets(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var topRectangleActualName: String?,
    var bottomRectangleActualName: String?,
    var topRectangleUpdateTimeStamp: String?,
    var bottomRectangleUpdateTimeStamp: String?,
    var topRectangleUnit: String?,
    var bottomRectangleUnit: String?,
    var topRectangleValue : Float,
    var bottomRectangleValue : Float,
    var topRectangleColor: Int,
    var bottomRectangleColor: Int
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(topRectangleActualName)
        parcel.writeString(bottomRectangleActualName)
        parcel.writeString(topRectangleUpdateTimeStamp)
        parcel.writeString(bottomRectangleUpdateTimeStamp)
        parcel.writeString(topRectangleUnit)
        parcel.writeString(bottomRectangleUnit)
        parcel.writeFloat(topRectangleValue)
        parcel.writeFloat(bottomRectangleValue)
        parcel.writeInt(topRectangleColor)
        parcel.writeInt(bottomRectangleColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApexTwoRectangleWidgets> {
        override fun createFromParcel(parcel: Parcel): ApexTwoRectangleWidgets {
            return ApexTwoRectangleWidgets(parcel)
        }

        override fun newArray(size: Int): Array<ApexTwoRectangleWidgets?> {
            return arrayOfNulls(size)
        }
    }
}
