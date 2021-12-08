package com.yyds.cloud.example.netty.protocol.message;

import com.yyds.cloud.example.netty.protocol.message.command.Command;

public class HeartbeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
