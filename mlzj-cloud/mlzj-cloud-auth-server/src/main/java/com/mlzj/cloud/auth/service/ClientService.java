package com.mlzj.cloud.auth.service;

import com.mlzj.cloud.auth.model.SimpleClientDetail;
import org.springframework.security.oauth2.provider.ClientDetailsService;

/**
 * @author yhl
 * @date 2019/7/25
 */
public interface ClientService extends ClientDetailsService {

    /**
     * 保存simpleClientDetail
     * @param simpleClientDetail 客户端信息
     */
    void saveSimpleClientDetail(SimpleClientDetail simpleClientDetail);

}
