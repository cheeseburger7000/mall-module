package com.shaohsiung.mallgoods.controller;

import com.shaohsiung.mallgoods.constant.QiniuConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadControllerTest {
    @Autowired
    private QiniuConstant qiniuConstant;

    @Test
    public void uploadPicture() {
        log.info("{}", qiniuConstant.getAccessKey());
        log.info("{}", qiniuConstant.getBucket());
    }
}
