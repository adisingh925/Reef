package app.android.damien.reef.model.aquariumdevices

data class Parameter(
    val baseline_device_value: Int,
    val baseline_reference_value: Int,
    val high_reference: Int,
    val is_action_mode_on: Int,
    val is_automatic_mode_on: Int,
    val latest_record: Int,
    val low_reference: Int,
    val multiply_factor: Int,
    val parameter: String,
    val record_time: Int
)