package app.android.damien.reef.repository

import app.android.damien.reef.dao.CustomWidgetsDao
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.database_model.CustomWidgetModel

class Repository(private val dao: CustomWidgetsDao) {

    val readData = dao.read()
    val readApexFlaskBackgroundWidgetData = dao.readApexFlaskBackgroundWidget()
    val readApexPowerValuesWidgetData = dao.readApexPowerValuesWidget()
    val readApexCircleWidgetData = dao.readApexCircleWidget()
    val readApexTwoRectangleWidgetData = dao.readApexTwoRectangleWidget()
    val readApexSingleValueTypeOneWidgetData = dao.readApexSingleValueTypeOneWidget()
    val readApexSingleValueTypeTwoWidgetData = dao.readApexSingleValueTypeTwoWidget()
    val readApexWaterQualityWidgetData = dao.readApexWaterQualityWidget()

    fun insert(data: CustomWidgetModel) {
        dao.insert(data)
    }

    suspend fun update(data: CustomWidgetModel) {
        dao.update(data)
    }

    suspend fun delete(data: CustomWidgetModel) {
        dao.delete(data)
    }

    suspend fun insertApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel) {
        dao.insertApexFlaskBackgroundWidget(data)
    }

    suspend fun deleteApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel) {
        dao.deleteApexFlaskBackgroundWidget(data)
    }

    suspend fun updateApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel) {
        dao.updateApexFlaskBackgroundWidget(data)
    }

    suspend fun insertApexPowerValuesWidget(data: ApexPowerValuesWidgetModel) {
        dao.insertApexPowerValuesWidget(data)
    }

    suspend fun insertApexCircleWidget(data: ApexCircleWidgetModel) {
        dao.insertApexCircleWidget(data)
    }

    suspend fun deleteApexCircleWidget(data: ApexCircleWidgetModel) {
        dao.deleteApexCircleWidget(data)
    }

    suspend fun updateApexCircleWidget(data: ApexCircleWidgetModel) {
        dao.updateApexCircleWidget(data)
    }

    suspend fun insertApexTwoRectangleWidget(data: ApexTwoRectangleWidgets) {
        dao.insertApexTwoRectangleWidget(data)
    }

    suspend fun insertApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel) {
        dao.insertApexSingleValueTypeOneWidget(data)
    }

    suspend fun insertApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel) {
        dao.insertApexSingleValueTypeTwoWidget(data)
    }

    suspend fun insertApexWaterQualityWidget(data: ApexWaterQualityWidget) {
        dao.insertApexWaterQualityWidget(data)
    }
}