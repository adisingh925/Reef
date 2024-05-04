package app.android.damien.reef.model.apex

data class Status(
    val alarm: AlarmX,
    val cal: Cal,
    val clock: ClockX,
    val command: Command,
    val feed: Feed,
    val inputs: List<InputX>,
    val modules: List<ModuleX>,
    val network: NetworkX,
    val outputs: List<OutputX>,
    val power: PowerX
)