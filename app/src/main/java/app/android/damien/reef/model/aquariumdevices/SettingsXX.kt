package app.android.damien.reef.model.aquariumdevices

data class SettingsXX(
    val current_hose_count: Int,
    val current_needle_count: Int,
    val hose_count_limit: Int,
    val needle_count_limit: Int,
    val notifications: Notifications
)