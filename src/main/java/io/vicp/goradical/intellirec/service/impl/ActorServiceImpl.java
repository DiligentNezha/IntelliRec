package io.vicp.goradical.intellirec.service.impl;

import io.vicp.goradical.intellirec.dao.ActorDao;
import io.vicp.goradical.intellirec.model.pmrs.Actor;
import io.vicp.goradical.intellirec.model.pmrs.vo.ActorVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.service.ActorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李浩然 on 2017/3/23.
 */
@Service("actorService")
public class ActorServiceImpl extends BaseServiceImpl<Actor> implements ActorService {

    @Autowired
    private ActorDao actorDao;

    @Override
    public TableVo<ActorVo> tableData(ActorVo actorVo) {

        TableVo<ActorVo> actorVoTableVo = new TableVo<>();
        String hql = "from Actor a";
        Map<String, Object> params = new HashMap();
        addWhere(actorVo, hql, params);
        List<Actor> actorList = actorDao.findEntityByHQL(hql, params, actorVo.getPage(), actorVo.getRows());
        List<ActorVo> actorVoList = new ArrayList<>();
        for (Actor actor : actorList) {
            ActorVo actorVoTemp = new ActorVo();
            actorVoTemp.setPage(actorVo.getPage());
            actorVoTemp.setRows(actorVo.getRows());
            BeanUtils.copyProperties(actor, actorVoTemp);
            actorVoList.add(actorVoTemp);
        }
        String countHql = "select count(*) " + hql;
        actorVoTableVo.setRecordsTotal((Long) actorDao.uniqueResult(countHql));
        actorVoTableVo.setRows(actorVoList);
        return actorVoTableVo;
    }


    private String addWhere(ActorVo actorVo, String hql, Map<String, Object> params) {

        // TODO 演员条件查询参数置入方法

        return null;
    }
}
