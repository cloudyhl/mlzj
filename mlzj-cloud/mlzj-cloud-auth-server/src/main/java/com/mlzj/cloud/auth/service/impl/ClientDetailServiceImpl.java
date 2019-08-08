package com.mlzj.cloud.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlzj.cloud.auth.dao.ClientDao;
import com.mlzj.cloud.auth.model.SimpleClientDetail;
import com.mlzj.cloud.auth.service.ClientService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2019/7/25
 */
@Service
public class ClientDetailServiceImpl extends ServiceImpl<ClientDao,SimpleClientDetail> implements ClientService {


    @Resource
    private ClientDao clientDao;


    @Override
    public ClientDetails loadClientByClientId(String s) {
        return null;
    }

    @Override
    public void saveSimpleClientDetail(SimpleClientDetail simpleClientDetail) {
        super.save(simpleClientDetail);
    }
}
