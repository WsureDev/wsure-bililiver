package top.wsure.bililiver.bililiver

import top.wsure.bililiver.bililiver.BiliLiverChatUtils.brotli
import top.wsure.bililiver.bililiver.BiliLiverChatUtils.toChatPackage
import top.wsure.bililiver.bililiver.BiliLiverChatUtils.toChatPackageList
import top.wsure.bililiver.bililiver.BiliLiverChatUtils.zlib
import top.wsure.bililiver.bililiver.dtos.api.room.Room
import top.wsure.bililiver.bililiver.dtos.event.*
import top.wsure.bililiver.bililiver.dtos.event.cmd.*
import top.wsure.bililiver.bililiver.dtos.event.cmd.DanmuMsg.Companion.toDanmuMsg
import top.wsure.bililiver.bililiver.enums.NoticeCmd
import top.wsure.bililiver.bililiver.enums.Operation
import top.wsure.bililiver.bililiver.enums.ProtocolVersion
import top.wsure.bililiver.common.BaseBotListener
import top.wsure.bililiver.utils.JsonUtils.jsonToObjectOrNull
import top.wsure.bililiver.utils.ScheduleUtils
import okhttp3.Response
import okhttp3.WebSocket
import okio.ByteString
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.bililiver.bililiver.dtos.event.cmd.EntryEffect
import top.wsure.bililiver.bililiver.dtos.event.cmd.GuardBuy
import top.wsure.bililiver.bililiver.dtos.event.cmd.HotRankSettlement
import java.util.*
import java.util.concurrent.atomic.AtomicLong

class BiliLiverListener(
    val room: Room,
    val token: String?,
    val biliLiverEvents: List<BiliLiverEvent>,
    val heartbeatDelay: Long,
    val reconnectTimeout: Long,
    retryTime:Long,
    retryWait:Long,
) : BaseBotListener(retryTime, retryWait) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private var hbTimer: Timer? = null
    private val lastReceivedHeartBeat = AtomicLong(0)

    val logHeader = room.toRoomStr()

    var enterRoom = EnterRoom(room.roomid.toLong(),token)
    override fun onOpen(webSocket: WebSocket, response: Response) {
        logger.info("$logHeader onOpen ,send enterRoom package")
        logger.trace("$logHeader enter room hex : ${enterRoom.toPackage().encode().hex()}")
        webSocket.sendAndPrintLog(enterRoom.toPackage())
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        stopRetryReconnect()
        kotlin.runCatching{
            logger.trace("$logHeader onMessage ,context:${bytes.hex() }")
            val originPkg = bytes.toChatPackage()
            val pkgList = mutableListOf<ChatPackage>()
            when(originPkg.protocolVersion){
                ProtocolVersion.INT,ProtocolVersion.JSON -> pkgList.add(originPkg)
                ProtocolVersion.ZLIB_INFLATE -> pkgList.addAll(originPkg.body.zlib().toChatPackageList())
                ProtocolVersion.BROTLI -> pkgList.addAll(originPkg.body.brotli().toChatPackageList())
                else -> {
                    logger.warn("onMessage : unknown Message  ,context:${bytes.hex() }")
                    return
                }
            }
            pkgList.onEach { pkg ->
                logger.trace("$logHeader onMessage ${pkg.protocolVersion},${pkg.operation} ,context:${ pkg.content() }")
                when(pkg.operation){
                    Operation.HELLO_ACK -> {
                        initHeartbeat(webSocket)
                    }
                    Operation.HEARTBEAT_ACK -> {
                        receivedHeartbeat(pkg.content())
                    }
                    Operation.NOTICE -> {
                        onNotice(pkg)
                    }
                    else -> {
                        logger.warn("$logHeader unhandled operation,${pkg.content()}")
                    }
                }
            }
        }.onFailure {
            it.printStackTrace()
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        logger.error("$logHeader onClosing , try to reconnect code:{} reason:{},",code,reason)
        startRetryReconnect()
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        logger.error("$logHeader onClosing , try to reconnect code:{} reason:{},",code,reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        logger.error("$logHeader onFailure , try to reconnect",t)
        startRetryReconnect()
    }


    private fun onNotice(pkg: ChatPackage) {
        val content = pkg.content()

        content.jsonToObjectOrNull<CmdType>()?.also { type ->
            logger.debug("$logHeader received ${type.cmd.description} :{}",content)
            when(type.cmd){
                NoticeCmd.INTERACT_WORD -> {
                    content.jsonToObjectOrNull<ChatCmdBody<InteractWord>>()?.also { interactWord ->
                        biliLiverEvents.onEach {
                            it.onInteractWord(interactWord.data)
                        }
                    }
                }
                NoticeCmd.ENTRY_EFFECT -> {
                    content.jsonToObjectOrNull<ChatCmdBody<EntryEffect>>()?.also { entryEffect ->
                        biliLiverEvents.onEach {
                            it.onEntryEffect(entryEffect.data)
                        }
                    }
                }
                NoticeCmd.SUPER_CHAT_MESSAGE -> {
                    content.jsonToObjectOrNull<ChatCmdBody<SuperChatMessage>>()?.also { superChat ->
                        biliLiverEvents.onEach {
                            it.onSuperChatMessage(superChat.data)
                        }
                    }
                }
                NoticeCmd.SEND_GIFT -> {
                    content.jsonToObjectOrNull<ChatCmdBody<SendGift>>()?.also { sendGift ->
                        biliLiverEvents.onEach {
                            it.onSendGift(sendGift.data)
                        }
                    }
                }
                NoticeCmd.ROOM_REAL_TIME_MESSAGE_UPDATE -> {
                    content.jsonToObjectOrNull<ChatCmdBody<RoomRealTimeMessageUpdate>>()?.also { roomRealTimeMessageUpdate ->
                        biliLiverEvents.onEach {
                            it.onRoomRealTimeMessageUpdate(roomRealTimeMessageUpdate.data)
                        }
                    }
                }
                NoticeCmd.ONLINE_RANK_TOP3 -> {
                    content.jsonToObjectOrNull<ChatCmdBody<OnlineRankTop3>>()?.also { onlineRankTop3 ->
                        biliLiverEvents.onEach {
                            it.onOnlineRankTop3(onlineRankTop3.data)
                        }
                    }
                }
                NoticeCmd.GUARD_BUY -> {
                    content.jsonToObjectOrNull<ChatCmdBody<GuardBuy>>()?.also { guardBuy ->
                        biliLiverEvents.onEach {
                            it.onGuardBuy(guardBuy.data)
                        }
                    }
                }
                NoticeCmd.ROOM_BLOCK_MSG -> {
                    content.jsonToObjectOrNull<ChatCmdBody<RoomBlockMsg>>()?.also { roomBlockMsg ->
                        biliLiverEvents.onEach {
                            it.onRoomBlockMsg(roomBlockMsg.data)
                        }
                    }
                }
                NoticeCmd.SUPER_CHAT_MESSAGE_DELETE -> {
                    content.jsonToObjectOrNull<ChatCmdBody<SuperChatMessageDelete>>()?.also { superChatMessageDelete ->
                        biliLiverEvents.onEach {
                            it.onSuperChatMessageDelete(superChatMessageDelete.data)
                        }
                    }
                }
                NoticeCmd.HOT_RANK_SETTLEMENT,NoticeCmd.HOT_RANK_SETTLEMENT_V2 -> {
                    content.jsonToObjectOrNull<ChatCmdBody<HotRankSettlement>>()?.also { hotRankSettlement ->
                        biliLiverEvents.onEach {
                            it.onHotRankSettlement(hotRankSettlement.data)
                        }
                    }
                }
                NoticeCmd.DANMU_MSG -> {
                    content.toDanmuMsg().also { danmuMsg ->
                        biliLiverEvents.onEach {
                            it.onDanmuMsg(danmuMsg)
                        }
                    }
                }
                NoticeCmd.VTR_GIFT_LOTTERY -> {
                    content.jsonToObjectOrNull<ChatCmdBody<VtrGiftLottery>>()?.also { vtrGiftLottery ->
                        biliLiverEvents.onEach {
                            it.onVtrGiftLottery(vtrGiftLottery.data)
                        }
                    }
                }
                else -> {}
            }
        }
    }
    private fun initHeartbeat(webSocket:WebSocket) {
        lastReceivedHeartBeat.getAndSet(System.currentTimeMillis())
        val processor = createHeartBeatProcessor(webSocket)
        //  先取消以前的定时器
        hbTimer?.cancel()
        // 启动新的心跳
        hbTimer = ScheduleUtils.loopEvent(processor,Date(),heartbeatDelay)
    }
    override fun cancel(){
        super.cancel()
        // do something cancel heartbeat
        hbTimer?.cancel()
    }

    private fun receivedHeartbeat(content:String) {
        logger.trace("$logHeader received heartbeat $content")
        lastReceivedHeartBeat.getAndSet(System.currentTimeMillis())
    }

    private fun createHeartBeatProcessor(webSocket: WebSocket):suspend () ->Unit {
        return suspend {
            val last = lastReceivedHeartBeat.get()
            val now = System.currentTimeMillis()
            if( now - last > reconnectTimeout){
                logger.warn("$logHeader heartbeat timeout , try to reconnect")
                startRetryReconnect()
            } else {
                webSocket.sendAndPrintLog(HeartbeatPackage,true)

            }

        }
    }

    private fun WebSocket.sendAndPrintLog(pkg: ChatPackage, isHeartbeat:Boolean = false){
        if(isHeartbeat){
            logger.trace("$logHeader send Heartbeat ${pkg.content()}")
        } else {
            logger.debug("$logHeader send text message ${pkg.content()}")
        }
        this.send(pkg.encode())
    }
}
