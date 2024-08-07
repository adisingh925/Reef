package app.android.damien.reef.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import app.android.damien.reef.database.Database
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
    val apexGraphWidgets : LiveData<List<ApexGraphWidgetModel>>

    val focustronicOneElementWidgets: LiveData<List<FocustronicOneElementWidgetModel>>
    val focustronicGridWidgets: LiveData<List<FocustronicGridWidgetModel>>
    val focustronicSingleValueTypeOneWidgets: LiveData<List<FocustronicSingleValueType1WidgetModel>>
    val focustronicSingleValueTypeTwoWidgets: LiveData<List<FocustronicSingleValueType2WidgetModel>>
    val focustronicTwoRectangleWidgets: LiveData<List<FocustronicTwoRectangleWidgetModel>>

    val customWidgetSingleValueType1Data : LiveData<List<CustomWidgetSingleValueType1Model>>
    val customWidgetSingleValueType2Data : LiveData<List<CustomWidgetSingleValueType2Model>>
    val customWidgetTwoRectangleData : LiveData<List<CustomWidgetTwoRectangleModel>>


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
        apexGraphWidgets = repository.readApexGraphWidgetData

        focustronicOneElementWidgets = repository.focustronicOneElementData
        focustronicGridWidgets = repository.focustronicGridData
        focustronicSingleValueTypeOneWidgets = repository.focustronicSingleValueType1Data
        focustronicSingleValueTypeTwoWidgets = repository.focustronicSingleValueType2Data
        focustronicTwoRectangleWidgets = repository.focustronicTwoRectangleData

        customWidgetSingleValueType1Data = repository.customWidgetSingleValueType1Data
        customWidgetSingleValueType2Data = repository.customWidgetSingleValueType2Data
        customWidgetTwoRectangleData = repository.customWidgetTwoRectangleData
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

    /**
     * Apex Flask Background Widget
     */
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

    /**
     * Apex Flask Background Widget
     */
    fun insertApexGraphWidget(data: ApexGraphWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexGraphWidget(data)
        }
    }

    fun deleteApexGraphWidget(data: ApexGraphWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteApexGraphWidget(data)
        }
    }

    fun updateApexGraphWidget(data: ApexGraphWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateApexGraphWidget(data)
        }
    }

    /**
     * Apex Power Values Widget
     */

    fun insertApexPowerValuesWidget(data: ApexPowerValuesWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexPowerValuesWidget(data)
        }
    }

    fun deleteApexPowerValuesWidget(data: ApexPowerValuesWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteApexPowerValuesWidget(data)
        }
    }

    fun updateApexPowerValuesWidget(data: ApexPowerValuesWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateApexPowerValuesWidget(data)
        }
    }

    /**
     * Apex Circle Widget
     */
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

    /**
     * Apex Two Rectangle Widget
     */
    fun insertApexTwoRectangleWidget(data: ApexTwoRectangleWidgets) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexTwoRectangleWidget(data)
        }
    }

    fun deleteApexTwoRectangleWidget(data: ApexTwoRectangleWidgets) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteApexTwoRectangleWidget(data)
        }
    }

    fun updateApexTwoRectangleWidget(data: ApexTwoRectangleWidgets) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateApexTwoRectangleWidget(data)
        }
    }


    /**
     * Apex Single Value Type One Widget
     */
    fun insertApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexSingleValueTypeOneWidget(data)
        }
    }

    fun deleteApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteApexSingleValueTypeOneWidget(data)
        }
    }

    fun updateApexSingleValueTypeOneWidget(data: ApexSingleValueTypeOneModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateApexSingleValueTypeOneWidget(data)
        }
    }

    /**
     * Apex Single Value Type Two Widget
     */

    fun insertApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexSingleValueTypeTwoWidget(data)
        }
    }

    fun deleteApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteApexSingleValueTypeTwoWidget(data)
        }
    }

    fun updateApexSingleValueTypeTwoWidget(data: ApexSingleValueTypeTwoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateApexSingleValueTypeTwoWidget(data)
        }
    }

    /**
     * Apex Water Quality Widget

     */
    fun insertApexWaterQualityWidget(data: ApexWaterQualityWidget) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertApexWaterQualityWidget(data)
        }
    }

    fun deleteApexWaterQualityWidget(data: ApexWaterQualityWidget) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteApexWaterQualityWidget(data)
        }
    }

    fun updateApexWaterQualityWidget(data: ApexWaterQualityWidget) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateApexWaterQualityWidget(data)
        }
    }

    /**
     * Focustronic Double Rectangle Widget
     */

    fun insertFocustronicDoubleRectangleWidget(data: FocustronicTwoRectangleWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFocustronicTwoRectangleWidget(data)
        }
    }

    fun deleteFocustronicDoubleRectangleWidget(data: FocustronicTwoRectangleWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFocustronicTwoRectangleWidget(data)
        }
    }

    fun updateFocustronicDoubleRectangleWidget(data: FocustronicTwoRectangleWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFocustronicTwoRectangleWidget(data)
        }
    }

    /**
     * Focustronic Single Value Type One Widget
     */

    fun insertFocustronicSingleValueTypeOneWidget(data: FocustronicSingleValueType1WidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFocustronicSingleValueTypeOneWidget(data)
        }
    }

    fun deleteFocustronicSingleValueTypeOneWidget(data: FocustronicSingleValueType1WidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFocustronicSingleValueTypeOneWidget(data)
        }
    }

    fun updateFocustronicSingleValueTypeOneWidget(data: FocustronicSingleValueType1WidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFocustronicSingleValueTypeOneWidget(data)
        }
    }

    /**
     * Focustronic Single Value Type Two Widget
     */

    fun insertFocustronicSingleValueTypeTwoWidget(data: FocustronicSingleValueType2WidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFocustronicSingleValueTypeTwoWidget(data)
        }
    }

    fun deleteFocustronicSingleValueTypeTwoWidget(data: FocustronicSingleValueType2WidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFocustronicSingleValueTypeTwoWidget(data)
        }
    }

    fun updateFocustronicSingleValueTypeTwoWidget(data: FocustronicSingleValueType2WidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFocustronicSingleValueTypeTwoWidget(data)
        }
    }

    /**
     * Focustronic Grid Widget
     */

    fun insertFocustronicGridWidget(data: FocustronicGridWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFocustronicGridWidget(data)
        }
    }

    fun deleteFocustronicGridWidget(data: FocustronicGridWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFocustronicGridWidget(data)
        }
    }

    fun updateFocustronicGridWidget(data: FocustronicGridWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFocustronicGridWidget(data)
        }
    }

    /**
     * Focustronic One Element Widget
     */

    fun insertFocustronicOneElementWidget(data: FocustronicOneElementWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFocustronicOneElementWidget(data)
        }
    }

    fun deleteFocustronicOneElementWidget(data: FocustronicOneElementWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFocustronicOneElementWidget(data)
        }
    }

    fun updateFocustronicOneElementWidget(data: FocustronicOneElementWidgetModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFocustronicOneElementWidget(data)
        }
    }

    /**
     * Custom Widget Single Value Type 1
     */

    fun insertCustomWidgetSingleValueType1(data: CustomWidgetSingleValueType1Model) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCustomSingleValueTypeOneWidget(data)
        }
    }

    fun deleteCustomWidgetSingleValueType1(data: CustomWidgetSingleValueType1Model) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCustomSingleValueTypeOneWidget(data)
        }
    }

    fun updateCustomWidgetSingleValueType1(data: CustomWidgetSingleValueType1Model) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCustomSingleValueTypeOneWidget(data)
        }
    }

    /**
     * Custom Widget Single Value Type 2
     */

    fun insertCustomWidgetSingleValueType2(data: CustomWidgetSingleValueType2Model) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCustomSingleValueTypeTwoWidget(data)
        }
    }

    fun deleteCustomWidgetSingleValueType2(data: CustomWidgetSingleValueType2Model) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCustomSingleValueTypeTwoWidget(data)
        }
    }

    fun updateCustomWidgetSingleValueType2(data: CustomWidgetSingleValueType2Model) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCustomSingleValueTypeTwoWidget(data)
        }
    }

    /**
     * Custom Widget Two Rectangle
     */

    fun insertCustomWidgetTwoRectangle(data: CustomWidgetTwoRectangleModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCustomTwoRectangleWidget(data)
        }
    }

    fun deleteCustomWidgetTwoRectangle(data: CustomWidgetTwoRectangleModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCustomTwoRectangleWidget(data)
        }
    }

    fun updateCustomWidgetTwoRectangle(data: CustomWidgetTwoRectangleModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCustomTwoRectangleWidget(data)
        }
    }
}