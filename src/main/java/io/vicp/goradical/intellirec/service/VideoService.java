package io.vicp.goradical.intellirec.service;

import io.vicp.goradical.intellirec.model.pmrs.Video;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.VideoVo;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface VideoService extends BaseService<Video>{


    /**
     * 根据视频的视图模型返回视频数据的表格模型
     * @param videoVo   视频视图模型
     * @return  视频数据表格模型
     */
    TableVo<VideoVo> tableData(VideoVo videoVo);

}
