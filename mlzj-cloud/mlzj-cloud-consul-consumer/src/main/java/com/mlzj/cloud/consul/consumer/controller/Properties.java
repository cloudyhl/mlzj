package com.mlzj.cloud.consul.consumer.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "mlzj.consul")
@RefreshScope
public class Properties {

    private String message;
}
