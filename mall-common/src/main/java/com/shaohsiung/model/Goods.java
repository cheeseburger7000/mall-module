package com.shaohsiung.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods implements Serializable {
    private String id;
    private String name;
    private String detail;
    private Money price;
    private Integer stock;
    private String image;
    private Date createTime;
    private String categoryId;
}
