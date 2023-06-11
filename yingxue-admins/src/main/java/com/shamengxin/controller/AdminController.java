package com.shamengxin.controller;

import com.shamengxin.entity.Admin;
import com.shamengxin.service.AdminService;
import com.shamengxin.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class AdminController {

    private AdminService adminService;

    private RedisTemplate redisTemplate;

    @Autowired
    public AdminController(AdminService adminService, RedisTemplate redisTemplate) {
        this.adminService = adminService;
        this.redisTemplate = redisTemplate;
    }


    /**
     * 登录
     *
     * @param admin
     * @return
     */
    @PostMapping("/tokens")
    public Map<String, String> tockens(@RequestBody Admin admin, HttpSession session) {

        Map<String, String> result = new HashMap<>();
        // 1.接收参数
        log.info("接收的admin对象为：{}", JSONUtils.writeJson(admin));
        // 2.调用service方法实现验证判断，以及生成tocken信息
        Admin adminDB = adminService.login(admin);
        // 3.登录成功返回tocken喜喜
        String token = session.getId();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(token, adminDB, 30, TimeUnit.MINUTES);

        result.put("token",token);
        return result;

    }

}
