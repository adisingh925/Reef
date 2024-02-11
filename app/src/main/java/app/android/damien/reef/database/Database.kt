package app.android.damien.reef.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.android.damien.reef.dao.CustomWidgetsDao
import app.android.damien.reef.database_model.CustomWidgetModel

@Database(entities = [CustomWidgetModel::class], version = 1, exportSchema = false)
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