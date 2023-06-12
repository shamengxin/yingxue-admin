package com.shamengxin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shamengxin.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分类(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-12 23:26:21
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> queryByFirstLevel();
}

