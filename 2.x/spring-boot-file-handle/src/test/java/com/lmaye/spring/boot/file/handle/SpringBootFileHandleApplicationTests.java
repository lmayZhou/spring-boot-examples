package com.lmaye.spring.boot.file.handle;

import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Arrays;

@Slf4j
@SpringBootTest
class SpringBootFileHandleApplicationTests {

    @Test
    void contextLoads() throws Exception {
        File file = ResourceUtils.getFile("classpath:fdfs_client.conf");
        // 加载配置
        ClientGlobal.init(file.getPath());
        // 创建Tracker客户端
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        // 文件上传
        String[] items = storageClient.upload_file("C:\\Users\\lmayZhou\\Desktop\\test.png", "png", null);
        Arrays.asList(items).forEach(System.out::println);

        //获取Tracker地址
        String host = "http://" + trackerServer.getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port();
        log.info("host: {}", host);
        String fileUrl = host + "/" + items[0] + "/" + items[1];
        log.info("访问地址: {}", fileUrl);
        int count = storageClient.delete_file("group1", "wKgACl8SX3WAVU2wAAdH6FDo5_A449.png");
        log.info("文件删除: {}", count);
    }
}
