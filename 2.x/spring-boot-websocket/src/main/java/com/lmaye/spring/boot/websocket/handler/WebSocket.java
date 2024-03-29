package com.lmaye.spring.boot.websocket.handler;

import com.lmaye.cloud.core.exception.CoreException;
import com.lmaye.cloud.core.utils.GsonUtils;
import com.lmaye.cloud.starter.web.context.ResultVO;
import com.lmaye.spring.boot.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**

 * -- Websocket Handler
 *
 * @author lmay.Zhou
 * @date 2018/12/5 18:11 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Slf4j
@Component
@ServerEndpoint(value = "/ws/{userId}")
public class WebSocket {
    public static WebSocketService webSocketService;

    private static final ConcurrentHashMap<String, WebSocket> WEB_SOCKET = new ConcurrentHashMap<>();

    /**
     * 用户编号
     */
    private String userId;

    /**
     * session
     */
    private Session session;

    /**
     * 建立连接调用的方法
     *
     * @param userId  用户ID
     * @param session Session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String userId, Session session) {
        try {
            this.session = session;
            this.userId = userId;
            WEB_SOCKET.put(this.userId, this);
            long count = WEB_SOCKET.size();
            log.info("用户[" + this.userId + "]加入连接在线总数[" + count + "]");
            sendMessage(GsonUtils.toJson(ResultVO.success("连接成功")));
        } catch (CoreException e) {
            sendMessage(GsonUtils.toJson(ResultVO.failed()));
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        WEB_SOCKET.remove(this.userId);
        long count = WebSocket.WEB_SOCKET.size();
        log.info("用户[" + this.userId + "]关闭连接在线总数[" + count + "]");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param json 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String json, Session session) {
        if (StringUtils.isNotBlank(json)) {
            ResultVO<Map<String, Object>> result = webSocketService.selectUserByUserId(userId);
            result.getData().put("msg", json);
            sendMessage(GsonUtils.toJson(result));
        } else {
            sendMessage(GsonUtils.toJson(ResultVO.failed()));
        }
    }

    /**
     * 连接错误
     *
     * @param session Session
     * @param e       Throwable
     */
    @OnError
    public void onError(Session session, Throwable e) {
        log.error("websocket IO异常", e);
    }


    /**
     * 发送消息
     *
     * @param message 消息
     */
    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("发送用户[" + this.userId + "]消息[" + message + "]失败", e);
        }
    }

    /**
     * 给用户发送消息
     *
     * @param userId  用户编号
     * @param message 消息
     */
    private void sendMessage(String userId, String message) {
        if (WEB_SOCKET.get(userId) != null) {
            WEB_SOCKET.get(userId).sendMessage(message);
        }
    }

    /**
     * 发送信息
     *
     * @param userIds 用户
     * @param message 消息
     */
    public static void sendMessage(Set<String> userIds, String message) {
        for (String userId : userIds) {
            WEB_SOCKET.get(userId).sendMessage(message);
        }
    }

    /**
     * 发送信息给所有人
     *
     * @param message 消息
     */
    public static void sendMessageAll(String message) {
        for (String userId : WEB_SOCKET.keySet()) {
            WEB_SOCKET.get(userId).sendMessage(message);
        }
    }

    /**
     * 获取在线人数
     *
     * @return long
     */
    public static synchronized long getCount() {
        return WEB_SOCKET.size();
    }
}
