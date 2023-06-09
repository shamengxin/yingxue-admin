package com.shamengxin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shamengxin.entity.Category;

import java.util.List;

/**
 * 分类(Category)表服务接口
 *
 * @author makejava
 * @since 2023-06-12 23:26:21
 */
public interface CategoryService extends IService<Category> {

    /**
     * 类别列表
     * @return
     */
    List<Category> queryByFirstLevel();

    Category saveAndReturn(Category category);
}

