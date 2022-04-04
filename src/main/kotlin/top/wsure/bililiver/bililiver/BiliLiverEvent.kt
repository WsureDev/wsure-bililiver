package top.wsure.bililiver.bililiver

import top.wsure.bililiver.bililiver.dtos.api.room.Room
import top.wsure.bililiver.bililiver.dtos.event.cmd.*
import top.wsure.bililiver.bililiver.dtos.event.cmd.*

abstract class BiliLiverEvent {

    lateinit var room: Room

    open suspend fun onSuperChatMessage(superChatMessage: SuperChatMessage){

    }
    open suspend fun onSendGift(sendGift: SendGift){

    }

    open suspend fun onComboSend(comboSend: ComboSend){

    }

    open suspend fun onOnlineRankTop3(onlineRankTop3: OnlineRankTop3){

    }

    open suspend fun onRoomRealTimeMessageUpdate(roomRealTimeMessageUpdate: RoomRealTimeMessageUpdate){

    }

    open suspend fun onRoomBlockMsg(roomBlockMsg:RoomBlockMsg){

    }

    open suspend fun onSuperChatMessageDelete(superChatMessageDelete:SuperChatMessageDelete){

    }

    open suspend fun onGuardBuy(guardBuy: GuardBuy){

    }

    open suspend fun onHotRankSettlement(hotRankSettlement: HotRankSettlement) {

    }

    open suspend fun onDanmuMsg(danmuMsg: DanmuMsg) {

    }

    open suspend fun onInteractWord(interactWord: InteractWord) {

    }

    open suspend fun onEntryEffect(entryEffect: EntryEffect) {

    }

    open suspend fun onVtrGiftLottery(vtrGiftLottery: VtrGiftLottery) {

    }

    open suspend fun onUserToastMsg(userToastMsg: UserToastMsg) {

    }

    open suspend fun onWatchedChange(watchedChange: WatchedChange) {

    }
}
