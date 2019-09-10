package com.shaohsiung.mallsearch.service;

import com.shaohsiung.mallsearch.model.GoodsDoc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {
    @Autowired
    private SearchService searchService;

    @Test
    public void search() {
        List<GoodsDoc> goodsDocList = searchService.search("roosevelt", 0, 5);
        log.info("{}", goodsDocList);
    }
}
