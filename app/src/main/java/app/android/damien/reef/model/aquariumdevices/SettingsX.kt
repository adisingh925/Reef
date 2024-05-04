package app.android.damien.reef.model.aquariumdevices

data class SettingsX(
    val alkatronic_serial_number: String,
    val awc_mode_hourly_flow_rate: Int,
    val continuous_mode_hose_limit: Int,
    val continuous_mode_maximum_hourly_flow_rate: Int,
    val continuous_mode_maximum_pump_count: Int,
    val pumps: List<Pump>
)