package app.android.damien.reef.model.mastertronic

data class Schedule(
    val hour: Int,
    val minute: Int,
    val test_profile_id: Int,
    val weekday: Int
)