package com.shamengxin.controller;


import com.shamengxin.entity.User;
import com.shamengxin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户(User)表控制层
 *
 * @author makejava
 * @since 2023-06-14 23:41:24
 */
@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<String, Object> users(@RequestParam(value = "page", defaultValue = "1") Integer pageNow,
                                     @RequestParam(value = "per_page", defaultValue = "5") Integer rows,
                                     @RequestParam(required = false) String id, String name, String phone) {
        log.info("分页信息 当前页:{}，每页显示记录条数:{}", pageNow, rows);
        log.info("搜索的值 id:{}，name:{}，phone:{}", id, name, phone);
        Map<String, Object> result = new HashMap<>();
        List<User> items = userService.findAllByKeyWorlds(pageNow, rows, id, name, phone);
        Long total = userService.findTotalCountsByKeyWorlds(id, name, phone);
        log.info("当前list中的总数:{}",items.size());
        log.info("符合条件的总数:{}",total);
        result.put("total_count",total);
        result.put("items",items);
        return result;
    }
}

