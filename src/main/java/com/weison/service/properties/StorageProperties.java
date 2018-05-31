package com.weison.service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 文件上传服务属性配置烦类
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
