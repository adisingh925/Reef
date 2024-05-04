package app.android.damien.reef.model.aquariumdevices

data class Settings(
    val aquarium_volume: Int,
    val baseline_calibration: Int,
    val correction_method: Int,
    val current_test_count: Int,
    val dose_buffer_alert: Int,
    val is_action_mode_on: Int,
    val is_dosetronic_mode_on: Int,
    val is_fast_mode_on: Int,
    val is_washout_mode_on: Int,
    val kh_alert: Int,
    val lower_kh: Int,
    val measure_interval: Int,
    val next_test_time: Int,
    val notifications: Notifications,
    val pump_d_action: String,
    val reagent_alert: Int,
    val retest_duration: Int,
    val settings_update_time: Int,
    val test_count_limit: Int,
    val upper_kh: Int
)