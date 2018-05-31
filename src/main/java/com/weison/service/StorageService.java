package com.weison.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 文件上传类接口
 */
public interface StorageService {

    /**
     * 初始化，创建文件夹
     */
    void init();

    /**
     * 存储文件
     * @param file 文件
     */
    void store(MultipartFile file);

    /**
     * 加载所有文件
     * @return Path
     */
    Stream<Path> loadAll();

    /**
     * 加载一个文件
     * @param filename 文件路径
     * @return Path
     */
    Path load(String filename);

    /**
     * 下载文件
     * @param filename 文件路径
     * @return Resource
     */
    Resource loadAsResource(String filename);

    /**
     * 删除所有文件
     */
    void deleteAll();

}
