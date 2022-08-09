package com.mlzj.es.service.impl;

import com.google.common.collect.Lists;
import com.mlzj.es.dao.TitleContentDao;
import com.mlzj.es.dto.TitleContentEntity;
import com.mlzj.es.handler.HighlightResultMapper;
import com.mlzj.es.service.ContentService;
import java.util.List;
import javax.annotation.Resource;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private TitleContentDao titleContentDao;

    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public void save(TitleContentEntity titleContentEntity) {
        titleContentDao.save(titleContentEntity);
    }

    @Override
    public List<TitleContentEntity> findContent(String queryStr) {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(queryStr, "title", "content");

        NativeSearchQuery highlightQuery = new NativeSearchQueryBuilder().withQuery(multiMatchQueryBuilder).withHighlightFields(new Field("content")
                //numOfFragments 命中后拆解的条数 fragmentSize 指定每个条目数字数上线 方分组为0时则返回整个文章
                .preTags("<span style=background:yellow>").postTags("</span>").numOfFragments(5).fragmentSize(100)).build();
        NativeSearchQuery searchQuery = new NativeSearchQuery(multiMatchQueryBuilder);
        HighlightBuilder highlightBuilder = new HighlightBuilder().highlightQuery(multiMatchQueryBuilder);
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<TitleContentEntity> search = titleContentDao.search(multiMatchQueryBuilder, pageRequest);
        titleContentDao.search(multiMatchQueryBuilder, Pageable.unpaged());
        List<TitleContentEntity> titleContentEntities = Lists.newArrayList(titleContentDao.search(multiMatchQueryBuilder));
        Page<TitleContentEntity> titleContentEntities1 = elasticsearchTemplate.queryForPage(highlightQuery, TitleContentEntity.class, new HighlightResultMapper());
        return titleContentEntities1.getContent();
    }

}
