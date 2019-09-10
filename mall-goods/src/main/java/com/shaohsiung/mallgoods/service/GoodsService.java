package com.shaohsiung.mallgoods.service;

import com.shaohsiung.mallgoods.mapper.GoodsMapper;
import com.shaohsiung.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Slf4j
@Service
@Transactional
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 管理员添加商品
     * @param goods
     */
    public void addGoods(Goods goods) {
        goodsMapper.save(goods);
        log.info("保存商品到数据库：{}", goods);

        // 发送消息到消息队列
        try {
            byte[] bytes = getBytesFromObject(goods);
            rabbitTemplate.convertAndSend("exchange", "topic.messages", bytes);
            log.info("发送消息：{}", bytes);
        } catch (Exception e) {
            e.printStackTrace(); // TODO 异常处理
        }
    }

    // 将对象转化为字节数组
    public  byte[] getBytesFromObject(Serializable obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        return bo.toByteArray();
    }
}
