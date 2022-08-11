package com.mlzj.es.service.impl;

import com.google.common.collect.Lists;
import com.mlzj.es.dao.TitleContentDao;
import com.mlzj.es.dto.TitleContentEntity;
import com.mlzj.es.handler.HighlightResultMapper;
import com.mlzj.es.service.ContentService;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
import org.elasticsearch.search.sort.SortOrder;
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
    public List<TitleContentEntity> findContent(String queryStr) throws IOException {
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(queryStr, "title", "content");

        NativeSearchQuery highlightQuery = new NativeSearchQueryBuilder().withQuery(multiMatchQueryBuilder).withHighlightFields(new Field("content")
                //numOfFragments 命中后拆解的条数 fragmentSize 指定每个条目数字数上线 方分组为0时则返回整个文章
                .preTags("<span style=background:yellow>").postTags("</span>").numOfFragments(5).fragmentSize(100)).build();
        NativeSearchQuery searchQuery = new NativeSearchQuery(multiMatchQueryBuilder);
        HighlightBuilder highlightBuilder = new HighlightBuilder().highlightQuery(multiMatchQueryBuilder);
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<TitleContentEntity> search = titleContentDao.search(multiMatchQueryBuilder, pageRequest);
        titleContentDao.search(multiMatchQueryBuilder, Pageable.unpaged());
        SearchRequest searchRequest = new SearchRequest("title_content");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        List<TitleContentEntity> titleContentEntities = Lists.newArrayList(titleContentDao.search(multiMatchQueryBuilder));
        Page<TitleContentEntity> titleContentEntities1 = elasticsearchTemplate.queryForPage(highlightQuery, TitleContentEntity.class, new HighlightResultMapper());

//        Page<TitleContentEntity> search = titleContentDao.search(multiMatchQueryBuilder, pageRequest);
//        titleContentDao.search(multiMatchQueryBuilder, Pageable.unpaged());

        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(multiMatchQueryBuilder).withPageable(pageRequest).build();
        //searchAfter 只能从第一页开始 查询每页数目 之后需要指定上一次返回的最后一条数据
        MultiMatchQueryBuilder multiMatchQueryBuilder1 = QueryBuilders.multiMatchQuery(queryStr, "content", "title");
        searchSourceBuilder.query(multiMatchQueryBuilder1);
        searchSourceBuilder.sort("sortId.keyword", SortOrder.ASC);
        searchSourceBuilder.from(0).size(100);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = elasticsearchTemplate.getClient().search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        Object[] sortValues = hits[hits.length - 1].getSortValues();
        for (Object sortValue : sortValues) {
            System.out.println(sortValue);
        }
        for (int index = 0; index < 140; index ++) {

        }
        searchSourceBuilder.searchAfter(sortValues);
        searchRequest.source(searchSourceBuilder);
        return titleContentEntities1.getContent();
    }

}
