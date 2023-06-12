package com.shamengxin.controller;


import com.shamengxin.entity.Category;
import com.shamengxin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 分类(Category)表控制层
 *
 * @author makejava
 * @since 2023-06-12 23:26:21
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> categories(){
        return categoryService.queryByFirstLevel();
    }
}

