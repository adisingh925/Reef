package app.android.damien.reef.database_model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.android.damien.reef.utils.Constants.APEX_POWER_VALUES_WIDGET_TABLE

@Entity(tableName = APEX_POWER_VALUES_WIDGET_TABLE)
data class ApexPowerValuesWidgetModel(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val slot1 : Float,
    val slot2 : Float,
    val slot3 : Float,
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeFloat(slot1)
        parcel.writeFloat(slot2)
        parcel.writeFloat(slot3)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApexPowerValuesWidgetModel> {
        override fun createFromParcel(parcel: Parcel): ApexPowerValuesWidgetModel {
            return ApexPowerValuesWidgetModel(parcel)
        }

        override fun newArray(size: Int): Array<ApexPowerValuesWidgetModel?> {
            return arrayOfNulls(size)
        }
    }
}
