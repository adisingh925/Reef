package app.android.damien.reef.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.database_model.CustomWidgetModel
import app.android.damien.reef.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WidgetsViewModel(application: Application) : AndroidViewModel(application) {

    val widgets: LiveData<List<CustomWidgetModel>>
    val apexFlaskBackgroundWidgets: LiveData<List<ApexFlaskBackgroundWidgetModel>>
    val apexPowerValuesWidgets: LiveData<List<ApexPowerValuesWidgetModel>>
    val apexCircleWidgets: LiveData<List<ApexCircleWidgetModel>>
    val apexTwoRectangleWidgets: LiveData<List<ApexTwoRectangleWidgets>>
    val apexSingleValueTypeOneWidgets: LiveData<List<ApexSingleValueTypeOneModel>>
    val apexSingleValueTypeTwoWidgets: LiveData<List<ApexSingleValueTypeTwoModel>>
    val apexWaterQualityWidgets: LiveData<List<ApexWaterQualityWidget>>
    val repository: Repository

    init {
        val userDao = Database.getDatabase(application).customWidgetsDao()
        repository = Repository(userDao)
        widgets = repository.readData
        apexFlaskBackgroundWidgets = repository.readApexFlaskBackgroundWidgetData
        apexPowerValuesWidgets = repository.readApexPowerValuesWidgetData
        apexCircleWidgets = repository.readApexCircleWidgetData
        apexTwoRectangleWidgets = repository.readApexTwoRectangleWidgetData
        apexSingleValueTypeOneWidgets = repository.readApexSingleValueTypeOneWidgetData
        apexSingleValueTypeTwoWidgets = repository.readApexSingleValueTypeTwoWidgetData
        apexWaterQualityWidgets = repository.readApexWaterQualityWidgetData
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

    fun insertApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexFlaskBackgroundWidget(data)
        }
    }

    fun deleteApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteApexFlaskBackgroundWidget(data)
        }
    }

    fun updateApexFlaskBackgroundWidget(data: ApexFlaskBackgroundWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateApexFlaskBackgroundWidget(data)
        }
    }

    fun insertApexPowerValuesWidget(data: ApexPowerValuesWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexPowerValuesWidget(data)
        }
    }

    fun insertApexCircleWidget(data: ApexCircleWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexCircleWidget(data)
        }
    }

    fun deleteApexCircleWidget(data: ApexCircleWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteApexCircleWidget(data)
        }
    }

    fun updateApexCircleWidget(data: ApexCircleWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateApexCircleWidget(data)
        }
    }

    fun insertApexTwoRectangleWidget(data: ApexTwoRectangleWidgets) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexTwoRectangleWidget(data)
        }
    }

    fun insertApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexSingleValueTypeOneWidget(data)
        }
    }

    fun insertApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexSingleValueTypeTwoWidget(data)
        }
    }

    fun insertApexWaterQualityWidget(data: ApexWaterQualityWidget) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexWaterQualityWidget(data)
        }
    }
}