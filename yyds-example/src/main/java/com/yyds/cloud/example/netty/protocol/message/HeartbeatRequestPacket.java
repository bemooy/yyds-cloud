package com.yyds.cloud.example.netty.protocol.message;

import lombok.Data;

import static com.yyds.cloud.example.netty.protocol.message.command.Command.HEARTBEAT_REQUEST;

@Data
public class HeartbeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
