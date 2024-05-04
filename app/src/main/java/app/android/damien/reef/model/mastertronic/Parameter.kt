package app.android.damien.reef.model.mastertronic

data class Parameter(
    val baseline_device_value: Int,
    val baseline_reference_value: Int,
    val baselined_value: Int,
    val high_reference: Int,
    val indicator: Int,
    val is_action_mode_on: Int,
    val is_automatic_mode_on: Int,
    val is_washout_mode_on: Int,
    val low_reference: Int,
    val lower_bound: Int,
    val multiply_factor: Int,
    val parameter: String,
    val record_time: Int,
    val upper_bound: Int,
    val value: Int
)