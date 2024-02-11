package app.android.damien.reef.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.android.damien.reef.database_model.CustomWidgetModel

@Dao
interface CustomWidgetsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data : CustomWidgetModel)

    @Update
    fun update(data : CustomWidgetModel)

    @Delete
    fun delete(data: CustomWidgetModel)

    @Query("SELECT * from custom_widget_table order by id asc")
    fun read() : LiveData<List<CustomWidgetModel>>
}