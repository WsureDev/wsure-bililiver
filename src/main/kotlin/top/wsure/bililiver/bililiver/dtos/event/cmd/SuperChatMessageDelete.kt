package top.wsure.bililiver.bililiver.dtos.event.cmd

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SuperChatMessageDelete(
    @SerialName("ids")
    val ids: List<Int>
)