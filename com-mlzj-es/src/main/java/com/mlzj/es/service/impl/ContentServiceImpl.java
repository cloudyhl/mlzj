package com.mlzj.es.service.impl;

import com.google.common.collect.Lists;

import com.mlzj.es.dao.TitleContentDao;
import com.mlzj.es.dto.TitleContentEntity;
import com.mlzj.es.service.ContentService;
import java.util.List;
import javax.annotation.Resource;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private TitleContentDao titleContentDao;

    @Override
    public void save(TitleContentEntity titleContentEntity) {
        titleContentDao.save(titleContentEntity);
    }

    @Override
    public List<TitleContentEntity> findContent(String queryStr) {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(queryStr, "title", "content");

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<TitleContentEntity> search = titleContentDao.search(multiMatchQueryBuilder, pageRequest);
        titleContentDao.search(multiMatchQueryBuilder, Pageable.unpaged());
        return Lists.newArrayList(titleContentDao.search(multiMatchQueryBuilder));
    }


}
