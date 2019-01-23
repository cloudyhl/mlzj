package com.mlzj.jpa.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;

/**
 * @author yhl
 * @date 2018/12/12
 */
@NoRepositoryBean
public class CommonRepositoryBean<T,ID> extends SimpleJpaRepository<T,ID> implements JpaRepository<T,ID> {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public CommonRepositoryBean(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }
    public CommonRepositoryBean(final JpaEntityInformation<T, ?> entityInformation, final EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }
}
