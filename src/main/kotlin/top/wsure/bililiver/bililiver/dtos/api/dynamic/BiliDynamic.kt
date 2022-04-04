package top.wsure.bililiver.bililiver.dtos.api.dynamic

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import top.wsure.guild.common.utils.TimeUtils.toEpochSecond
import top.wsure.guild.common.utils.TimeUtils.toLocalDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Serializable
data class BiliDynamic(
    @SerialName("cards")
    val cards: List<DynamicCard>? = null,
    @SerialName("_gt_")
    val gt: Long? = null,
    @SerialName("has_more")
    val hasMore: Long? = null,
    @SerialName("next_offset")
    val nextOffset: Long? = null
)

@Serializable
data class DynamicCard(
    @SerialName("card")
    @Serializable( with = CardJsonStrSerializer::class)
    val card: CardJsonStr,
    @SerialName("desc")
    val desc: Desc,
    @SerialName("extend_json")
    val extendJson: String? = null,
    @SerialName("extension")
    val extension: Extension? = null,
    @SerialName("extra")
    val extra: Extra? = null
)

@Serializable
data class Desc(
    @SerialName("acl")
    val acl: Long? = null,
    @SerialName("comment")
    val comment: Long? = null,
    @SerialName("dynamic_id")
    val dynamicId: Long,
    @SerialName("dynamic_id_str")
    val dynamicIdStr: String,
    @SerialName("inner_id")
    val innerId: Long? = null,
    @SerialName("is_liked")
    val isLiked: Long? = null,
    @SerialName("like")
    val like: Long? = null,
    @SerialName("orig_dy_id")
    val origDyId: Long? = null,
    @SerialName("orig_dy_id_str")
    val origDyIdStr: String? = null,
    @SerialName("orig_type")
    val origType: Long? = null,
    @SerialName("pre_dy_id")
    val preDyId: Long? = null,
    @SerialName("pre_dy_id_str")
    val preDyIdStr: String? = null,
    @SerialName("r_type")
    val rType: Long? = null,
    @SerialName("repost")
    val repost: Long? = null,
    @SerialName("rid")
    val rid: Long? = null,
    @SerialName("rid_str")
    val ridStr: String? = null,
    @SerialName("status")
    val status: Long? = null,
    @SerialName("stype")
    val stype: Long? = null,
    @SerialName("timestamp")
    @Serializable(with = LocalDateTimeSerializer::class)
    val timestamp: LocalDateTime,
    @SerialName("type")
    val type: Long? = null,
    @SerialName("uid")
    val uid: Long? = null,
    @SerialName("uid_type")
    val uidType: Long? = null,
    @SerialName("view")
    val view: Long? = null
){
    object LocalDateTimeSerializer: KSerializer<LocalDateTime> {
        override fun deserialize(decoder: Decoder): LocalDateTime {
            return kotlin.runCatching { decoder.decodeLong().toLocalDateTime() }.getOrElse { LocalDateTime.now() }
        }

        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("BiliDynamicTimestamp", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: LocalDateTime) {
            encoder.encodeLong(value.toEpochSecond())
        }

    }
}

@Serializable
data class Extension(
    @SerialName("lott")
    val lott: String? = null
)

@Serializable
data class Extra(
    @SerialName("is_space_top")
    val isSpaceTop: Long? = null
)