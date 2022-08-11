package com.mlzj.hdfs.service.impl;

import com.mlzj.hdfs.service.HDFSService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yhl
 * @date 2022/8/11
 */
@Slf4j
@Service
public class HDFSServiceImpl implements HDFSService {

    private static final int bufferSize = 1024 * 1024 * 64;

    @Resource
    private FileSystem fileSystem;

    @Override
    public boolean mkdirFolder(String path) {
        // TODO Auto-generated method stub
        boolean target = false;
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        if (existFile(path)) {
            return true;
        }
        Path src = new Path(path);
        try {
            target = fileSystem.mkdirs(src);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
        return target;
    }

    @Override
    public boolean existFile(String path) {
        // TODO Auto-generated method stub
        boolean target = false;

        if (StringUtils.isEmpty(path)) {
            return target;
        }
        Path src = new Path(path);
        try {
            target = fileSystem.exists(src);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
        return target;
    }

    @Override
    public List<Map<String, Object>> readCatalog(String path) {
        // TODO Auto-generated method stub
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }

        // 目标路径
        Path newPath = new Path(path);
        FileStatus[] statusList = null;
        try {
            statusList = fileSystem.listStatus(newPath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
        List<Map<String, Object>> list = new ArrayList<>();
        if (null != statusList && statusList.length > 0) {
            for (FileStatus fileStatus : statusList) {
                Map<String, Object> map = new HashMap<>();
                map.put("filePath", fileStatus.getPath());
                map.put("fileStatus", fileStatus.toString());
                list.add(map);
            }
            return list;
        } else {
            return null;
        }

    }

    @Override
    public void createFile(String path, MultipartFile file) {
        // TODO Auto-generated method stub
        if (StringUtils.isEmpty(path)) {
            return;
        }
        String fileName = file.getName();
        Path newPath = new Path(path + "/" + fileName);
        // 打开一个输出流
        FSDataOutputStream outputStream;
        try {
            outputStream = fileSystem.create(newPath);
            outputStream.write(file.getBytes());
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
    }

    @Override
    public String readFileContent(String path) {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        // 目标路径
        Path srcPath = new Path(path);
        FSDataInputStream inputStream = null;
        try {
            inputStream = fileSystem.open(srcPath);
            // 防止中文乱码
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String lineTxt = "";
            while ((lineTxt = reader.readLine()) != null) {
                sb.append(lineTxt);
            }
        }catch(Exception e){
            log.error(e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e.getMessage());
            }
        }
        return sb.toString();
    }

    @Override
    public List<Map<String, String>> listFile(String path) {
        // TODO Auto-generated method stub
        List<Map<String, String>> returnList = new ArrayList<>();
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        // 目标路径
        Path srcPath = new Path(path);
        // 递归找到所有文件
        try{
            RemoteIterator<LocatedFileStatus> filesList = fileSystem.listFiles(srcPath, true);
            while (filesList.hasNext()) {
                LocatedFileStatus next = filesList.next();
                String fileName = next.getPath().getName();
                Path filePath = next.getPath();
                Map<String, String> map = new HashMap<>();
                map.put("fileName", fileName);
                map.put("filePath", filePath.toString());
                returnList.add(map);
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return returnList;
    }

    @Override
    public boolean renameFile(String oldName, String newName) {
        // TODO Auto-generated method stub
        boolean target = false;
        if (StringUtils.isEmpty(oldName) || StringUtils.isEmpty(newName)) {
            return false;
        }
        // 原文件目标路径
        Path oldPath = new Path(oldName);
        // 重命名目标路径
        Path newPath = new Path(newName);
        try{
            target = fileSystem.rename(oldPath, newPath);
        }catch(Exception e){
            log.error(e.getMessage());
        }

        return target;
    }

    @Override
    public boolean deleteFile(String path) {
        // TODO Auto-generated method stub
        boolean target = false;
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        if (!existFile(path)) {
            return false;
        }
        Path srcPath = new Path(path);
        try{
            target = fileSystem.delete(srcPath, true);
        }catch(Exception e){
            log.error(e.getMessage());
        }

        return target;

    }

    @Override
    public void uploadFileCopy(String path, String uploadPath) {
        // TODO Auto-generated method stub
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(uploadPath)) {
            return;
        }
        // 上传路径
        Path clientPath = new Path(path);
        // 目标路径
        Path serverPath = new Path(uploadPath);

        // 调用文件系统的文件复制方法，第一个参数是否删除原文件true为删除，默认为false
        try {
            fileSystem.copyFromLocalFile(false, clientPath, serverPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }

    }

    @Override
    public void downloadFile(String path, String downloadPath) {
        // TODO Auto-generated method stub
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(downloadPath)) {
            return;
        }
        // 上传路径
        Path clientPath = new Path(path);
        // 目标路径
        Path serverPath = new Path(downloadPath);

        // 调用文件系统的文件复制方法，第一个参数是否删除原文件true为删除，默认为false
        try {
            fileSystem.copyToLocalFile(false, clientPath, serverPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }

    }

    @Override
    public void copyFile(String sourcePath, String targetPath) {
        // TODO Auto-generated method stub
        if (StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(targetPath)) {
            return;
        }
        // 原始文件路径
        Path oldPath = new Path(sourcePath);
        // 目标路径
        Path newPath = new Path(targetPath);

        FSDataInputStream inputStream = null;
        FSDataOutputStream outputStream = null;
        try {
            try{
                inputStream = fileSystem.open(oldPath);
                outputStream = fileSystem.create(newPath);

                IOUtils.copyBytes(inputStream, outputStream, bufferSize, false);
            }catch(Exception e){
                log.error(e.getMessage());
            }
        } finally {
            try{
                inputStream.close();
                outputStream.close();
            }catch(Exception e){
                log.error(e.getMessage());
            }

        }

    }

    @Override
    public byte[] openFileToBytes(String path) {
        // TODO Auto-generated method stub
        byte[] bytes= null;
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        // 目标路径
        Path srcPath = new Path(path);
        try {
            FSDataInputStream inputStream = fileSystem.open(srcPath);
            bytes = IOUtils.readFullyToByteArray(inputStream);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return bytes;

    }

    @Override
    public BlockLocation[] getFileBlockLocations(String path) {
        // TODO Auto-generated method stub
        BlockLocation[] blocks = null;
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (!existFile(path)) {
            return null;
        }
        // 目标路径
        Path srcPath = new Path(path);
        try{
            FileStatus fileStatus = fileSystem.getFileStatus(srcPath);
            blocks = fileSystem.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return blocks;

    }

    @Override
    public void uploadFile(MultipartFile file, String path) {
        if (StringUtils.isEmpty(path)) {
            return;
        }

        String fileName = file.getOriginalFilename();
        Path newPath = new Path(path + "/" + fileName);

        // 打开一个输出流
        FSDataOutputStream outputStream;
        try {
            long l = System.currentTimeMillis();
            InputStream inputStream = file.getInputStream();
            outputStream = fileSystem.create(newPath);
            int len;
            byte[] b = new byte[102400];
            while ((len = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
            System.out.println( System.currentTimeMillis() - l);
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }
    }

    @Override
    public void downLoadFile(String fileUrl, HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        if (!existFile(fileUrl)) {
            return;
        }
        // 目标路径
        // 目标路径
        Path srcPath = new Path(fileUrl);
        FSDataInputStream inputStream = null;
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //弹出下载对话框的文件名，不能为中文，中文请自行编码
            String[] split = fileUrl.split("/");
            String fileName = split[split.length - 1];
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            inputStream = fileSystem.open(srcPath);
            int len;
            byte[] b = new byte[102400];
            while ((len = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
            outputStream.flush();

        }catch(Exception e){
            log.error(e.getMessage());
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e.getMessage());
            }
        }
    }

}
