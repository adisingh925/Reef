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
import app.android.damien.reef.database_model.ApexGraphWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.database_model.CustomWidgetModel
import app.android.damien.reef.database_model.CustomWidgetSingleValueType1Model
import app.android.damien.reef.database_model.CustomWidgetSingleValueType2Model
import app.android.damien.reef.database_model.CustomWidgetTwoRectangleModel
import app.android.damien.reef.database_model.FocustronicOneElementWidgetModel
import app.android.damien.reef.database_model.FocustronicGridWidgetModel
import app.android.damien.reef.database_model.FocustronicSingleValueType1WidgetModel
import app.android.damien.reef.database_model.FocustronicSingleValueType2WidgetModel
import app.android.damien.reef.database_model.FocustronicTwoRectangleWidgetModel

@Dao
interface CustomWidgetsDao {

    /**
     * Insert, update and delete operations for the custom widgets
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: CustomWidgetModel)

    @Update
    fun update(data: CustomWidgetModel)

    @Delete
    fun delete(data: CustomWidgetModel)

    @Query("SELECT * from custom_widget_table order by id asc")
    fun read(): LiveData<List<CustomWidgetModel>>

    /**
     * Apex Flask Background Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel)

    @Query("SELECT * from apex_flask_background_widget_table order by id asc")
    fun readApexFlaskBackgroundWidget(): LiveData<List<ApexFlaskBackgroundWidgetModel>>

    @Query("SELECT * from apex_flask_background_widget_table order by id asc")
    fun readApexFlaskBackgroundWidgetBackground(): List<ApexFlaskBackgroundWidgetModel>

    @Delete
    fun deleteApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel)

    @Update
    fun updateApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel)

    /**
     * apex graph widgets
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexGraphWidget(data: ApexGraphWidgetModel)

    @Query("SELECT * from apex_graph_widget_table order by id asc")
    fun readApexGraphWidget(): LiveData<List<ApexGraphWidgetModel>>

    @Query("SELECT * from apex_graph_widget_table order by id asc")
    fun readApexGraphBackground(): List<ApexGraphWidgetModel>

    @Delete
    fun deleteApexGraphWidget(data: ApexGraphWidgetModel)

    @Update
    fun updateApexGraphWidget(data: ApexGraphWidgetModel)

    /**
     * Apex Power Values Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexPowerValuesWidget(data: ApexPowerValuesWidgetModel)

    @Query("SELECT * from apex_power_values_widget_table order by id asc")
    fun readApexPowerValuesWidget(): LiveData<List<ApexPowerValuesWidgetModel>>

    @Query("SELECT * from apex_power_values_widget_table order by id asc")
    fun readApexPowerValuesWidgetBackground(): List<ApexPowerValuesWidgetModel>

    @Delete
    fun deleteApexPowerValuesWidget(data: ApexPowerValuesWidgetModel)

    @Update
    fun updateApexPowerValuesWidget(data: ApexPowerValuesWidgetModel)


    /**
     * Apex Circle Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexCircleWidget(data: ApexCircleWidgetModel)

    @Query("SELECT * from apex_circle_widget_table order by id asc")
    fun readApexCircleWidget(): LiveData<List<ApexCircleWidgetModel>>

    @Query("SELECT * from apex_circle_widget_table order by id asc")
    fun readApexCircleWidgetBackground(): List<ApexCircleWidgetModel>

    @Delete
    fun deleteApexCircleWidget(data: ApexCircleWidgetModel)

    @Update
    fun updateApexCircleWidget(data: ApexCircleWidgetModel)

    /**
     * Apex Two Rectangle Widget
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexTwoRectangleWidget(data: ApexTwoRectangleWidgets)

    @Query("SELECT * from apex_two_rectangle_widget_table order by id asc")
    fun readApexTwoRectangleWidget(): LiveData<List<ApexTwoRectangleWidgets>>

    @Query("SELECT * from apex_two_rectangle_widget_table order by id asc")
    fun readApexTwoRectangleWidgetBackground(): List<ApexTwoRectangleWidgets>

    @Update
    fun updateApexTwoRectangleWidget(data: ApexTwoRectangleWidgets)

    @Delete
    fun deleteApexTwoRectangleWidget(data: ApexTwoRectangleWidgets)

    /**
     * Apex Single Value Type One Widget
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel)

    @Query("SELECT * from apex_single_value_type_1_widget_table order by id asc")
    fun readApexSingleValueTypeOneWidget(): LiveData<List<ApexSingleValueTypeOneModel>>

    @Query("SELECT * from apex_single_value_type_1_widget_table order by id asc")
    fun readApexSingleValueTypeOneWidgetBackground(): List<ApexSingleValueTypeOneModel>

    @Update
    fun updateApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel)

    @Delete
    fun deleteApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel)

    /**
     * Apex Single Value Type Two Widget
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel)

    @Query("SELECT * from apex_single_value_type_2_widget_table order by id asc")
    fun readApexSingleValueTypeTwoWidget(): LiveData<List<ApexSingleValueTypeTwoModel>>

    @Query("SELECT * from apex_single_value_type_2_widget_table order by id asc")
    fun readApexSingleValueTypeTwoWidgetBackground(): List<ApexSingleValueTypeTwoModel>

    @Update
    fun updateApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel)

    @Delete
    fun deleteApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel)

    /**
     * Apex Water Quality Widget
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertApexWaterQualityWidget(data: ApexWaterQualityWidget)

    @Query("SELECT * from apex_water_quality_widget_table order by id asc")
    fun readApexWaterQualityWidget(): LiveData<List<ApexWaterQualityWidget>>

    @Query("SELECT * from apex_water_quality_widget_table order by id asc")
    fun readApexWaterQualityWidgetBackground(): List<ApexWaterQualityWidget>

    @Update
    fun updateApexWaterQualityWidget(data: ApexWaterQualityWidget)

    @Delete
    fun deleteApexWaterQualityWidget(data: ApexWaterQualityWidget)

    /**
     * Focustronic Double Rectangle Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFocustronicDoubleRectangleWidget(data: FocustronicTwoRectangleWidgetModel)

    @Query("SELECT * from focustronic_two_rectangle_widgets_table order by id asc")
    fun readFocustronicDoubleRectangleWidget(): LiveData<List<FocustronicTwoRectangleWidgetModel>>

    @Query("SELECT * from focustronic_two_rectangle_widgets_table order by id asc")
    fun readFocustronicDoubleRectangleWidgetBackground(): List<FocustronicTwoRectangleWidgetModel>

    @Update
    fun updateFocustronicDoubleRectangleWidget(data: FocustronicTwoRectangleWidgetModel)

    @Delete
    fun deleteFocustronicDoubleRectangleWidget(data: FocustronicTwoRectangleWidgetModel)

    /**
     * Focustronic Single Value Type One Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFocustronicSingleValueTypeOneWidget(data: FocustronicSingleValueType1WidgetModel)

    @Query("SELECT * from focustronic_single_value_type_1_widget_table order by id asc")
    fun readFocustronicSingleValueTypeOneWidget(): LiveData<List<FocustronicSingleValueType1WidgetModel>>

    @Query("SELECT * from focustronic_single_value_type_1_widget_table order by id asc")
    fun readFocustronicSingleValueTypeOneWidgetBackground(): List<FocustronicSingleValueType1WidgetModel>

    @Update
    fun updateFocustronicSingleValueTypeOneWidget(data: FocustronicSingleValueType1WidgetModel)

    @Delete
    fun deleteFocustronicSingleValueTypeOneWidget(data: FocustronicSingleValueType1WidgetModel)

    /**
     * Focustronic Single Value Type Two Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFocustronicSingleValueTypeTwoWidget(data: FocustronicSingleValueType2WidgetModel)

    @Query("SELECT * from focustronic_single_value_type_2_widget_table order by id asc")
    fun readFocustronicSingleValueTypeTwoWidget(): LiveData<List<FocustronicSingleValueType2WidgetModel>>

    @Query("SELECT * from focustronic_single_value_type_2_widget_table order by id asc")
    fun readFocustronicSingleValueTypeTwoWidgetBackground(): List<FocustronicSingleValueType2WidgetModel>

    @Update
    fun updateFocustronicSingleValueTypeTwoWidget(data: FocustronicSingleValueType2WidgetModel)

    @Delete
    fun deleteFocustronicSingleValueTypeTwoWidget(data: FocustronicSingleValueType2WidgetModel)

    /**
     * Focustronic Grid Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFocustronicGridWidget(data: FocustronicGridWidgetModel)

    @Query("SELECT * from focustronic_grid_widget_table order by id asc")
    fun readFocustronicGridWidget(): LiveData<List<FocustronicGridWidgetModel>>

    @Query("SELECT * from focustronic_grid_widget_table order by id asc")
    fun readFocustronicGridWidgetBackground(): List<FocustronicGridWidgetModel>

    @Update
    fun updateFocustronicGridWidget(data: FocustronicGridWidgetModel)

    @Delete
    fun deleteFocustronicGridWidget(data: FocustronicGridWidgetModel)

    /**
     * Focustronic One Element Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFocustronicOneElementWidget(data: FocustronicOneElementWidgetModel)

    @Query("SELECT * from focustronic_one_element_widget_table order by id asc")
    fun readFocustronicOneElementWidget(): LiveData<List<FocustronicOneElementWidgetModel>>

    @Query("SELECT * from focustronic_one_element_widget_table order by id asc")
    fun readFocustronicOneElementWidgetBackground(): List<FocustronicOneElementWidgetModel>

    @Update
    fun updateFocustronicOneElementWidget(data: FocustronicOneElementWidgetModel)

    @Delete
    fun deleteFocustronicOneElementWidget(data: FocustronicOneElementWidgetModel)

    /**
     * Custom Single Value Type One Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCustomSingleValueTypeOneWidget(data: CustomWidgetSingleValueType1Model)

    @Query("SELECT * from custom_widget_single_value_type_1_table order by id asc")
    fun readCustomSingleValueTypeOneWidget(): LiveData<List<CustomWidgetSingleValueType1Model>>

    @Query("SELECT * from custom_widget_single_value_type_1_table order by id asc")
    fun readCustomSingleValueTypeOneWidgetBackground(): List<CustomWidgetSingleValueType1Model>

    @Update
    fun updateCustomSingleValueTypeOneWidget(data: CustomWidgetSingleValueType1Model)

    @Delete
    fun deleteCustomSingleValueTypeOneWidget(data: CustomWidgetSingleValueType1Model)

    /**
     * Custom Single Value Type Two Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCustomSingleValueTypeTwoWidget(data: CustomWidgetSingleValueType2Model)

    @Query("SELECT * from custom_widget_single_value_type_2_table order by id asc")
    fun readCustomSingleValueTypeTwoWidget(): LiveData<List<CustomWidgetSingleValueType2Model>>

    @Query("SELECT * from custom_widget_single_value_type_2_table order by id asc")
    fun readCustomSingleValueTypeTwoWidgetBackground(): List<CustomWidgetSingleValueType2Model>

    @Update
    fun updateCustomSingleValueTypeTwoWidget(data: CustomWidgetSingleValueType2Model)

    @Delete
    fun deleteCustomSingleValueTypeTwoWidget(data: CustomWidgetSingleValueType2Model)

    /**
     * Custom Two Rectangle Widget
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCustomTwoRectangleWidget(data: CustomWidgetTwoRectangleModel)

    @Query("SELECT * from custom_widget_two_rectangle_table order by id asc")
    fun readCustomTwoRectangleWidget(): LiveData<List<CustomWidgetTwoRectangleModel>>

    @Query("SELECT * from custom_widget_two_rectangle_table order by id asc")
    fun readCustomTwoRectangleWidgetBackground(): List<CustomWidgetTwoRectangleModel>

    @Update
    fun updateCustomTwoRectangleWidget(data: CustomWidgetTwoRectangleModel)

    @Delete
    fun deleteCustomTwoRectangleWidget(data: CustomWidgetTwoRectangleModel)
}