package io.vicp.goradical.intellirec.struts2.action.video;

import io.vicp.goradical.intellirec.model.common.Log;
import io.vicp.goradical.intellirec.model.pmrs.vo.ActorVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.service.ActorService;
import io.vicp.goradical.intellirec.struts2.action.BaseAction;
import org.apache.logging.log4j.LogManager;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 李浩然 on 2017/3/23.
 */
@Namespace("/videoManager")
@Action(value = "actorManagerAction")
public class ActorManagerAction extends BaseAction<ActorVo> {

    @Autowired
    private ActorService actorService;

    public void tableData(){
        TableVo<ActorVo> actorVoTableVo = actorService.tableData(model);
        super.writeJson(actorVoTableVo);
    }

    /**
     * 页面跳转 Action
     * @return
     */
    @Action(value = "actorManagerAction_toActorInfoManagerPage", results = {
            @Result(name = SUCCESS, location = "/background/video/actor-info-manager.jsp")})
    public String toActorInfoManagerPage(){

        // TODO Actor 页面跳转 Action
        return SUCCESS;
    }
}
