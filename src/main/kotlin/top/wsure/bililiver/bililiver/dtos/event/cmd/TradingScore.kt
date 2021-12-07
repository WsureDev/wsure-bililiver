package top.wsure.bililiver.bililiver.dtos.event.cmd

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TradingScore(
    @SerialName("bubble_show_time")
    val bubbleShowTime: Int,
    @SerialName("num")
    val num: Int,
    @SerialName("score_id")
    val scoreId: Int,
    @SerialName("uid")
    val uid: Int,
    @SerialName("update_time")
    val updateTime: Int,
    @SerialName("update_type")
    val updateType: Int
)