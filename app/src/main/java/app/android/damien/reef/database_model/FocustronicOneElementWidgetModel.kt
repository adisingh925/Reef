package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET_TABLE)
data class FocustronicOneElementWidgetModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var actualName: String?,
    var value: Float,
    var unit: String?,
    var backgroundColor: Int
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
        parcel.writeString(actualName)
        parcel.writeFloat(value)
        parcel.writeString(unit)
        parcel.writeInt(backgroundColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FocustronicOneElementWidgetModel> {
        override fun createFromParcel(parcel: Parcel): FocustronicOneElementWidgetModel {
            return FocustronicOneElementWidgetModel(parcel)
        }

        override fun newArray(size: Int): Array<FocustronicOneElementWidgetModel?> {
            return arrayOfNulls(size)
        }
    }
}
