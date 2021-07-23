package com.lmaye.spring.boot.websocket.service;

import com.lmaye.cloud.starter.web.context.ResultVO;

import java.util.Map;

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
     * @return ResultVO<Map<String, Object>>
     */
    ResultVO<Map<String, Object>> selectUserByUserId(String userId);

    /**
     * 服务器主动推送消息
     *
     * @return ResultVO<Object>
     */
    ResultVO<String> initiativeSendMsg();
}
