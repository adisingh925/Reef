package app.android.damien.reef.model

data class MyWidgetsParentModel(
    val widgetType : String,
    val widgets : List<MyWidgetsChildModel>
)
