package app.android.damien.reef.model.mastertronic

data class DeviceSettings(
    val current_waste_volume: Int,
    val ro_alert_volume: Int,
    val ro_remaining_volume: Int,
    val waste_alert_volume: Int,
    val waste_container_volume: Int
)