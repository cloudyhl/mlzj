package com.mlzj.hdfs.controller;

import com.alibaba.fastjson.JSONObject;
import com.mlzj.hdfs.service.HDFSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
/**
 * @author yhl
 * @date 2022/8/11
 */
@Api(value = "/hdfs", tags = "文件测试")
@Slf4j
@RestController
@RequestMapping("/hdfs")
public class HDFSController {

    @Resource
    private HDFSService service;

    /**
     * 创建的文件夹权限不够，需要设置权限问题
     * @param path
     * @return
     */

    @ApiOperation(httpMethod = "POST", value = "创建文件夹")
    @RequestMapping(value = "/mkdirFolder", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String mkdirFolder(
            @RequestBody @ApiParam(name = "JSON对象", value = "json格式对象", required = true) String path) {
        boolean target = service.mkdirFolder(path);
        return "OK";

    }

    @ApiOperation(httpMethod = "POST", value = "判断文件夹是否存在")
    @RequestMapping(value = "/existFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String existFile(
            @RequestBody @ApiParam(name = "JSON对象", value = "json格式对象", required = true) JSONObject entity) {
        boolean target = service.existFile(entity.getString("/testHdfs"));
        return "OK";
    }

    @ApiOperation(httpMethod = "POST", value = "读取目录")
    @RequestMapping(value = "/readCatalog", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String, Object>> readCatalog(
            @RequestBody @ApiParam(name = "JSON对象", value = "json格式对象", required = true) JSONObject entity) {
        List<Map<String, Object>> list = service.readCatalog("/testHdfs");
        return list;
    }

    @ApiOperation(httpMethod = "POST", value = "新建文件")
    @RequestMapping(value = "/createFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String createFile(
            @RequestBody @ApiParam(name = "JSON对象", value = "json格式对象", required = true) JSONObject entity) {
        FileInputStream inputStream = null;
        MultipartFile file = null;
        try {
            inputStream = new FileInputStream("D:\\data\\words.txt");
            file = new MockMultipartFile("test.txt", inputStream);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        }finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e.getMessage());
            }
        }
        service.createFile("/testHdfs",file);
        return "OK";
    }

    @ApiOperation(value = "读取文件内容")
    @RequestMapping(value = "/readFileContent", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String readFileContent(@RequestParam String path) {
        return service.readFileContent(path);
    }

    @ApiOperation(httpMethod = "POST", value = "文件列表")
    @RequestMapping(value = "/listFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  List<Map<String, String>> listFile(@RequestParam String path) {
        List<Map<String, String>> list = service.listFile(path);
        return list;
    }

    @ApiOperation(httpMethod = "POST", value = "文件重命名")
    @RequestMapping(value = "/renameFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String renameFile(
            @RequestBody @ApiParam(name = "JSON对象", value = "json格式对象", required = true) JSONObject entity) {
        boolean target = service.renameFile(entity.getString("oldName"),entity.getString("newName"));
        return "OK";
    }

    /**
     * 指定文件位删除成功，需要寻找原因
     * @param path
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "文件删除")
    @RequestMapping(value = "/deleteFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteFile(
            @RequestBody @ApiParam(name = "JSON对象", value = "json格式对象", required = true) String path) {
        boolean target = service.deleteFile(path);
        return "OK";
    }


    @ApiOperation(httpMethod = "POST", value = "文件拷贝")
    @RequestMapping(value = "/uploadFileCopy", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String uploadFileCopy(
            @RequestBody @ApiParam(name = "JSON对象", value = "json格式对象", required = true) JSONObject entity) {
        service.uploadFileCopy(entity.getString("path"), entity.getString("uploadPath"));
        return "OK";
    }

    @ApiOperation(httpMethod = "POST", value = "直接上传文件")
    @RequestMapping(value = "/uploadFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam String path) {
        service.uploadFile(file, path);
        return "OK";

    }


    @ApiOperation(value = "下载文件")
    @RequestMapping(value = "/downLoadFile", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void downLoadFile(@RequestParam String fileUrl, HttpServletResponse response) throws IOException {
        service.downLoadFile(fileUrl, response);
    }

}
