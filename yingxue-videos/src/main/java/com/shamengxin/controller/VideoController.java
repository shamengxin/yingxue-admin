package com.shamengxin.controller;

import com.shamengxin.entity.Video;
import com.shamengxin.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频(Video)表控制层
 *
 * @author makejava
 * @since 2023-06-18 22:21:49
 */
@Slf4j
@RestController
@RequestMapping("videos")
public class VideoController {

    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public Map<String,Object> videos(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                     @RequestParam(value = "per_page",defaultValue = "5") Integer rows,
                                     @RequestParam(required = false) String id,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(value = "category_id",required = false) String categoryId,
                                     @RequestParam(value = "uploader_name",required = false) String uploaderName){
        log.info("当前页面为：{}",page);
        log.info("显示的行数为：{}",rows);
        log.info("搜索条件id是否存在：{}，id为：{}", !ObjectUtils.isEmpty(id),id);
        log.info("搜索条件name是否存在：{}，name为：{}", !ObjectUtils.isEmpty(name),name);
        log.info("搜索条件categoryId是否存在：{}，categoryId为：{}", !ObjectUtils.isEmpty(categoryId),categoryId);
        log.info("搜索条件uploaderName是否存在：{}，uploaderName为：{}", !ObjectUtils.isEmpty(uploaderName),uploaderName);

        Map<String,Object> result = new HashMap<>();
        Long total = videoService.findTotalCountsByKeyworlds(id,name,categoryId,uploaderName);
        List<Video> items = videoService.findAllByKeyworlds(page,rows,id,name,categoryId,uploaderName);
        log.info("符合条件的总数为：{}",total);
        result.put("total_count",total);
        result.put("items",items);

        return result;
    }
}

