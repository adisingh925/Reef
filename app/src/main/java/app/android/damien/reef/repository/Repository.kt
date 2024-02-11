package app.android.damien.reef.repository

import app.android.damien.reef.dao.CustomWidgetsDao
import app.android.damien.reef.database_model.CustomWidgetModel

class Repository(private val dao: CustomWidgetsDao) {

    val readData = dao.read()

    fun insert(data: CustomWidgetModel) {
        dao.insert(data)
    }

    suspend fun update(data: CustomWidgetModel) {
        dao.update(data)
    }

    suspend fun delete(data: CustomWidgetModel) {
        dao.delete(data)
    }
}