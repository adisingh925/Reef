package app.android.damien.reef.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.database_model.CustomWidgetModel

@Dao
interface CustomWidgetsDao {

    /**
     * Insert, update and delete operations for the custom widgets
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data : CustomWidgetModel)

    @Update
    fun update(data : CustomWidgetModel)

    @Delete
    fun delete(data: CustomWidgetModel)

    @Query("SELECT * from custom_widget_table order by id asc")
    fun read() : LiveData<List<CustomWidgetModel>>

    /**
     * Apex widgets
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexPowerValuesWidget(data: ApexPowerValuesWidgetModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexCircleWidget(data: ApexCircleWidgetModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexTwoRectangleWidget(data: ApexTwoRectangleWidgets)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexWaterQualityWidget(data: ApexWaterQualityWidget)
}