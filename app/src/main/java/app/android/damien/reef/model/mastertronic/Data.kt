package app.android.damien.reef.model.mastertronic

data class Data(
    val actions: List<Any>,
    val device_settings: DeviceSettings,
    val parameters: List<Parameter>,
    val schedules: List<Schedule>,
    val vials: List<Vial>
)