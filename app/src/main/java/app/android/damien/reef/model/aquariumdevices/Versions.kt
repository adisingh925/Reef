package app.android.damien.reef.model.aquariumdevices

data class Versions(
    val is_allow_cloud_settings: Boolean,
    val is_allow_dosetronic_settings: Boolean,
    val is_allow_k_bnc_settings: Boolean,
    val is_allow_mtest_phtest_refill_settings: Boolean,
    val is_allow_plug_settings: Boolean,
    val is_allow_reset_ph_probe_calibration_settings: Boolean,
    val is_allow_sda_oda_retest_settings: Boolean,
    val is_allow_super_low_reference_settings: Boolean,
    val is_use_record_format_v2: Boolean
)