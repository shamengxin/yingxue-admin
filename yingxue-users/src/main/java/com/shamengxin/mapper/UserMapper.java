package com.shamengxin.mapper;

import com.shamengxin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author he'yu'feng
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-06-14 23:40:33
* @Entity com.shamengxin.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




