package com.yyds.cloud.example.netty;

import com.google.protobuf.ByteString;
import com.yyds.cloud.example.netty.client.NettyClient;
import com.yyds.cloud.example.netty.protocol.protobuf.MessageBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@Api(value = "测试")
public class TestController {
    @Autowired
    private NettyClient nettyClient;

    @GetMapping("/send")
    @ApiOperation(value = "发送文件")
    public String send() throws IOException {
        System.out.println("开始传输: " + LocalDateTime.now());
        this.readFile();
        System.out.println("结束传输: " + LocalDateTime.now());
        return "send ok";
    }


    private void readFile() {
        // 定义一个输入流对象
        FileInputStream fis = null;
//        FileOutputStream fou = null;
        try {
            fis = new FileInputStream("/Volumes/me/下载/android-studio-2020.3.1.23-mac.dmg");
//            fou = new FileOutputStream("/Volumes/me/base/Docker.dmg");

            int len;
            byte[] buf = new byte[1024*100];
            String requestId = UUID.randomUUID().toString();
            while ((len = fis.read(buf)) != -1) {
                ByteString bytes = ByteString.copyFrom(buf, 0, len);
//                //这里直接用字符串StringBuffer的append方法也可以接收
                MessageBase.Message message = new MessageBase.Message()
                        .toBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
                        .setContent(bytes)
                        .setRequestId(requestId).build();
                nettyClient.sendMsg(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
