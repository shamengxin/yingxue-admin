package com.shamengxin.service;

import com.shamengxin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author he'yu'feng
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-06-14 23:40:34
*/
public interface UserService extends IService<User> {

    List<User> findAllByKeyWorlds(Integer pageNow, Integer rows, String id, String name, String phone);

    Long findTotalCountsByKeyWorlds(String id, String name, String phone);
}
