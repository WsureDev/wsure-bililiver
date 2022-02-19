package top.wsure.bililiver.bililiver.api

import top.wsure.bililiver.bililiver.dtos.api.BiliResponse
import top.wsure.bililiver.bililiver.dtos.api.danmu.Danmu
import top.wsure.bililiver.bililiver.dtos.api.danmu.DanmuRes
import top.wsure.bililiver.bililiver.dtos.api.room.Room
import top.wsure.bililiver.bililiver.dtos.api.token.TokenAndUrl
import top.wsure.guild.common.utils.JsonUtils.jsonToObjectOrNull
import top.wsure.guild.common.utils.JsonUtils.toMap
import top.wsure.guild.common.utils.OkHttpUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.bililiver.bililiver.dtos.api.dynamic.BiliDynamic
import top.wsure.bililiver.bililiver.dtos.api.dynamic.DynamicCard
import top.wsure.bililiver.bililiver.dtos.api.room.RoomInfo
import top.wsure.bililiver.bililiver.dtos.api.space.Space
import top.wsure.guild.common.utils.UA

object BiliLiverApi {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private const val ROOM_NEWS = "https://api.live.bilibili.com/room_ex/v1/RoomNews/get?roomid={{room_id}}"
    private const val ROOM_INFO = "https://api.live.bilibili.com/room/v1/Room/get_info?id={{room_id}}"
    private const val TOKEN_AND_URL = "https://api.live.bilibili.com/xlive/web-room/v1/index/getDanmuInfo?id={{real_room_id}}&type=0"

    private const val SEND_DANMU = "https://api.live.bilibili.com/msg/send"

    private const val SPACE = "https://api.bilibili.com/x/space/acc/info?mid={{uid}}&jsonp=jsonp"

    private const val DYNAMIC = "https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?visitor_uid=0&host_uid={{host_uid}}&offset_dynamic_id={{offset_dynamic_id}}&need_top={{need_top}}&platform=web"
    fun getRealRoomId(roomId:String): Room?{
        val url = ROOM_NEWS.replace("{{room_id}}", roomId)
        val res = OkHttpUtils.getJson(url, getApiHeader(roomId)).jsonToObjectOrNull<BiliResponse<Room>>()
        logger.info("$roomId getRealRoomId ${if(res != null) "success" else "fail"}")
        return res?.data
    }
    fun getRoomInfo(roomId:String): RoomInfo?{
        val url = ROOM_INFO.replace("{{room_id}}", roomId)
        val res = OkHttpUtils.getJson(url, getApiHeader(roomId)).jsonToObjectOrNull<BiliResponse<RoomInfo>>()
        logger.info("$roomId getRoomInfo ${if(res != null) "success" else "fail"}")
        return res?.data
    }

    fun getTokenAndUrl(realRoomId:String): TokenAndUrl?{
        val url = TOKEN_AND_URL.replace("{{real_room_id}}", realRoomId)
        val res = OkHttpUtils.getJson(url, mutableMapOf("User-Agent" to UA.PC.getValue()))
            .jsonToObjectOrNull<BiliResponse<TokenAndUrl>>()
        logger.info("$realRoomId getTokenAndUrl ${if(res != null) "success" else "fail"}")
        return res?.data
    }

    fun sendDanmu(danmu: Danmu, cookie:String) : DanmuRes?{
        val res = OkHttpUtils.postStr(
            SEND_DANMU ,danmu.toMap(), OkHttpUtils.addHeaders(mutableMapOf(
            "User-Agent" to UA.PC.getValue(),
            "Cookie" to cookie
        ))).jsonToObjectOrNull<BiliResponse<DanmuRes>>()
        logger.info("send danmu :${danmu.msg} ${if(res != null) "success" else "fail"}")
        return res?.data
    }

    private fun getApiHeader(roomId:String):Map<String,String>{
        return mutableMapOf(
            "User-Agent" to UA.PC.getValue(),
            "referer" to "https://live.bilibili.com/$roomId"
        )
    }

    fun getSpace(uid:String):Space?{
        val url = SPACE.replace("{{uid}}",uid)
        val res = OkHttpUtils.getStr(url, mutableMapOf(
            "User-Agent" to UA.PC.getValue(),
        )).jsonToObjectOrNull<BiliResponse<Space>>()
        logger.info("get space from ${uid}: ${if(res != null) "success" else "fail"}")
        return res?.data
    }

    fun getDynamicTopList(uid:String,offsetDynamicId:String = "0",needTop:Boolean = true): List<DynamicCard>{
        val res = getBiliDynamic(uid, offsetDynamicId, needTop)
        return if (res == null || res.cards.isNullOrEmpty()) emptyList() else res.cards
    }


    fun getBiliDynamic(uid:String,offsetDynamicId:String = "0",needTop:Boolean = true): BiliDynamic?{
        val url = DYNAMIC.replace("{{host_uid}}",uid)
            .replace("{{offset_dynamic_id}}",offsetDynamicId)
            .replace("{{need_top}}", if(needTop) "1" else "0")
        logger.info(" url :$url")
        val text = OkHttpUtils.getStr(url, mutableMapOf(
            "User-Agent" to UA.PC.getValue(),
        ))
        val res = text.jsonToObjectOrNull<BiliResponse<BiliDynamic>>()
        logger.info("get Dynamic from ${uid}: ${if(res != null) "success" else "fail"}")
        return res?.data
    }


}