package io.vicp.goradical.intellirec.struts2.action.video;

import com.alibaba.fastjson.JSON;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.VideoVo;
import io.vicp.goradical.intellirec.service.VideoService;
import io.vicp.goradical.intellirec.struts2.action.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 李浩然 on 2017/3/22.
 */
@Namespace("/videoManager")
@Action(value = "videoManagerAction")
public class VideoManagerAction extends BaseAction<VideoVo> {

    @Autowired
    private VideoService vs;

    /**
     * 页面查询请求响应方法
     */
    public void tableData() {
        TableVo<VideoVo> videoVoTableVo = vs.tableData(model);
        super.writeJson(videoVoTableVo);
    }

    /**
     * 页面跳转 Action
     * @return
     */
    @Action(value = "videoManagerAction_toVideoInfoManagerPage", results = {
            @Result(name = SUCCESS, location = "/background/video/video-info-manager.jsp")})
    public String toVideoInfoManagerPage(){
        // 查询并使用 TableVo 装载数据模型
        TableVo<VideoVo> videoVoTableVo = vs.tableData(model);
        // 将 TableVo 数据模型转换为 JSon 模型
        String jsonStr = JSON.toJSONStringWithDateFormat(videoVoTableVo,"yyyy-MM-dd HH:mm:ss");
        // 将数据放入 request 中
        getRequest().setAttribute("jsonStr",jsonStr);
        return SUCCESS;
    }

}