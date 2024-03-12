package app.android.damien.reef.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.android.damien.reef.dao.CustomWidgetsDao
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
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

@Database(
    entities = [CustomWidgetModel::class, ApexFlaskBackgroundWidgetModel::class, ApexPowerValuesWidgetModel::class, ApexCircleWidgetModel::class, ApexTwoRectangleWidgets::class, ApexSingleValueTypeOneModel::class, ApexSingleValueTypeTwoModel::class, ApexWaterQualityWidget::class, FocustronicTwoRectangleWidgetModel::class, FocustronicGridWidgetModel::class, FocustronicOneElementWidgetModel::class, FocustronicSingleValueType1WidgetModel::class, FocustronicSingleValueType2WidgetModel::class, CustomWidgetTwoRectangleModel::class, CustomWidgetSingleValueType2Model::class, CustomWidgetSingleValueType1Model::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun customWidgetsDao(): CustomWidgetsDao

    companion object {
        @Volatile
        private var INSTANCE: app.android.damien.reef.database.Database? = null

        fun getDatabase(context: Context): app.android.damien.reef.database.Database {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    app.android.damien.reef.database.Database::class.java,
                    "ReelWidgetsDatabase"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}