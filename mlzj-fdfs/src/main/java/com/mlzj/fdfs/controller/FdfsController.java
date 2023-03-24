package com.mlzj.fdfs.controller;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yhl
 * @date 2023/3/23
 */
@SuppressWarnings("all")
@RestController
@Api("文件上传下载")
@Slf4j
@RequestMapping("/fdfs/file")
public class FdfsController {

    @Resource
    private FastFileStorageClient fastFileStorageClient;

    @Resource
    private FdfsWebServer fdfsWebServer;

    /**
     * 上传文件
     */
    @PostMapping("/uploadImageAndThumb")
    public List<String> uploadImageAndThumb(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        // 获取文件扩展名
        String extension = FilenameUtils.getExtension(fileName);
        // 文件元数据信息
        Set<MetaData> metaData = new HashSet<>(4);
        metaData.add(new MetaData("fileName",fileName));
        // 上传文件
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extension, metaData);
        log.info("文件上传路径:[{}]",storePath.getFullPath());
        String viewPath = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        log.info("可访问路径:[{}]",viewPath);

        extension = FilenameUtils.getExtension(viewPath);
        String xthumbPath = viewPath.replace("." + extension, "")+"_150x150."+extension;
        log.info("缩略图路径:[{}]",xthumbPath);
        List<String> result = new ArrayList<>(3);
        result.add(viewPath);
        result.add(xthumbPath);
        result.add(storePath.getFullPath());

        return result;
    }

    @PostMapping("/uploadImage")
    public List<String> uploadImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        // 获取文件扩展名
        String extension = FilenameUtils.getExtension(fileName);
        // 文件元数据信息
        Set<MetaData> metaData = new HashSet<>(4);
        metaData.add(new MetaData("fileName",fileName));

        FastImageFile fastImageFile = new FastImageFile(file.getInputStream(), file.getSize(), extension, metaData);
        // 上传文件
        StorePath storePath = fastFileStorageClient.uploadImage(fastImageFile);
        log.info("文件上传路径:[{}]",storePath.getFullPath());
        String viewPath = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        log.info("可访问路径:[{}]",viewPath);
        List<String> result = new ArrayList<>(3);
        result.add(viewPath);
        result.add(storePath.getFullPath());

        return result;
    }


    @PostMapping("/uploadFile")
    public List<String> uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        // 获取文件扩展名
        String extension = FilenameUtils.getExtension(fileName);
        // 文件元数据信息
        Set<MetaData> metaData = new HashSet<>(4);
        metaData.add(new MetaData("fileName",fileName));
        // 上传文件
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extension, metaData);
        log.info("文件上传路径:[{}]",storePath.getFullPath());
        String viewPath = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
        log.info("可访问路径:[{}]",viewPath);
        List<String> result = new ArrayList<>(3);
        result.add(viewPath);
        result.add(storePath.getFullPath());
        return result;
    }

    /**
     * 下载文件
     */
    @GetMapping("/download")
    public void downloadFile(String filePath, HttpServletResponse response) throws IOException {
        log.info("需要下载的文件:[{}]",filePath);
        String group = filePath.substring(0, filePath.indexOf("/"));
        String path = filePath.substring(filePath.indexOf("/") + 1);
        Set<MetaData> metadata = fastFileStorageClient.getMetadata(group, path);
        String fileName = metadata.iterator().next().getValue();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, new DownloadByteArray());
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, Charsets.UTF_8.displayName()));
        IOUtils.write(bytes,response.getOutputStream());
    }


    /**
     * 下载文件
     */
    @DeleteMapping("/deleteFile")
    public void deleteFile(String filePath) throws IOException {
        log.info("fastDFS开始删除文件{}", filePath);
        fastFileStorageClient.deleteFile(filePath);
    }



    /**
     * 预览图片
     */
    @GetMapping("/viewImage")
    public void viewImage(String filePath, HttpServletResponse response) throws IOException {
        String group = filePath.substring(0, filePath.indexOf("/"));
        String path = filePath.substring(filePath.indexOf("/") + 1);
        Set<MetaData> metadata = fastFileStorageClient.getMetadata(group, path);
        String fileName = metadata.iterator().next().getValue();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, new DownloadByteArray());
        IOUtils.write(bytes,response.getOutputStream());
    }
}
