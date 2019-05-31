package com.mlzj.mongodb.demo.service.impl;

import com.mlzj.mongodb.behavior.OnlineUserBehavior;
import com.mlzj.mongodb.behavior.BehaviorInterface;
import com.mlzj.mongodb.demo.model.Company;
import com.mlzj.mongodb.demo.model.OnlineUser;
import com.mlzj.mongodb.demo.model.UserBehavior;
import com.mlzj.mongodb.demo.utils.UserUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author yhl
 * @date 2019/5/24
 */
//@Service
public class UserBehaviorServiceImpl implements BehaviorInterface {

    @Resource
    private UserUtils userUtils;

    @Override
    public OnlineUserBehavior getUserBehavior() {
        OnlineUser onlineUser = userUtils.getOnlineUser();
        UserBehavior onlineUserBehavior = new UserBehavior();
        onlineUserBehavior.setAccount(onlineUser.getAccount());
        Company company = new Company();
        company.setCompanyName("1919");
        company.setCompanyTel("4009991919");
        onlineUserBehavior.setCompany(company);
        onlineUserBehavior.setUserName(onlineUser.getUserName());
        onlineUserBehavior.setDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        return onlineUserBehavior;
    }
}
