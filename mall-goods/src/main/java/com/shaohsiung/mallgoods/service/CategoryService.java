package com.shaohsiung.mallgoods.service;

import com.shaohsiung.mallgoods.mapper.CategoryMapper;
import com.shaohsiung.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 获取所有商品分类
     * @return 商品分类列表
     */
    public List<Category> getCategoryList() {
        return categoryMapper.findAllCategories();
    }
}
