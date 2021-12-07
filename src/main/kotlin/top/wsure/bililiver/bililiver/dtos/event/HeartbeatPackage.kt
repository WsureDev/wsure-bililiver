package top.wsure.bililiver.bililiver.dtos.event

import top.wsure.bililiver.bililiver.enums.Operation
import top.wsure.bililiver.bililiver.enums.ProtocolVersion

object HeartbeatPackage: ChatPackage(
31,
    16,
    ProtocolVersion.INT,
    Operation.HEARTBEAT,
    1,
    "[object Object]".toByteArray()
)