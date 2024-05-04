package app.android.damien.reef.model.apex

data class Sdstat(
    val readErr: Int,
    val reads: Int,
    val writeErr: Int,
    val writes: Int
)