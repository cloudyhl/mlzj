package com.mlzj.alibaba.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.mlzj.alibaba.consumer.dto.ClientReqDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {

    @GetMapping("/getConsumer")
    public String getConsumer(@RequestParam String  id) {
        return "consumer" + id;
    }


    @PostMapping("/postConsumer")
    public String postConsumer(@RequestBody ClientReqDto reqDto) {
        return "consumer" + JSONObject.toJSONString(reqDto);
    }

}
