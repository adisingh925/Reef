package app.android.damien.reef.model.aquariumdevices

data class Data(
    val alkalinity_device_type: String,
    val alkatronics: List<Alkatronic>,
    val dosetronics: List<Dosetronic>,
    val filtertronics: List<Any>,
    val id: Int,
    val mastertronics: List<Mastertronic>,
    val name: String,
    val powertronics: List<Any>,
    val solartronics: List<Any>,
    val tank_volume_unit: String,
    val temperature_unit: String
)