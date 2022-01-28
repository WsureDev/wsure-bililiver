package top.wsure.bililiver.bililiver.dtos.api.room

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Serializable
data class RoomInfo(
    @SerialName("area_id")
    val areaId: Int,
    @SerialName("area_name")
    val areaName: String,
    @SerialName("attention")
    val attention: Int? = null,
    @SerialName("background")
    val background: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("is_anchor")
    val isAnchor: Int? = null,
    @SerialName("is_portrait")
    val isPortrait: Boolean? = null,
    @SerialName("is_strict_room")
    val isStrictRoom: Boolean? = null,
    @SerialName("keyframe")
    val keyframe: String? = null,
    @SerialName("live_status")
    val liveStatus: Int,
    @SerialName("live_time")
    @Serializable(with =LocalDateTimeSerializer::class )
    val liveTime: LocalDateTime,
    @SerialName("old_area_id")
    val oldAreaId: Int? = null,
    @SerialName("online")
    val online: Int? = null,
    @SerialName("parent_area_id")
    val parentAreaId: Int? = null,
    @SerialName("parent_area_name")
    val parentAreaName: String? = null,
    @SerialName("room_id")
    val roomId: Int? = null,
    @SerialName("room_silent_level")
    val roomSilentLevel: Int? = null,
    @SerialName("room_silent_second")
    val roomSilentSecond: Int? = null,
    @SerialName("room_silent_type")
    val roomSilentType: String? = null,
    @SerialName("short_id")
    val shortId: Int? = null,
    @SerialName("tags")
    val tags: String,
    @SerialName("title")
    val title: String,
    @SerialName("uid")
    val uid: Int? = null,
    @SerialName("user_cover")
    val userCover: String
)

object LocalDateTimeSerializer:KSerializer<LocalDateTime>{
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    override fun deserialize(decoder: Decoder): LocalDateTime {
        return LocalDateTime.parse(decoder.decodeString(), formatter)
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("roomInfoLocalDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeString(value.format(formatter))
    }

}