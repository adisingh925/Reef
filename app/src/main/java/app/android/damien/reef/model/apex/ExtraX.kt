package app.android.damien.reef.model.apex

data class ExtraX(
    val auth: Int,
    val auto: List<Boolean>,
    val flowMode: String,
    val levelMode: String,
    val mode: List<String>,
    val serial: Int,
    val status: String,
    val swapAddr: Int,
    val type: String
)