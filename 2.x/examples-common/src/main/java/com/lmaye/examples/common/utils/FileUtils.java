package com.lmaye.examples.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * -- 文件工具类
 *
 * @author lmay.Zhou
 * @date 2020/6/2 14:43 星期二
 * @email lmay_zlm@meten.com
 */
public class FileUtils {
    /**
     * 获取某个目录下的所有文件
     *
     * @param dir   目录
     * @param files 文件集合
     * @return List<File>
     */
    public static List<File> loadFiles(File dir, List<File> files) {
        if(Objects.isNull(files)) {
            files = new ArrayList<>();
        }
        if (!dir.exists() || !dir.isDirectory()) {
            return files;
        }
        File[] listFiles = dir.listFiles();
        if (Objects.isNull(listFiles) || listFiles.length <= 0) {
            return files;
        }
        for (File file : listFiles) {
            if (file.isFile()) {
                // 如果是文件
                files.add(new File(dir + File.separator + file.getName()));
                continue;
            }
            // 递归获取
            loadFiles(file, files);
        }
        return files;
    }

    /**
     * 打包下载
     *
     * @param outputStream 输出流
     * @param file 文件/目录
     */
    public static void zipBatchDownload(OutputStream outputStream, File file) {
        if(Objects.isNull(outputStream) || Objects.isNull(file)) {
            return;
        }
        try(ZipOutputStream zos = new ZipOutputStream(outputStream)) {
            List<File> files = FileUtils.loadFiles(file, null);
            for (File it : files) {
                zos.putNextEntry(new ZipEntry(it.getName()));
                try(FileInputStream fis = new FileInputStream(it)) {
                    byte[] buffer = new byte[1024];
                    int red;
                    while((red = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, red);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
