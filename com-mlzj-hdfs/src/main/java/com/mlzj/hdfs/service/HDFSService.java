package com.mlzj.hdfs.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.hadoop.fs.BlockLocation;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yhl
 * @date 2022/8/11
 */
public interface HDFSService {

    /**
     * HDFS 文件夹创建
     * @param path
     * @return
     */
    public boolean mkdirFolder(String path);

    /**
     * HDFS 文件是否存在
     * @param path
     * @return
     */
    public boolean existFile(String path);

    /**
     * HDFS 读取目录信息
     * @param path
     * @return
     */
    public List<Map<String, Object>> readCatalog(String path);

    /**
     * HDFS 创建文件
     * @param path
     * @param file
     */
    public void createFile(String path, MultipartFile file);

    /**
     * HDFS 读取文件内容
     * @param path
     * @return
     */
    public String readFileContent(String path);

    /**
     * HDFS 读完文件列表
     * @param path
     * @return
     */
    public List<Map<String, String>> listFile(String path);

    /**
     * HDFS 文件重命名
     * @param oldName
     * @param newName
     * @return
     */
    public boolean renameFile(String oldName, String newName);

    /**
     * HDFS 文件删除
     * @param path
     * @return
     */
    public boolean deleteFile(String path);

    /**
     * HDFS 文件上传
     * @param path
     * @param uploadPath
     */
    public void uploadFileCopy(String path, String uploadPath);

    /**
     * HDFS 文件下载
     * @param path
     * @param downloadPath
     */
    public void downloadFile(String path, String downloadPath);

    /**
     * HDFS 文件复制
     * @param sourcePath
     * @param targetPath
     */
    public void copyFile(String sourcePath, String targetPath);


    /**
     * HDFS 读取指定文件 返回字节数组
     * @param path
     * @return
     */
    public byte[] openFileToBytes(String path);

    /**
     * HDFS 获取指定文件 BlockLocation信息
     * @param path
     * @return
     * @throws Exception
     */
    public BlockLocation[] getFileBlockLocations(String path);

    void uploadFile(MultipartFile file, @RequestParam String path);


    void downLoadFile(String fileUrl, HttpServletResponse response) throws IOException;

}
