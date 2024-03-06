package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants

@Entity(tableName = Constants.APEX_FLASK_BACKGROUND_WIDGET_TABLE)
data class ApexFlaskBackgroundWidgetModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var slot1Value: Float,
    var slot2Value: Float,
    var slot3Value: Float,
    var slot1ActualName: String?,
    var slot1GivenName : String?,
    var slot2ActualName: String?,
    var slot2GivenName : String?,
    var slot3ActualName: String?,
    var slot3GivenName : String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
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
        parcel.writeString(slot1ActualName)
        parcel.writeString(slot1GivenName)
        parcel.writeString(slot2ActualName)
        parcel.writeString(slot2GivenName)
        parcel.writeString(slot3ActualName)
        parcel.writeString(slot3GivenName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApexFlaskBackgroundWidgetModel> {
        override fun createFromParcel(parcel: Parcel): ApexFlaskBackgroundWidgetModel {
            return ApexFlaskBackgroundWidgetModel(parcel)
        }

        override fun newArray(size: Int): Array<ApexFlaskBackgroundWidgetModel?> {
            return arrayOfNulls(size)
        }
    }

}
