package com.shamengxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shamengxin.mapper.CategoryMapper;
import com.shamengxin.entity.Category;
import com.shamengxin.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 分类(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-06-12 23:26:21
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<Category> queryByFirstLevel() {
        return categoryMapper.queryByFirstLevel();
    }

    @Override
    public Category saveAndReturn(Category category) {
        Date date = new Date();
        category.setCreatedAt(date);
        category.setUpdatedAt(date);
        categoryMapper.insert(category);
        return category;
    }
}

