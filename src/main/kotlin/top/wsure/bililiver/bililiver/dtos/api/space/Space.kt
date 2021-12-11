package top.wsure.bililiver.bililiver.dtos.api.space
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class Space(
    @SerialName("birthday")
    val birthday: String? = null,
    @SerialName("coins")
    val coins: Int? = null,
    @SerialName("face")
    val face: String? = null,
    @SerialName("face_nft")
    val faceNft: Int? = null,
    @SerialName("fans_badge")
    val fansBadge: Boolean? = null,
    @SerialName("fans_medal")
    val fansMedal: FansMedal? = null,
    @SerialName("is_followed")
    val isFollowed: Boolean? = null,
    @SerialName("jointime")
    val jointime: Int? = null,
    @SerialName("level")
    val level: Int? = null,
    @SerialName("live_room")
    val liveRoom: LiveRoom? = null,
    @SerialName("mid")
    val mid: Int? = null,
    @SerialName("moral")
    val moral: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("nameplate")
    val nameplate: Nameplate? = null,
    @SerialName("official")
    val official: Official? = null,
    @SerialName("pendant")
    val pendant: Pendant? = null,
    @SerialName("profession")
    val profession: Profession? = null,
    @SerialName("rank")
    val rank: Int? = null,
    @SerialName("school")
    val school: School? = null,
    @SerialName("series")
    val series: Series? = null,
    @SerialName("sex")
    val sex: String? = null,
    @SerialName("sign")
    val sign: String? = null,
    @SerialName("silence")
    val silence: Int? = null,
    @SerialName("top_photo")
    val topPhoto: String? = null,
    @SerialName("vip")
    val vip: Vip? = null
)

@Serializable
data class FansMedal(
    @SerialName("medal")
    val medal: Medal? = null,
    @SerialName("show")
    val show: Boolean? = null,
    @SerialName("wear")
    val wear: Boolean? = null
)

@Serializable
data class LiveRoom(
    @SerialName("")
    var name: String? = null,
    @SerialName("broadcast_type")
    val broadcastType: Int,
    @SerialName("cover")
    val cover: String,
    @SerialName("liveStatus")
    val liveStatus: Int ,
    @SerialName("online")
    val online: Int,
    @SerialName("roomStatus")
    val roomStatus: Int,
    @SerialName("roomid")
    val roomid: Int,
    @SerialName("roundStatus")
    val roundStatus: Int,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class Nameplate(
    @SerialName("condition")
    val condition: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("image_small")
    val imageSmall: String? = null,
    @SerialName("level")
    val level: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("nid")
    val nid: Int? = null
)

@Serializable
data class Official(
    @SerialName("desc")
    val desc: String? = null,
    @SerialName("role")
    val role: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("type")
    val type: Int? = null
)

@Serializable
data class Pendant(
    @SerialName("expire")
    val expire: Int? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("image_enhance")
    val imageEnhance: String? = null,
    @SerialName("image_enhance_frame")
    val imageEnhanceFrame: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("pid")
    val pid: Int? = null
)

@Serializable
data class Profession(
    @SerialName("name")
    val name: String? = null
)

@Serializable
data class School(
    @SerialName("name")
    val name: String? = null
)

@Serializable
data class Series(
    @SerialName("show_upgrade_window")
    val showUpgradeWindow: Boolean? = null,
    @SerialName("user_upgrade_status")
    val userUpgradeStatus: Int? = null
)


@Serializable
data class Vip(
    @SerialName("avatar_subscript")
    val avatarSubscript: Int? = null,
    @SerialName("avatar_subscript_url")
    val avatarSubscriptUrl: String? = null,
    @SerialName("due_date")
    val dueDate: Long? = null,
    @SerialName("label")
    val label: Label? = null,
    @SerialName("nickname_color")
    val nicknameColor: String? = null,
    @SerialName("role")
    val role: Int? = null,
    @SerialName("status")
    val status: Int? = null,
    @SerialName("theme_type")
    val themeType: Int? = null,
    @SerialName("type")
    val type: Int? = null,
    @SerialName("vip_pay_type")
    val vipPayType: Int? = null
)

@Serializable
data class Medal(
    @SerialName("day_limit")
    val dayLimit: Int? = null,
    @SerialName("guard_level")
    val guardLevel: Int? = null,
    @SerialName("intimacy")
    val intimacy: Int? = null,
    @SerialName("is_lighted")
    val isLighted: Int? = null,
    @SerialName("level")
    val level: Int? = null,
    @SerialName("light_status")
    val lightStatus: Int? = null,
    @SerialName("medal_color")
    val medalColor: Int? = null,
    @SerialName("medal_color_border")
    val medalColorBorder: Int? = null,
    @SerialName("medal_color_end")
    val medalColorEnd: Int? = null,
    @SerialName("medal_color_start")
    val medalColorStart: Int? = null,
    @SerialName("medal_id")
    val medalId: Int? = null,
    @SerialName("medal_name")
    val medalName: String? = null,
    @SerialName("next_intimacy")
    val nextIntimacy: Int? = null,
    @SerialName("score")
    val score: Int? = null,
    @SerialName("target_id")
    val targetId: Int? = null,
    @SerialName("uid")
    val uid: Int? = null,
    @SerialName("wearing_status")
    val wearingStatus: Int? = null
)

@Serializable
data class Label(
    @SerialName("bg_color")
    val bgColor: String? = null,
    @SerialName("bg_style")
    val bgStyle: Int? = null,
    @SerialName("border_color")
    val borderColor: String? = null,
    @SerialName("label_theme")
    val labelTheme: String? = null,
    @SerialName("path")
    val path: String? = null,
    @SerialName("text")
    val text: String? = null,
    @SerialName("text_color")
    val textColor: String? = null
)