package com.shamengxin.service;

import com.shamengxin.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author he'yu'feng
* @description 针对表【video(视频)】的数据库操作Service
* @createDate 2023-06-18 22:19:26
*/
public interface VideoService extends IService<Video> {

    Long findTotalCountsByKeyworlds(String id, String name, String categoryId, String uploaderName);

    List<Video> findAllByKeyworlds(Integer page, Integer rows, String id, String name, String categoryId, String uploaderName);
}
