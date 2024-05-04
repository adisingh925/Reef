package app.android.damien.reef.model.aquariumdevices

data class Options(
    val aquarium_volume_upper_limit: Int,
    val has_k_value_settings: Int,
    val high_reference_range_from: Int,
    val high_reference_range_to: Int,
    val instruction_link: String,
    val is_require_passcode: Int,
    val low_reference_range_from: Int,
    val low_reference_range_to: Int,
    val target_version: String
)