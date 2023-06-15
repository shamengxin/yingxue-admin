package com.shamengxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shamengxin.entity.User;
import com.shamengxin.service.UserService;
import com.shamengxin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author he'yu'feng
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2023-06-14 23:40:34
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAllByKeyWorlds(Integer pageNow, Integer rows, String id, String name, String phone) {
        Integer start = (pageNow - 1) * rows;
        return userMapper.findAllByKeyWorlds(start, rows, id, name, phone);

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotalCountsByKeyWorlds(String id, String name, String phone) {
        return userMapper.findTotalCountsByKeyWorlds(id, name, phone);
    }
}




