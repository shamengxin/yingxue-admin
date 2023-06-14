package com.shamengxin.controller;


import com.shamengxin.entity.Category;
import com.shamengxin.service.CategoryService;
import com.shamengxin.utils.JSONUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * 分类(Category)表控制层
 *
 * @author makejava
 * @since 2023-06-12 23:26:21
 */
@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 删除类别
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        log.info("删除的类别id：{}",id);
        categoryService.removeById(id);

    }

    /**
     * 添加类别
     * @param category
     * @return
     */
    @PostMapping
    public Category save(@RequestBody Category category){
        log.info("保存的类别信息:{}", JSONUtils.writeJson(category));
        Category categoryDB = categoryService.saveAndReturn(category);
        return categoryDB;
    }

    /**
     * 更新类别
     * @param id
     * @param category
     * @return
     */
    @PatchMapping("/{id}")
    public Category update(@PathVariable("id") Integer id, @RequestBody Category category) {
        log.info("更新的类别id：{}", id);
        log.info("更新的类别信息:{}", JSONUtils.writeJson(category));
        category.setId(id);
        boolean success = categoryService.updateById(category);
        if (BooleanUtils.isFalse(success)){
            throw new RuntimeException("更新失败，请求联系管理员。");
        }
        return categoryService.getById(id);
    }

    /**
     * 类别列表
     *
     * @return
     */
    @GetMapping
    public List<Category> categories() {
        return categoryService.queryByFirstLevel();
    }

}

