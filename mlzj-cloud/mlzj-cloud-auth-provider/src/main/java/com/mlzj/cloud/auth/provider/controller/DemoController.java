package com.mlzj.cloud.auth.provider.controller;

import com.alibaba.fastjson.JSON;
import com.mlzj.cloud.auth.common.constants.AuthConstants;
import com.mlzj.cloud.auth.common.model.SimpleUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yhl
 * @date 2019/7/26
 */
@RestController
@Slf4j
public class DemoController {

    @RequestMapping("/message")
    public String getMessage(HttpServletRequest request){
        String header = request.getHeader(AuthConstants.USER_HEADER);
        SimpleUserDetail simpleUserDetail = JSON.parseObject(header, SimpleUserDetail.class);
        log.info(simpleUserDetail.toString());
        return "this is a common controller message";
    }

}
