package com.mlzj.mlzjsecurity.dao.jpacommon;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;

/**
 * @author yhl
 * @date 2018/12/12
 */
public class CommonRepositoryFactory extends JpaRepositoryFactory {

    public CommonRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata){
        return CommonRepositoryBean.class;
    }
}
