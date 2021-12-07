package top.wsure.bililiver.common

interface BotClient {

    fun reconnect()

    fun sendMessage(text: String)
}