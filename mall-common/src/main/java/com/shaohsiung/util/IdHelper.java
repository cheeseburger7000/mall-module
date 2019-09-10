package com.shaohsiung.util;

import java.util.UUID;

// TODO 使用分布式ID生成策略替代
public class IdHelper {
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
