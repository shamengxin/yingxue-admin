package com.shamengxin.service;

import com.shamengxin.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
* @author he'yu'feng
* @description 针对表【admin】的数据库操作Service
* @createDate 2023-06-08 22:59:41
*/
public interface AdminService extends IService<Admin> {

    /**
     * 登录方法
     * @param admin
     * @return
     */
    Admin login(Admin admin);
}
