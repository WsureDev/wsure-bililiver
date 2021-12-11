package top.wsure.bililiver.bililiver.dtos.event.cmd
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class StopLiveRoomList(
    @SerialName("room_id_list")
    val roomIdList: List<Long>
)