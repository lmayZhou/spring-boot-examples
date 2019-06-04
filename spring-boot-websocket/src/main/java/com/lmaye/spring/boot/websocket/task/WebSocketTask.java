package com.lmaye.spring.boot.websocket.task;

import com.lmaye.spring.boot.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * -- WebSocket Task
 *
 * @author lmay.Zhou
 * @date 2018/12/6 14:06 星期四
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Slf4j
@Component
public class WebSocketTask {
    private final WebSocketService webSocketService;

    public WebSocketTask(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    /**
     * 服务器主动向客户端推送消息
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void initiativeSendMsg() {
        log.info("服务器主动向客户端推送消息: {}", webSocketService.initiativeSendMsg());
    }
}
