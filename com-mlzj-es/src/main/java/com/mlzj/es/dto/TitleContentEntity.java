package com.mlzj.es.dto;


import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author yhl
 * @date 2022/8/5
 */
@Data
@Document(indexName = "title_content")
public class TitleContentEntity {

    @Id
    private String id;

    /**
     * 业务id
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String businessId;

    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    /**
     * 正文
     */
    @Field
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

}
