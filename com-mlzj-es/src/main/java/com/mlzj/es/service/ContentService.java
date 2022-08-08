package com.mlzj.es.service;


import com.mlzj.es.dto.TitleContentEntity;
import java.util.List;

/**
 * @author yhl
 * @date 2022/8/5
 */
public interface ContentService {

    void save(TitleContentEntity titleContentEntity);


    List<TitleContentEntity> findContent(String queryStr);

}
