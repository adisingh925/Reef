package app.android.damien.reef.model.apex

data class Network(
    val dhcp: Boolean,
    val dns: List<String>,
    val emailAuth: Boolean,
    val emailEnable: Boolean,
    val emailFrom: String,
    val emailTo: String,
    val emailUser: String,
    val gateway: String,
    val hostname: String,
    val httpPort: Int,
    val ipaddr: String,
    val netmask: String,
    val reEmail: Int,
    val smtpPort: Int,
    val smtpServer: String,
    val ssid: String,
    val updateFirmware: Boolean,
    val wifiAP: Boolean,
    val wifiAPLock: Boolean,
    val wifiEnable: Boolean
)