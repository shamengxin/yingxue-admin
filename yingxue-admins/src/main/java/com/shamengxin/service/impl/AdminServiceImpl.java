package com.shamengxin.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shamengxin.entity.Admin;
import com.shamengxin.service.AdminService;
import com.shamengxin.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author he'yu'feng
 * @description 针对表【admin】的数据库操作Service实现
 * @createDate 2023-06-08 22:59:41
 */
@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {


    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin login(Admin admin) {
        // 1.根据admin查询用户
        Admin adminDB = adminMapper.findByUserName(admin.getUsername());
        // 2.判断用户是否存在
        if(ObjectUtils.isEmpty(adminDB)){
            throw new RuntimeException("用户名错误!");
        }
        // 3.判断密码是否正确
        String password = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes(StandardCharsets.UTF_8));
        if (!StringUtils.equals(password,adminDB.getPassword())){
            throw new RuntimeException("密码输入错误!");
        }

        return adminDB;
    }
}




