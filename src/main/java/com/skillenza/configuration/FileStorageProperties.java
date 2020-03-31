package com.skillenza.configuration;


import org.springframework.context.annotation.Configuration;


@Configuration(value = "file")
public class FileStorageProperties {

	private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
