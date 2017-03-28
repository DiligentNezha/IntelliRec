package io.vicp.goradical.intellirec.service.impl;

import io.vicp.goradical.intellirec.dao.VideoDao;
import io.vicp.goradical.intellirec.model.pmrs.Video;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.VideoVo;
import io.vicp.goradical.intellirec.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/6.
 */
@Service("videoService")
public class VideoServiceImpl extends BaseServiceImpl<Video> implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public TableVo<VideoVo> tableData(VideoVo videoVo) {
        TableVo<VideoVo> videoVoTableVo = new TableVo<>();
        String hql = "from Video v";
        Map<String, Object> params = new HashMap();
        // TODO 参数置入
        addWhere(videoVo, hql, params);
        List<Video> videoList = videoDao.findEntityByHQL(hql, params, videoVo.getPage(), videoVo.getRows());
        List<VideoVo> videoVoList = new ArrayList<>();
        for (Video video: videoList) {
            VideoVo videoVoTemp = new VideoVo();
            videoVoTemp.setRows(videoVo.getRows());
            videoVoTemp.setPage(videoVo.getPage());
            BeanUtils.copyProperties(video, videoVoTemp);
            videoVoList.add(videoVoTemp);
        }
        videoVoTableVo.setRows(videoVoList);
        String countHql = "select count(*) " + hql;
        videoVoTableVo.setRecordsTotal((Long) videoDao.uniqueResult(countHql, params));
        return videoVoTableVo;
    }

    private String addWhere(VideoVo videoVo, String hql, Map<String, Object> params) {

        // TODO 视频条件查询参数置入方法

        return null;
    }

}
