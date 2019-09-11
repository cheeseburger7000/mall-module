package com.shaohsiung.mallsearch.controller;

import com.shaohsiung.mallsearch.model.GoodsDoc;
import com.shaohsiung.mallsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private SearchService searchService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("prompt","请输入商品关键词");
        return "index";
    }

    @GetMapping(path = "/search/{keyword}")
    @ResponseBody
    public List<GoodsDoc> search(@PathVariable String keyword) {
        ModelAndView result = new ModelAndView();
        List<GoodsDoc> goodsDocList = searchService.search(keyword, 0, 4);
        // TODO 返回JSON...
        return goodsDocList;
    }
}
