package app.android.damien.reef.model.apex

data class Clock(
    val auto: Boolean,
    val date: List<String>,
    val dst: Boolean,
    val timezone: List<String>
)