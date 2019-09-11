package com.shaohsiung.mallgoods.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 图片上传消息提示
 */
@Data
@Builder
public class Result {
    private String path;
    private Integer status;
    private String message;
}
