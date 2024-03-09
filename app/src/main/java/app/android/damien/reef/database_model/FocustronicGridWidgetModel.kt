package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.FOCUSTRONIC_GRID_WIDGET_TABLE)
data class FocustronicGridWidgetModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var slot1Value: Float,
    var slot2Value: Float,
    var slot3Value: Float,
    var slot4Value : Float,
    var slot5Value : Float,
    var slot6Value : Float,
    var slot7Value : Float,
    var slot8Value : Float,
    var slot1ActualName: String?,
    var slot1GivenName : String?,
    var slot2ActualName: String?,
    var slot2GivenName : String?,
    var slot3ActualName: String?,
    var slot3GivenName : String?,
    var slot4ActualName: String?,
    var slot4GivenName : String?,
    var slot5ActualName: String?,
    var slot5GivenName : String?,
    var slot6ActualName: String?,
    var slot6GivenName : String?,
    var slot7ActualName: String?,
    var slot7GivenName : String?,
    var slot8GivenName : String?,
    var slot8ActualName : String?,
    var slot1Unit: String?,
    var slot2Unit: String?,
    var slot3Unit: String?,
    var slot4Unit: String?,
    var slot5Unit: String?,
    var slot6Unit : String?,
    var slot7Unit : String?,
    var slot8Unit : String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeFloat(slot1Value)
        parcel.writeFloat(slot2Value)
        parcel.writeFloat(slot3Value)
        parcel.writeFloat(slot4Value)
        parcel.writeFloat(slot5Value)
        parcel.writeFloat(slot6Value)
        parcel.writeFloat(slot7Value)
        parcel.writeFloat(slot8Value)
        parcel.writeString(slot1ActualName)
        parcel.writeString(slot1GivenName)
        parcel.writeString(slot2ActualName)
        parcel.writeString(slot2GivenName)
        parcel.writeString(slot3ActualName)
        parcel.writeString(slot3GivenName)
        parcel.writeString(slot4ActualName)
        parcel.writeString(slot4GivenName)
        parcel.writeString(slot5ActualName)
        parcel.writeString(slot5GivenName)
        parcel.writeString(slot6ActualName)
        parcel.writeString(slot6GivenName)
        parcel.writeString(slot7ActualName)
        parcel.writeString(slot7GivenName)
        parcel.writeString(slot8GivenName)
        parcel.writeString(slot8ActualName)
        parcel.writeString(slot1Unit)
        parcel.writeString(slot2Unit)
        parcel.writeString(slot3Unit)
        parcel.writeString(slot4Unit)
        parcel.writeString(slot5Unit)
        parcel.writeString(slot6Unit)
        parcel.writeString(slot7Unit)
        parcel.writeString(slot8Unit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FocustronicGridWidgetModel> {
        override fun createFromParcel(parcel: Parcel): FocustronicGridWidgetModel {
            return FocustronicGridWidgetModel(parcel)
        }

        override fun newArray(size: Int): Array<FocustronicGridWidgetModel?> {
            return arrayOfNulls(size)
        }
    }


}