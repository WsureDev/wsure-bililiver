package top.wsure.bililiver.bililiver.dtos.event.cmd

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RoomChange(
    @SerialName("area_id")
    val areaId: Int,
    @SerialName("area_name")
    val areaName: String,
    @SerialName("live_key")
    val liveKey: String,
    @SerialName("parent_area_id")
    val parentAreaId: Int,
    @SerialName("parent_area_name")
    val parentAreaName: String,
    @SerialName("sub_session_key")
    val subSessionKey: String,
    @SerialName("title")
    val title: String
)