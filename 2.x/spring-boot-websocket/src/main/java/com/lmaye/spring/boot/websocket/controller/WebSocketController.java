package com.lmaye.spring.boot.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * -- Websocket Controller
 *
 * @author lmay.Zhou
 * @date 2018/12/5 18:06 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Controller
@RequestMapping("/ws")
public class WebSocketController {
    /**
     * 首页
     *
     * @return View
     */
    @GetMapping("/index.html")
    public String indexHtml() {
        return "index";
    }
}
