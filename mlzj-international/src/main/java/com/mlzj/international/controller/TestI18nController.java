package com.mlzj.international.controller;

import com.mlzj.international.utils.I18nMessageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@Slf4j
@RestController
@RequestMapping("/i18n")
public class TestI18nController {


    @ApiOperation(value = "获取i18n消息")
    @GetMapping("/getI18nMessage")
    public String getI18nMessage() {
        return I18nMessageUtils.message("500");
    }

}
