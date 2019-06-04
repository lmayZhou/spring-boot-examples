package com.lmaye.spring.boot.websocket.service;

import com.lmaye.examples.common.common.Response;

/**

 * -- WebSocket Service Interface
 *
 * @author lmay.Zhou
 * @date 2018/12/6 10:20 星期四
 * @qq 379839355
 * @email lmay@lmaye.com
 */
public interface WebSocketService {
    /**
     * 查询用户信息
     * - 根据用户ID
     *
     * @param userId 用户ID
     * @return Response
     */
    Response selectUserByUserId(String userId);

    /**
     * 服务器主动推送消息
     *
     * @return Response
     */
    Response initiativeSendMsg();
}
