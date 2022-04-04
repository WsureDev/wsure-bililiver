package top.wsure.bililiver.bililiver.dtos.event.cmd
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class WatchedChange(
    @SerialName("num")
    val num: Int,
    @SerialName("text_large")
    val textLarge: String,
    @SerialName("text_small")
    val textSmall: String
)