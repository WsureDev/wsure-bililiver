package top.wsure.bililiver.bililiver.dtos.event.cmd

import top.wsure.bililiver.bililiver.dtos.event.MedalInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ComboSend(
    @SerialName("action")
    val action: String,
    @SerialName("batch_combo_id")
    val batchComboId: String,
    @SerialName("batch_combo_num")
    val batchComboNum: Int,
    @SerialName("combo_id")
    val comboId: String,
    @SerialName("combo_num")
    val comboNum: Int,
    @SerialName("combo_total_coin")
    val comboTotalCoin: Int,
    @SerialName("dmscore")
    val dmscore: Int,
    @SerialName("gift_id")
    val giftId: Int,
    @SerialName("gift_name")
    val giftName: String,
    @SerialName("gift_num")
    val giftNum: Int,
    @SerialName("is_show")
    val isShow: Int,
    @SerialName("medal_info")
    val medalInfo: MedalInfo,
    @SerialName("name_color")
    val nameColor: String,
    @SerialName("r_uname")
    val rUname: String,
    @SerialName("ruid")
    val ruid: Int,
//    @SerialName("send_master")
//    val sendMaster: Any,
    @SerialName("total_num")
    val totalNum: Int,
    @SerialName("uid")
    val uid: Int,
    @SerialName("uname")
    val uname: String,

    //--
    @SerialName("batch_combo_send")
    val batchComboSend: BatchComboSend? = null,
    @SerialName("beatId")
    val beatId: String? = null,
    @SerialName("biz_source")
    val bizSource: String? = null,
    @SerialName("blind_gift")
    val blindGift: BlindGift? = null,
    @SerialName("broadcast_id")
    val broadcastId: Int? = null,
    @SerialName("coin_type")
    val coinType: String? = null,
    @SerialName("combo_resources_id")
    val comboResourcesId: Int? = null,
    @SerialName("combo_send")
    val comboSend: InnerComboSend? = null,
    @SerialName("combo_stay_time")
    val comboStayTime: Int? = null,
    @SerialName("crit_prob")
    val critProb: Int? = null,
    @SerialName("demarcation")
    val demarcation: Int? = null,
    @SerialName("discount_price")
    val discountPrice: Int? = null,
    @SerialName("draw")
    val draw: Int? = null,
    @SerialName("effect")
    val effect: Int? = null,
    @SerialName("effect_block")
    val effectBlock: Int? = null,
    @SerialName("face")
    val face: String? = null,
    @SerialName("float_sc_resource_id")
    val floatScResourceId: Int? = null,
    @SerialName("giftType")
    val giftType: Int? = null,
    @SerialName("gold")
    val gold: Int? = null,
    @SerialName("guard_level")
    val guardLevel: Int? = null,
    @SerialName("is_first")
    val isFirst: Boolean? = null,
    @SerialName("is_special_batch")
    val isSpecialBatch: Int? = null,
    @SerialName("magnification")
    val magnification: Int? = null,
    @SerialName("num")
    val num: Int? = null,
    @SerialName("original_gift_name")
    val originalGiftName: String? = null,
    @SerialName("price")
    val price: Int? = null,
    @SerialName("rcost")
    val rcost: Int? = null,
    @SerialName("remain")
    val remain: Int? = null,
    @SerialName("rnd")
    val rnd: String? = null,
    @SerialName("silver")
    val silver: Int? = null,
    @SerialName("super_batch_gift_num")
    val superBatchGiftNum: Int? = null,
    @SerialName("super_gift_num")
    val superGiftNum: Int? = null,
    @SerialName("super")
    val superX: Int? = null,
    @SerialName("svga_block")
    val svgaBlock: Int? = null,
    @SerialName("tag_image")
    val tagImage: String? = null,
    @SerialName("tid")
    val tid: String? = null,
    @SerialName("timestamp")
    val timestamp: Int? = null,
    @SerialName("total_coin")
    val totalCoin: Int? = null,
)

@Serializable
data class InnerComboSend(
    @SerialName("action")
    val action: String? = null,
    @SerialName("combo_id")
    val comboId: String? = null,
    @SerialName("combo_num")
    val comboNum: Int? = null,
    @SerialName("gift_id")
    val giftId: Int? = null,
    @SerialName("gift_name")
    val giftName: String? = null,
    @SerialName("gift_num")
    val giftNum: Int? = null,
    @SerialName("uid")
    val uid: Int? = null,
    @SerialName("uname")
    val uname: String? = null
)