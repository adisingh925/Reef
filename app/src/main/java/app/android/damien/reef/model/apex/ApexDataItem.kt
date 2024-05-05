package app.android.damien.reef.model.apex

data class ApexDataItem(
    val __v: Int,
    val _id: String,
    val auth: Long,
    val config: Config,
    val extra: ExtraXXX,
    val hardware: String,
    val hostname: String,
    val layout: Layout,
    val limit: Limit,
    val link: Link,
    val media: List<Media>,
    val metadata: List<Any>,
    val reminder: Int,
    val serial: String,
    val software: String,
    val status: Status,
    val things: Things,
    val timezone: String,
    val type: String,
    val voice: Voice
)