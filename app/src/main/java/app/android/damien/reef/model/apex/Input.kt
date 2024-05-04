package app.android.damien.reef.model.apex

data class Input(
    val alarm: Alarm,
    val did: String,
    val enable: Boolean,
    val extra: Extra,
    val name: String,
    val type: String
)