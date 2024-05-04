package app.android.damien.reef.model.apex

data class Config(
    val clock: Clock,
    val display: List<Display>,
    val ebg: Ebg,
    val flo: Flo,
    val heartbeat: Heartbeat,
    val inputs: List<Input>,
    val misc: Misc,
    val modules: List<Module>,
    val network: Network,
    val outputs: List<Output>,
    val profiles: List<Profile>
)