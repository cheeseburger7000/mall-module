package com.shaohsiung.mallgoods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 商品/商品分类微服务
 */
@SpringBootApplication
@MapperScan("com.shaohsiung.mallgoods.mapper")
public class MallGoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallGoodsApplication.class, args);
    }

    /**
     * queue
     * @return
     */
    @Bean(name = "messages")
    public Queue queueMessages(){
        return new Queue("topic.messages");
    }

    /**
     * exchange
     * @return
     */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    /**
     * binding queue and exchange
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.messages");
    }
}
