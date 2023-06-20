package com.shamengxin.controller;

import cn.hutool.json.JSONUtil;
import com.shamengxin.constants.RedisPrefix;
import com.shamengxin.dto.AdminDTO;
import com.shamengxin.entity.Admin;
import com.shamengxin.service.AdminService;
import com.shamengxin.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class AdminController {

    private AdminService adminService;

    private StringRedisTemplate redisTemplate;

    @Autowired
    public AdminController(AdminService adminService, StringRedisTemplate redisTemplate) {
        this.adminService = adminService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 登出
     *
     * @param token
     */
    @DeleteMapping("/tokens/{token}")
    public void exit(@PathVariable("token") String token) {
        log.info("当前token信息：{}", token);
        redisTemplate.delete(RedisPrefix.TOKEN_KEY + token);
    }

    /**
     * 用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("/admin-user")
    public AdminDTO admin(String token) {
        log.info("当前token信息：{}", token);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        String adminJson = redisTemplate.opsForValue().get(RedisPrefix.TOKEN_KEY + token);
        Admin admin = JSONUtil.toBean(adminJson, Admin.class);
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(admin, adminDTO);
        return adminDTO;
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

        redisTemplate.opsForValue().set(RedisPrefix.TOKEN_KEY + token, JSONUtil.toJsonStr(adminDB), 30, TimeUnit.MINUTES);

        result.put("token", token);
        return result;

    }

}
