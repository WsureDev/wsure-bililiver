package top.wsure.bililiver.bililiver

import top.wsure.bililiver.bililiver.dtos.api.room.Room
import top.wsure.bililiver.common.WsBotClient

class BiliLiverClient(room: Room,
                      token: String? = null,
                      biliLiverEvents:List<BiliLiverEvent> = emptyList(),
                      wsUrl :String? = null,
                      heartbeatDelay: Long = 25000,
                      reconnectTimeout: Long = 50000,
                      retryTime:Long = 1000,
                      retryWait:Long = 3000
) : WsBotClient<BiliLiverListener>(
    wsUrl ?: "wss://broadcastlv.chat.bilibili.com/sub",
    BiliLiverListener(room,token,biliLiverEvents,heartbeatDelay,
            reconnectTimeout,
            retryTime,
            retryWait)
){
    init {
        biliLiverEvents.forEach { it.room = room }
    }
}