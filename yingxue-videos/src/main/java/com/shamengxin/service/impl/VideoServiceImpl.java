package com.shamengxin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shamengxin.entity.Video;
import com.shamengxin.service.VideoService;
import com.shamengxin.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author he'yu'feng
* @description 针对表【video(视频)】的数据库操作Service实现
* @createDate 2023-06-18 22:19:26
*/
@Service
@Transactional
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

    private VideoMapper videoMapper;

    @Autowired
    public VideoServiceImpl(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Override
    public Long findTotalCountsByKeyworlds(String id, String name, String categoryId, String uploaderName) {
        return videoMapper.findTotalCountsByKeyworlds(id,name,categoryId,uploaderName);
    }

    @Override
    public List<Video> findAllByKeyworlds(Integer page, Integer rows, String id, String name, String categoryId, String uploaderName) {

        int start = (page-1)*rows;

        return videoMapper.findAllByKeyworlds(start,rows,id,name,categoryId,uploaderName);
    }
}




