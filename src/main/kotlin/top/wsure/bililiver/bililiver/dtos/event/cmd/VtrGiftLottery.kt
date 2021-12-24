package top.wsure.bililiver.bililiver.dtos.event.cmd
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class VtrGiftLottery(
    @SerialName("act_name")
    val actName: String,
    @SerialName("award_username")
    val awardUsername: String,
    @SerialName("dark_highlight_col")
    val darkHighlightCol: String,
    @SerialName("dmscore")
    val dmscore: Long,
    @SerialName("highlight_col")
    val highlightCol: String,
    @SerialName("interact_msg")
    val interactMsg: String,
    @SerialName("lottery_id")
    val lotteryId: String,
    @SerialName("room_id")
    val roomId: Long,
    @SerialName("toast_msg")
    val toastMsg: String,
    @SerialName("uid")
    val uid: Long
)