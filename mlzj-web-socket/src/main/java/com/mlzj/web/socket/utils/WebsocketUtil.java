package com.mlzj.web.socket.utils;

import com.alibaba.fastjson.JSON;

import com.mlzj.web.socket.domain.JsonRsp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yhl
 * @date 2023/6/2
 */
@Slf4j
public class WebsocketUtil {

    /**
     * 记录当前在线的Session
     */
    private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();
    public static final String sessionKey = "userId";

    /**
     * 添加session
     *
     * @param userId
     * @param session
     */
    public static void addSession(String userId, Session session) {
        // 此处只允许一个用户的session链接。一个用户的多个连接，我们视为无效。
        ONLINE_SESSION.putIfAbsent(userId, session);
    }

    /**
     * 关闭session
     *
     * @param userId
     */
    public static void removeSession(String userId) {
        ONLINE_SESSION.remove(userId);
    }

    /**
     * 给单个用户推送消息
     *
     * @param session
     * @param message
     */
    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }

        // 同步
        RemoteEndpoint.Async async = session.getAsyncRemote();
        async.sendText(message);
    }

    /**
     * 向所有在线人发送消息
     *
     * @param message
     */
    public static void sendMessageForAll(String message) {
        //jdk8 新方法
        ONLINE_SESSION.forEach((sessionId, session) -> {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        });
    }

    /**
     * 根据用户ID发送消息
     *
     * @param result
     */
    public static <T> void sendMessage(String sessionId, JsonRsp<T> result) {
        sendMessage(sessionId, JSON.toJSONString(result));
    }

    /**
     * 根据用户ID发送消息
     *
     * @param message
     */
    public static void sendMessage(String sessionId, String message) {
        Session session = ONLINE_SESSION.get(sessionId);
        //判断是否存在该用户的session，判断是否还在线
        if (session == null || !session.isOpen()) {
            return;
        }
        sendMessage(session, message);
    }

    /**
     * 根据ID获取Session
     *
     * @param sessionId
     */
    public static Session getSession(String sessionId) {
        return ONLINE_SESSION.get(sessionId);
    }

    /**
     * 根据传过来的key获取session中的参数
     * @param key
     * @param session
     * @return
     */
    public static String getParam(String key, Session session) {
        Map map = session.getRequestParameterMap();
        Object userId1 = map.get(key);
        if (userId1 == null) {
            return null;
        }

        String s = userId1.toString();
        s = s.replaceAll("\\[", "").replaceAll("]", "");

        if (!StringUtils.isEmpty(s)) {
            return s;
        }
        return null;
    }

}
