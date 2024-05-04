package app.android.damien.reef.model.mastertronic

data class MastertronicData(
    val action: String,
    val `data`: Data,
    val result: Boolean,
    val timestamp: Long,
    val type: String
)