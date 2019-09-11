package com.shaohsiung.mallgoods.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuConstant {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String path;
}
