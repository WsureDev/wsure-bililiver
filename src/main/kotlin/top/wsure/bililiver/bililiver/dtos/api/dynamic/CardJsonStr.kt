package top.wsure.bililiver.bililiver.dtos.api.dynamic
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import top.wsure.guild.common.utils.JsonUtils.jsonToObject
import top.wsure.guild.common.utils.JsonUtils.objectToJson


@Serializable
data class CardJsonStr(
    @SerialName("category")
    val category: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("is_fav")
    val isFav: Int? = null,
    @SerialName("pictures")
    val pictures: List<Picture>? = null,
    @SerialName("pictures_count")
    val picturesCount: Int? = null,
    @SerialName("reply")
    val reply: Int? = null,
    @SerialName("settings")
    val settings: Settings? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("upload_time")
    val uploadTime: Int? = null
)

@Serializable
data class Picture(
    @SerialName("img_height")
    val imgHeight: Int? = null,
    @SerialName("img_size")
    val imgSize: Double? = null,
    @SerialName("img_src")
    val imgSrc: String? = null,
    @SerialName("img_width")
    val imgWidth: Int? = null
)

@Serializable
data class Settings(
    @SerialName("copy_forbidden")
    val copyForbidden: String? = null
)

object CardJsonStrSerializer :KSerializer<CardJsonStr>{
    override fun deserialize(decoder: Decoder): CardJsonStr {
        return decoder.decodeString().jsonToObject()
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("CardJsonStrSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: CardJsonStr) {
        encoder.encodeString(value.objectToJson())
    }

}