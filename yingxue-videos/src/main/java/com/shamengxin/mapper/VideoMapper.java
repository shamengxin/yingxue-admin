package com.shamengxin.mapper;

import com.shamengxin.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author he'yu'feng
* @description 针对表【video(视频)】的数据库操作Mapper
* @createDate 2023-06-18 22:19:26
* @Entity com.shamengxin.entity.Video
*/
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    Long findTotalCountsByKeyworlds(String id, String name, String categoryId, String uploaderName);

    List<Video> findAllByKeyworlds(int start, Integer rows, String id, String name, String categoryId, String uploaderName);
}




