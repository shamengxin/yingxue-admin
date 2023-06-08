package com.shamengxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shamengxin.entity.Admin;
import com.shamengxin.service.AdminService;
import com.shamengxin.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author he'yu'feng
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-06-08 22:59:41
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




