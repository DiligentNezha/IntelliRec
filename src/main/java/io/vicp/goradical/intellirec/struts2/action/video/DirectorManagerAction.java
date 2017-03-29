package io.vicp.goradical.intellirec.struts2.action.video;

import io.vicp.goradical.intellirec.model.pmrs.vo.DirectorVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.service.DirectorService;
import io.vicp.goradical.intellirec.struts2.action.BaseAction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 李浩然 on 2017/3/28.
 */
@Namespace("/videoManager")
@Action(value = "DirectorManagerAction")
public class DirectorManagerAction extends BaseAction<DirectorVo> {

    @Autowired
    private DirectorService directorService;

    public void tableData(){
        TableVo<DirectorVo> directorVoTableVo = directorService.tableData(model);
        super.writeJson(directorVoTableVo);
    }

    @Action(value = "directorManagerAction_toDirectorInfoManagerPage", results = {
            @Result(name = SUCCESS, location = "/background/video/director-info-manager.jsp")
    })
    public String toDirectorInfoPage(){

        // TODO toDirectorInfoPage
        return SUCCESS;
    }

}
