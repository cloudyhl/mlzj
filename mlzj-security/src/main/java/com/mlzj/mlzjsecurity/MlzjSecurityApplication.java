package com.mlzj.mlzjsecurity;

import com.mlzj.mlzjsecurity.dao.jpacommon.CommonRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author yhl
 * @date 2018/12/12
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.mlzj.mlzjsecurity",repositoryFactoryBeanClass = CommonRepositoryFactoryBean.class)
public class MlzjSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjSecurityApplication.class, args);
    }
}
