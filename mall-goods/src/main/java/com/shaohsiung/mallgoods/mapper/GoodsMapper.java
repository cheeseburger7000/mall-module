package com.shaohsiung.mallgoods.mapper;

import com.shaohsiung.model.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface GoodsMapper {
    @Insert("insert into t_goods (id, name, detail, price, stock, image, create_time, category_id)"
            + "values (#{id}, #{name}, #{detail}, #{price}, #{stock}, #{image}, #{createTime}, #{categoryId})")
    @Options(useGeneratedKeys = true)
    int save(Goods goods);
}
