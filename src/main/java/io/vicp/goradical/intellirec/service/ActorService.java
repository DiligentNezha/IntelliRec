package io.vicp.goradical.intellirec.service;

import io.vicp.goradical.intellirec.model.pmrs.Actor;
import io.vicp.goradical.intellirec.model.pmrs.vo.ActorVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;

/**
 * Created by 李浩然 on 2017/3/23.
 */
public interface ActorService extends BaseService<Actor>{

    /**
     * 根据演员的视图模型返回演员数据的表格视图模型
     * @param actorVo   演员的视图模型
     * @return  演员数据的表格模型
     */
    TableVo<ActorVo> tableData(ActorVo actorVo);
}
