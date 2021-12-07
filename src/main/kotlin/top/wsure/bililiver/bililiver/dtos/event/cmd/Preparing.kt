package top.wsure.bililiver.bililiver.dtos.event.cmd

import top.wsure.bililiver.bililiver.enums.NoticeCmd
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Preparing(
    @SerialName("cmd")
    val cmd: NoticeCmd,
    @SerialName("roomid")
    val roomid: String
)