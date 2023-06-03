package com.mlzj.web.socket.controller.socket;


import com.mlzj.web.socket.domain.JsonRspHelper;
import com.mlzj.web.socket.utils.WebsocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@ServerEndpoint(value = "/test")
public class TestController {

    @OnOpen
    public void onOpen(Session session) {
        String userId = WebsocketUtil.getParam(WebsocketUtil.sessionKey, session);
        // 添加到session的映射关系中
        WebsocketUtil.addSession(userId, session);

        //测试发送消息
        WebsocketUtil.sendMessage(userId, JsonRspHelper.success());
        new Thread(()->  {
            while (true) {
                WebsocketUtil.sendMessage(userId, JsonRspHelper.success(new Random().nextInt(100)));
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @OnClose
    public void onClose(Session session) {
        String deviceId = WebsocketUtil.getParam(WebsocketUtil.sessionKey, session);
        // 删除映射关系
        WebsocketUtil.removeSession(deviceId);
    }

    /**
     * 当接收到用户上传的消息
     *
     * @param session
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            if (session.isOpen()) {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }
}
