package com.lmaye.spring.boot.webrtc.handler;

import com.lmaye.cloud.core.exception.CoreException;
import com.lmaye.cloud.core.utils.GsonUtils;
import com.lmaye.cloud.starter.web.context.ResultVO;
import com.lmaye.spring.boot.webrtc.params.MessageParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * -- Web Socket
 *
 * @author lmay.Zhou
 * @date 2019/10/25 15:04 星期五
 * @email lmay_zlm@meten.com
 */
@Slf4j
@Component
@ServerEndpoint(value = "/ws/{name}")
public class WebSocket {
    /**
     * 在线连接数
     */
    private static long onlineConnectCount = 0;

    /**
     * Web Socket
     */
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();

    /**
     * session
     */
    private Session session;

    /**
     * 用户名
     */
    private String name;

    /**
     * 建立连接调用的方法
     *
     * @param session Session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "name") String name, Session session) {
        try {
            this.session = session;
            this.name = name;
            Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
            List<String> receivers = requestParameterMap.get("receiver");
            // 数据是否为空
            boolean isEmptyReceivers = !Objects.isNull(receivers) && !receivers.isEmpty();
            if (isEmptyReceivers) {
                // 非主播建立连接
                String receiver = receivers.get(0);
                MessageParam param = new MessageParam();
                param.setName(name);
                param.setReceiver(receiver);
                this.onMessage(GsonUtils.toJson(ResultVO.success(param)), session);
            }
            webSocketMap.put(name, this);
            onlineConnectCount = webSocketMap.size();
            log.info("用户[" + name + "]加入连接，在线总数[" + onlineConnectCount + "]");
        } catch (CoreException e) {
            sendMessage(GsonUtils.toJson(ResultVO.failed()));
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(this.name);
        onlineConnectCount = webSocketMap.size();
        log.info("用户[" + this.name + "]关闭连接，在线总数[" + onlineConnectCount + "]");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if (StringUtils.isNotBlank(message)) {
            ResultVO rs = GsonUtils.fromJson(message, ResultVO.class);
            MessageParam param = (MessageParam) rs.getData();
            webSocketMap.keySet().forEach(key -> {
                if (Objects.equals(key, param.getReceiver())) {
                    WebSocket webSocket = webSocketMap.get(key);
                    webSocket.sendMessage(message);
                }
            });
        } else {
            sendMessage(GsonUtils.toJson(ResultVO.failed()));
        }
    }

    /**
     * 连接错误
     *
     * @param session session
     * @param e       Throwable
     */
    @OnError
    public void onError(Session session, Throwable e) {
        log.error("Websocket IO异常", e);
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    private void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("发送用户[" + this.name + "]消息[" + message + "]失败", e);
        }
    }

    /**
     * 发送信息给所有人
     *
     * @param message 消息
     */
    public static void sendMessageAll(String message) {
        webSocketMap.keySet().forEach(key -> webSocketMap.get(key).sendMessage(message));
    }

    /**
     * 发送消息
     *
     * @param names   用户名[Many]
     * @param message 消息
     */
    public static void sendMessage(Set<String> names, String message) {
        Assert.notEmpty(names, "The matching value cannot be empty");
        names.forEach(it -> webSocketMap.get(it).sendMessage(message));
    }

    /**
     * 发送消息
     *
     * @param name    用户名
     * @param message 消息
     */
    public static void sendMessage(String name, String message) {
        Assert.hasLength(name, "The matching value cannot be empty");
        webSocketMap.get(name).sendMessage(message);
    }

    /**
     * 获取在线人数
     *
     * @return long
     */
    public static synchronized long getOnlineConnectCount() {
        return onlineConnectCount;
    }
}
