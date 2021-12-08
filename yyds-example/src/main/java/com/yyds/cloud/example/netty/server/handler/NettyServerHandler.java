package com.yyds.cloud.example.netty.server.handler;

import cn.hutool.core.io.file.FileWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yyds.cloud.example.netty.protocol.message.HeartbeatResponsePacket;
import com.yyds.cloud.example.netty.protocol.protobuf.MessageBase;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;


@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<MessageBase.Message> {

    private CountDownLatch second = new CountDownLatch(2);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBase.Message msg) throws Exception {
        if (msg.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)) {
            log.info("收到客户端发来的心跳消息：{}", msg.toString());
            //回应pong 150*10
            ctx.writeAndFlush(new HeartbeatResponsePacket());
        } else if (msg.getCmd().equals(MessageBase.Message.CommandType.NORMAL)) {
            byte[] bytes = msg.getContent().toByteArray();
            String requestId = msg.getRequestId();
//            FileOutputStre am fou = new FileOutputStream("/Volumes/me/base/file/"+requestId+".dmg", true);
//            fou.write(bytes);
//            fou.close();
            FileWriter fileWriter = new FileWriter("/Volumes/me/base/file/"+requestId+".dmg");
            fileWriter.append(bytes, 0, bytes.length);
//            ThreadPoolFactory.executorServic.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName()+"=>打印:");
//                    FileWriter fileWriter = new FileWriter("/Volumes/me/base/file/"+requestId+".dmg");
//                    fileWriter.append(bytes, 0, bytes.length);
//                }
//            });

        }
    }
}
