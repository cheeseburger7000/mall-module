package com.shaohsiung.mallsearch.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
@JsonComponent
public class MoneyDeserializer extends StdDeserializer<Money> {
    protected MoneyDeserializer() {
        super(Money.class);
    }

    @Override
    public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        log.info("执行反序列化操作1...{}", p.getDecimalValue());
        return Money.of(CurrencyUnit.of("CNY"), p.getDecimalValue());
    }
}
