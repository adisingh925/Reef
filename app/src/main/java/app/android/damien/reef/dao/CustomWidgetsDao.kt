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

    @Query("SELECT * from apex_flask_background_widget_table order by id asc")
    fun readApexFlaskBackgroundWidget() : LiveData<List<ApexFlaskBackgroundWidgetModel>>

    @Delete
    fun deleteApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel)

    @Update
    fun updateApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexPowerValuesWidget(data: ApexPowerValuesWidgetModel)

    @Query("SELECT * from apex_power_values_widget_table order by id asc")
    fun readApexPowerValuesWidget() : LiveData<List<ApexPowerValuesWidgetModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexCircleWidget(data: ApexCircleWidgetModel)

    @Query("SELECT * from apex_circle_widget_table order by id asc")
    fun readApexCircleWidget() : LiveData<List<ApexCircleWidgetModel>>

    @Delete
    fun deleteApexCircleWidget(data: ApexCircleWidgetModel)

    @Update
    fun updateApexCircleWidget(data: ApexCircleWidgetModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexTwoRectangleWidget(data: ApexTwoRectangleWidgets)

    @Query("SELECT * from apex_two_rectangle_widget_table order by id asc")
    fun readApexTwoRectangleWidget() : LiveData<List<ApexTwoRectangleWidgets>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel)

    @Query("SELECT * from apex_single_value_type_1_widget_table order by id asc")
    fun readApexSingleValueTypeOneWidget() : LiveData<List<ApexSingleValueTypeOneModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel)

    @Query("SELECT * from apex_single_value_type_2_widget_table order by id asc")
    fun readApexSingleValueTypeTwoWidget() : LiveData<List<ApexSingleValueTypeTwoModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexWaterQualityWidget(data: ApexWaterQualityWidget)

    @Query("SELECT * from apex_water_quality_widget_table order by id asc")
    fun readApexWaterQualityWidget() : LiveData<List<ApexWaterQualityWidget>>
}