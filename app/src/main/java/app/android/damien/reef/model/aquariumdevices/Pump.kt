package app.android.damien.reef.model.aquariumdevices

data class Pump(
    val current_hour_flow_rate: Int,
    val daily_volume: Int,
    val has_schedule: Int,
    val id: Int,
    val is_alkatronic_mode_on: Int,
    val is_continuous_mode_on: Int,
    val is_dose_alert_on: Int,
    val is_refill_alert_on: Int,
    val last_24_hour_total_dose_volume: Int,
    val last_replace_hose_time: Int,
    val max_volume: Int,
    val name: String,
    val remaining_volume: Int,
    val target_enable_time: Int
)