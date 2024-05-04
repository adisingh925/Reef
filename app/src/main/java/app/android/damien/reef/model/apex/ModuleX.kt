package app.android.damien.reef.model.apex

data class ModuleX(
    val abaddr: Int,
    val boot: Boolean,
    val extra: ExtraXXXX,
    val hwrev: Int,
    val hwtype: String,
    val inact: Int,
    val pcount: Int,
    val perror: Int,
    val pgood: Int,
    val present: Boolean,
    val reatt: Int,
    val swrev: Int,
    val swstat: String
)