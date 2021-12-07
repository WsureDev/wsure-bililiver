package top.wsure.bililiver.bililiver.dtos.api.room

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Room(
    @SerialName("content")
    val content: String,
    @SerialName("ctime")
    val ctime: String,
    @SerialName("roomid")
    val roomid: String,
    @SerialName("status")
    val status: String,
    @SerialName("uid")
    val uid: String,
    @SerialName("uname")
    val uname: String
){
    fun toRoomStr():String{
        return "主播:$uname 房间:$roomid"
    }
}