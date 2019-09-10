package com.shaohsiung.mallgoods.service;

import com.shaohsiung.model.Goods;
import com.shaohsiung.util.IdHelper;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void addGoods() {
        Goods  latte = Goods.builder()
                .id(IdHelper.randomUUID())
                .createTime(new Date())
                .name("latte")
                .categoryId("0")
                .detail("黑咖啡+牛奶")
                .price(Money.of(Money.of(CurrencyUnit.of("CNY"), 20.0)))
                .image("http://localhost/image")
                .stock(24)
                .build();
        Goods american = Goods.builder()
                .id(IdHelper.randomUUID())
                .createTime(new Date())
                .name("american")
                .categoryId("0")
                .detail("纯黑咖啡")
                .price(Money.of(Money.of(CurrencyUnit.of("CNY"), 18.0)))
                .image("http://localhost/image")
                .stock(36)
                .build();
        Goods roosevelt10 = Goods.builder()
                .id(IdHelper.randomUUID())
                .createTime(new Date())
                .name("roosevelt10")
                .categoryId("0")
                .detail("黑啤酒roosevelt10")
                .price(Money.of(Money.of(CurrencyUnit.of("CNY"), 120.0)))
                .image("http://localhost/image")
                .stock(4)
                .build();
        Goods roosevelt7 = Goods.builder()
                .id(IdHelper.randomUUID())
                .createTime(new Date())
                .name("roosevelt7")
                .categoryId("0")
                .detail("黑啤酒roosevelt7好喝！！！")
                .price(Money.of(Money.of(CurrencyUnit.of("CNY"), 90.0)))
                .image("http://localhost/image")
                .stock(31)
                .build();
        Goods book = Goods.builder()
                .id(IdHelper.randomUUID())
                .createTime(new Date())
                .name("沉思录")
                .categoryId("0")
                .detail("沉思录一本关于哲学的书籍")
                .price(Money.of(Money.of(CurrencyUnit.of("CNY"), 12.0)))
                .image("http://localhost/image")
                .stock(102)
                .build();
        goodsService.addGoods(latte);
        goodsService.addGoods(american);
        goodsService.addGoods(roosevelt10);
        goodsService.addGoods(roosevelt7);
        goodsService.addGoods(book);
    }
}
