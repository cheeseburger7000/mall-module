package com.shaohsiung.mallsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shaohsiung.mallsearch.support.MoneyDeserializer;
import com.shaohsiung.mallsearch.support.MoneySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "goods", type = "doc", shards = 1, replicas = 0, refreshInterval = "-1")
public class GoodsDoc {
    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String detail;

    @JsonDeserialize(using = MoneyDeserializer.class)
    @JsonSerialize(using = MoneySerializer.class)
    private Money price;

    private Integer stock;

    private String image;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    private String categoryId;
}
