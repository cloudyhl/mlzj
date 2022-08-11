package com.mlzj.hdfs.config;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class HDFSConfig {

    @Value("${hadoop.hdfs.default-fs}")
    private String hdfsPath;
    @Value("${hadoop.hdfs.hdfsName}")
    private String hdfsName;
    
    @Bean
    public org.apache.hadoop.conf.Configuration  getConfiguration(){
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", hdfsPath);
        return configuration;
    }
    @Bean
    public FileSystem getFileSystem(){
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI(hdfsPath), getConfiguration(), hdfsName);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            log.error(e.getMessage());
        }
        return fileSystem;
    }

}
