package app.android.damien.reef.model.apex

data class Display(
    val lineEnables: List<Boolean>,
    val outputs: List<String>,
    val page: Int,
    val probes: List<String>
)