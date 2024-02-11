package app.android.damien.reef.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.CustomWidgetModel
import app.android.damien.reef.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WidgetsViewModel(application: Application) : AndroidViewModel(application) {

    val widgets: LiveData<List<CustomWidgetModel>>
    val repository: Repository

    init {
        val userDao = Database.getDatabase(application).customWidgetsDao()
        repository = Repository(userDao)
        widgets = repository.readData
    }

    fun insert(data: CustomWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(data)
        }
    }

    fun update(data: CustomWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(data)
        }
    }

    fun delete(data: CustomWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(data)
        }
    }
}