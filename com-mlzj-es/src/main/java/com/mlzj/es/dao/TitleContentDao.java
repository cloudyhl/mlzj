package com.mlzj.es.dao;

import com.mlzj.es.dto.TitleContentEntity;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TitleContentDao extends ElasticsearchRepository<TitleContentEntity, String> {


    List<TitleContentEntity> findByContentOrTitle(String queryContent, String queryTitle);


}
