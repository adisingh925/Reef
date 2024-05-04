package app.android.damien.reef.model.aquariumdevices

data class Dosetronic(
    val aquarium_tank_id: Int,
    val batch_no: Int,
    val eligible_actions: List<EligibleActionX>,
    val firmware_version: String,
    val friendly_name: String,
    val id: Int,
    val is_active: Int,
    val is_adv_active: Int,
    val is_auto_update: Int,
    val is_awc_mode_on: Int,
    val is_dc: Int,
    val last_alkatronic_adv_time: Int,
    val last_online: Int,
    val local_hour: Int,
    val local_ip_address: String,
    val local_minute: Int,
    val mac_address: Any,
    val mcu_version: Any,
    val next_firmware_version: String,
    val serial_number: String,
    val settings: SettingsX,
    val user_id: Int
)