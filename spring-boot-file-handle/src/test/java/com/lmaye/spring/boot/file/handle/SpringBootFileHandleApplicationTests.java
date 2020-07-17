package com.lmaye.spring.boot.file.handle;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Arrays;

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
        String[] items = storageClient.upload_file("C:\\Users\\lmay\\Desktop\\git.jpeg", ".jpeg", null);
        Arrays.asList(items).forEach(System.out::println);
        System.out.println("获取文件: " + storageClient.get_file_info("group1", "M00/00/00/wKjThF1VFfKAJRJDAANdC6JX9KA980.jpg"));
    }
}
