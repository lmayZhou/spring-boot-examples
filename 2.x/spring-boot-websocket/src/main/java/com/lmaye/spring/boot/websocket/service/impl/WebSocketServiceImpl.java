package com.lmaye.spring.boot.websocket.service.impl;

import com.lmaye.examples.common.common.Response;
import com.lmaye.examples.common.utils.GsonUtils;
import com.lmaye.spring.boot.websocket.handler.WebSocket;
import com.lmaye.spring.boot.websocket.service.WebSocketService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**

 * -- WebSocket Service Implements
 *
 * @author lmay.Zhou
 * @date 2018/12/6 10:24 星期四
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {
    /**
     * 查询用户信息
     * - 根据用户ID
     *
     * @param userId 用户ID
     * @return Response
     */
    @Override
    public Response selectUserByUserId(String userId) {
        Map<String, Object> user = new HashMap<>(2);
        user.put("userId", userId);
        user.put("userName", "WebSocket Test");
        return Response.success(user);
    }

    /**
     * 服务器主动推送消息
     *
     * @return Response
     */
    @Override
    public Response initiativeSendMsg() {
        Response<String> rs = Response.success("欢迎使用 WebSocket 服务！");
        // 推送消息给所有用户
        WebSocket.sendMessageAll(GsonUtils.toJson(rs));
        return rs;
    }
}
