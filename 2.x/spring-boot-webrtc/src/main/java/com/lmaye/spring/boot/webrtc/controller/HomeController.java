package com.lmaye.spring.boot.webrtc.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * -- Home Controller
 *
 * @author lmay.Zhou
 * @date 2019/10/25 16:42 星期五
 * @email lmay_zlm@meten.com
 */
@Controller
@RequestMapping("/rtc")
@Api(tags = "直播相关接口")
public class HomeController {
    /**
     * 直播页面
     *
     * @return 视图
     */
    @GetMapping("/broadcast")
    public String broadcast() {
        return "broadcast";
    }

    /**
     * 观看直播页面
     *
     * @return 视图
     */
    @GetMapping("/watchLive")
    public String watchLive() {
        return "watchLive";
    }
}
