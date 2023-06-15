package com.shamengxin.mapper;

import com.shamengxin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author he'yu'feng
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-06-14 23:40:33
* @Entity com.shamengxin.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> findAllByKeyWorlds(Integer start, Integer rows, String id, String name, String phone);

    Long findTotalCountsByKeyWorlds(String id, String name, String phone);
}




