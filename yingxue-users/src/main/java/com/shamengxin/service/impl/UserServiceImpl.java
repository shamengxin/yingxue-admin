package com.shamengxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shamengxin.entity.User;
import com.shamengxin.service.UserService;
import com.shamengxin.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author he'yu'feng
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-06-14 23:40:34
*/
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




