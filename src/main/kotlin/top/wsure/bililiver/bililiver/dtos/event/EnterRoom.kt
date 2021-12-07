package top.wsure.bililiver.bililiver.dtos.event

import top.wsure.bililiver.bililiver.enums.Operation
import top.wsure.bililiver.bililiver.enums.ProtocolVersion
import top.wsure.bililiver.utils.JsonUtils.objectToJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EnterRoom(
    @SerialName("roomid")
    val roomid: Long,
    @SerialName("key")
    val key: String? = null,
    @SerialName("uid")
    val uid: Int = 0,
    @SerialName("protover")
    val protover: Int = 3,
    @SerialName("platform")
    val platform: String = "web",
    @SerialName("type")
    val type: Int = 2,
){
    fun toPackage():ChatPackage{
        val bodyByteArray = this.objectToJson().toByteArray()
        return ChatPackage(ProtocolVersion.INT,Operation.HELLO, bodyByteArray)
    }
}