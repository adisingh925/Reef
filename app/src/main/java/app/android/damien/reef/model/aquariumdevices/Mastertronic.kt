package app.android.damien.reef.model.aquariumdevices

data class Mastertronic(
    val aquarium_tank_id: Int,
    val batch_no: Int,
    val eligible_actions: List<EligibleActionXX>,
    val firmware_version: String,
    val friendly_name: String,
    val id: Int,
    val is_active: Int,
    val last_online: Int,
    val last_reset_hose_counter_time: Int,
    val last_reset_needle_counter_time: Int,
    val lifetime_test_count: Int,
    val local_hour: Int,
    val local_ip_address: String,
    val local_minute: Int,
    val mac_address: String,
    val mcu_status: String,
    val mcu_version: String,
    val next_firmware_version: String,
    val next_mcu_version: String,
    val parameters: List<Parameter>,
    val serial_number: String,
    val settings: SettingsXX,
    val user_id: Int,
    val version: Int
)