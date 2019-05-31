package com.mlzj.mongodb.demo.model;

import com.mlzj.mongodb.behavior.OnlineUserBehavior;
import lombok.Data;

import java.io.Serializable;

/**
 * 线上用户
 * @author yhl
 * @date 2019/5/24
 */
@Data
public class UserBehavior implements OnlineUserBehavior<Company>, Serializable {

    private static final long serialVersionUID = -8288612396372670579L;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 账号
     */
    private String account;

    /**
     * 公司
     */
    private Company company;


    /**
     * 访问日期
     */
    private String date;


    @Override
    public Company getSupplement() {
        return this.company;
    }
}
