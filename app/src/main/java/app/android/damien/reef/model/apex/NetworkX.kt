package app.android.damien.reef.model.apex

data class NetworkX(
    val dhcp: Boolean,
    val dns: List<String>,
    val gateway: String,
    val hostname: String,
    val httpPort: Int,
    val ipaddr: String,
    val latestFirmware: String,
    val link: Boolean,
    val netmask: String,
    val quality: Int,
    val ssid: String,
    val strength: Int,
    val updateFirmware: Boolean,
    val wifiAP: Boolean,
    val wifiAPLock: Boolean
)