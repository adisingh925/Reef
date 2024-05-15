package app.android.damien.reef.repository

import app.android.damien.reef.dao.CustomWidgetsDao
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

class Repository(private val dao: CustomWidgetsDao) {

    val readData = dao.read()
    val readApexFlaskBackgroundWidgetData = dao.readApexFlaskBackgroundWidget()
    val readApexPowerValuesWidgetData = dao.readApexPowerValuesWidget()
    val readApexCircleWidgetData = dao.readApexCircleWidget()
    val readApexTwoRectangleWidgetData = dao.readApexTwoRectangleWidget()
    val readApexSingleValueTypeOneWidgetData = dao.readApexSingleValueTypeOneWidget()
    val readApexSingleValueTypeTwoWidgetData = dao.readApexSingleValueTypeTwoWidget()
    val readApexWaterQualityWidgetData = dao.readApexWaterQualityWidget()
    val readApexGraphWidgetData = dao.readApexGraphWidget()

    val focustronicOneElementData = dao.readFocustronicOneElementWidget()
    val focustronicGridData = dao.readFocustronicGridWidget()
    val focustronicSingleValueType1Data = dao.readFocustronicSingleValueTypeOneWidget()
    val focustronicSingleValueType2Data = dao.readFocustronicSingleValueTypeTwoWidget()
    val focustronicTwoRectangleData = dao.readFocustronicDoubleRectangleWidget()

    val customWidgetSingleValueType1Data = dao.readCustomSingleValueTypeOneWidget()
    val customWidgetSingleValueType2Data = dao.readCustomSingleValueTypeTwoWidget()
    val customWidgetTwoRectangleData = dao.readCustomTwoRectangleWidget()


    fun insert(data: CustomWidgetModel) {
        dao.insert(data)
    }

    suspend fun update(data: CustomWidgetModel) {
        dao.update(data)
    }

    suspend fun delete(data: CustomWidgetModel) {
        dao.delete(data)
    }

    /**
     * Apex Flask Background Widget
     */
    suspend fun insertApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel) {
        dao.insertApexFlaskBackgroundWidget(data)
    }

    suspend fun deleteApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel) {
        dao.deleteApexFlaskBackgroundWidget(data)
    }

    suspend fun updateApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel) {
        dao.updateApexFlaskBackgroundWidget(data)
    }

    /**
     * Apex Graph Widget
     */
    suspend fun insertApexGraphWidget(data: ApexGraphWidgetModel) {
        dao.insertApexGraphWidget(data)
    }

    suspend fun deleteApexGraphWidget(data: ApexGraphWidgetModel) {
        dao.deleteApexGraphWidget(data)
    }

    suspend fun updateApexGraphWidget(data: ApexGraphWidgetModel) {
        dao.updateApexGraphWidget(data)
    }

    /**
     * Apex Power Values Widget
     */
    suspend fun insertApexPowerValuesWidget(data: ApexPowerValuesWidgetModel) {
        dao.insertApexPowerValuesWidget(data)
    }

    suspend fun deleteApexPowerValuesWidget(data: ApexPowerValuesWidgetModel) {
        dao.deleteApexPowerValuesWidget(data)
    }

    suspend fun updateApexPowerValuesWidget(data: ApexPowerValuesWidgetModel) {
        dao.updateApexPowerValuesWidget(data)
    }


    /**
     * Apex Circle Widget
     */
    suspend fun insertApexCircleWidget(data: ApexCircleWidgetModel) {
        dao.insertApexCircleWidget(data)
    }

    suspend fun deleteApexCircleWidget(data: ApexCircleWidgetModel) {
        dao.deleteApexCircleWidget(data)
    }

    suspend fun updateApexCircleWidget(data: ApexCircleWidgetModel) {
        dao.updateApexCircleWidget(data)
    }

    /**
     * Apex Two Rectangle Widget
     */
    suspend fun insertApexTwoRectangleWidget(data: ApexTwoRectangleWidgets) {
        dao.insertApexTwoRectangleWidget(data)
    }

    suspend fun deleteApexTwoRectangleWidget(data: ApexTwoRectangleWidgets) {
        dao.deleteApexTwoRectangleWidget(data)
    }

    suspend fun updateApexTwoRectangleWidget(data: ApexTwoRectangleWidgets) {
        dao.updateApexTwoRectangleWidget(data)
    }

    /**
     * Apex Single Value Type One Widget
     */

    suspend fun insertApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel) {
        dao.insertApexSingleValueTypeOneWidget(data)
    }

    suspend fun deleteApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel) {
        dao.deleteApexSingleValueTypeOneWidget(data)
    }

    suspend fun updateApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel) {
        dao.updateApexSingleValueTypeOneWidget(data)
    }

    /**
     * Apex Single Value Type Two Widget
     */

    suspend fun insertApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel) {
        dao.insertApexSingleValueTypeTwoWidget(data)
    }

    suspend fun deleteApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel) {
        dao.deleteApexSingleValueTypeTwoWidget(data)
    }

    suspend fun updateApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel) {
        dao.updateApexSingleValueTypeTwoWidget(data)
    }

    /**
     * Apex Water Quality Widget

     */
    suspend fun insertApexWaterQualityWidget(data: ApexWaterQualityWidget) {
        dao.insertApexWaterQualityWidget(data)
    }

    suspend fun deleteApexWaterQualityWidget(data: ApexWaterQualityWidget) {
        dao.deleteApexWaterQualityWidget(data)
    }

    suspend fun updateApexWaterQualityWidget(data: ApexWaterQualityWidget) {
        dao.updateApexWaterQualityWidget(data)
    }

    /**
     * Focustronic One Element Widget
     */

    suspend fun insertFocustronicOneElementWidget(data: FocustronicOneElementWidgetModel) {
        dao.insertFocustronicOneElementWidget(data)
    }

    suspend fun deleteFocustronicOneElementWidget(data: FocustronicOneElementWidgetModel) {
        dao.deleteFocustronicOneElementWidget(data)
    }

    suspend fun updateFocustronicOneElementWidget(data: FocustronicOneElementWidgetModel) {
        dao.updateFocustronicOneElementWidget(data)
    }

    /**
     * Focustronic Grid Widget
     */

    suspend fun insertFocustronicGridWidget(data: FocustronicGridWidgetModel) {
        dao.insertFocustronicGridWidget(data)
    }

    suspend fun deleteFocustronicGridWidget(data: FocustronicGridWidgetModel) {
        dao.deleteFocustronicGridWidget(data)
    }

    suspend fun updateFocustronicGridWidget(data: FocustronicGridWidgetModel) {
        dao.updateFocustronicGridWidget(data)
    }

    /**
     * Focustronic Single Value Type One Widget
     */

    suspend fun insertFocustronicSingleValueTypeOneWidget(data: FocustronicSingleValueType1WidgetModel) {
        dao.insertFocustronicSingleValueTypeOneWidget(data)
    }

    suspend fun deleteFocustronicSingleValueTypeOneWidget(data: FocustronicSingleValueType1WidgetModel) {
        dao.deleteFocustronicSingleValueTypeOneWidget(data)
    }

    suspend fun updateFocustronicSingleValueTypeOneWidget(data: FocustronicSingleValueType1WidgetModel) {
        dao.updateFocustronicSingleValueTypeOneWidget(data)
    }

    /**
     * Focustronic Single Value Type Two Widget
     */

    suspend fun insertFocustronicSingleValueTypeTwoWidget(data: FocustronicSingleValueType2WidgetModel) {
        dao.insertFocustronicSingleValueTypeTwoWidget(data)
    }

    suspend fun deleteFocustronicSingleValueTypeTwoWidget(data: FocustronicSingleValueType2WidgetModel) {
        dao.deleteFocustronicSingleValueTypeTwoWidget(data)
    }

    suspend fun updateFocustronicSingleValueTypeTwoWidget(data: FocustronicSingleValueType2WidgetModel) {
        dao.updateFocustronicSingleValueTypeTwoWidget(data)
    }

    /**
     * Focustronic Two Rectangle Widget
     */

    suspend fun insertFocustronicTwoRectangleWidget(data: FocustronicTwoRectangleWidgetModel) {
        dao.insertFocustronicDoubleRectangleWidget(data)
    }

    suspend fun deleteFocustronicTwoRectangleWidget(data: FocustronicTwoRectangleWidgetModel) {
        dao.deleteFocustronicDoubleRectangleWidget(data)
    }

    suspend fun updateFocustronicTwoRectangleWidget(data: FocustronicTwoRectangleWidgetModel) {
        dao.updateFocustronicDoubleRectangleWidget(data)
    }

    /**
     * Custom Single Value Type One Widget
     */

    suspend fun insertCustomSingleValueTypeOneWidget(data: CustomWidgetSingleValueType1Model) {
        dao.insertCustomSingleValueTypeOneWidget(data)
    }

    suspend fun deleteCustomSingleValueTypeOneWidget(data: CustomWidgetSingleValueType1Model) {
        dao.deleteCustomSingleValueTypeOneWidget(data)
    }

    suspend fun updateCustomSingleValueTypeOneWidget(data: CustomWidgetSingleValueType1Model) {
        dao.updateCustomSingleValueTypeOneWidget(data)
    }

    /**
     * Custom Single Value Type Two Widget
     */

    suspend fun insertCustomSingleValueTypeTwoWidget(data: CustomWidgetSingleValueType2Model) {
        dao.insertCustomSingleValueTypeTwoWidget(data)
    }

    suspend fun deleteCustomSingleValueTypeTwoWidget(data: CustomWidgetSingleValueType2Model) {
        dao.deleteCustomSingleValueTypeTwoWidget(data)
    }

    suspend fun updateCustomSingleValueTypeTwoWidget(data: CustomWidgetSingleValueType2Model) {
        dao.updateCustomSingleValueTypeTwoWidget(data)
    }

    /**
     * Custom Two Rectangle Widget
     */

    suspend fun insertCustomTwoRectangleWidget(data: CustomWidgetTwoRectangleModel) {
        dao.insertCustomTwoRectangleWidget(data)
    }

    suspend fun deleteCustomTwoRectangleWidget(data: CustomWidgetTwoRectangleModel) {
        dao.deleteCustomTwoRectangleWidget(data)
    }

    suspend fun updateCustomTwoRectangleWidget(data: CustomWidgetTwoRectangleModel) {
        dao.updateCustomTwoRectangleWidget(data)
    }
}