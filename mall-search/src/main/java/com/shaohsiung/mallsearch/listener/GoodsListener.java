package com.shaohsiung.mallsearch.listener;

import com.shaohsiung.mallsearch.model.GoodsDoc;
import com.shaohsiung.mallsearch.repository.GoodsDocRepository;
import com.shaohsiung.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

@Component
@Slf4j
public class GoodsListener {
    @Autowired
    private GoodsDocRepository goodsDocRepository;

    @RabbitListener(queues = "topic.messages")
    public void addGoodsToElasticSearch(byte[] bytes) throws Exception{
        //字节码转化为对象
        Goods goods=(Goods) getObjectFromBytes(bytes);
        log.info("获取消息：{}", goods);

        GoodsDoc goodsDoc = new GoodsDoc();
        BeanUtils.copyProperties(goods, goodsDoc);
        log.info("索引库新增文档：{}", goodsDoc);
        goodsDocRepository.save(goodsDoc);
    }

    // 字节码转化为对象
    public  Object getObjectFromBytes(byte[] objBytes) throws Exception {
        if (objBytes == null || objBytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }
}