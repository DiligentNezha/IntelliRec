package io.vicp.goradical.intellirec.service.impl;

import io.vicp.goradical.intellirec.dao.DirectorDao;
import io.vicp.goradical.intellirec.model.pmrs.Director;
import io.vicp.goradical.intellirec.model.pmrs.vo.DirectorVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.VideoVo;
import io.vicp.goradical.intellirec.service.DirectorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李浩然 on 2017/3/28.
 */
@Service(value = "directorService")
public class DirectorServiceImpl extends BaseServiceImpl<Director> implements DirectorService {

    @Autowired
    private DirectorDao directorDao;

    @Override
    public TableVo<DirectorVo> tableData(DirectorVo directorVo) {
        TableVo<DirectorVo> directorVoTableVo = new TableVo<>();
        String hql = "from Director";
        HashMap<String, Object> paamer = new HashMap<>();
        addWhere(directorVo, hql, paamer);
        List<Director> directorlist = directorDao.findEntityByHQL(hql, paamer, directorVo.getPage(), directorVo.getRows());
        List<DirectorVo> directorVoList = new ArrayList<>();
        for (Director director: directorlist) {
            DirectorVo directorVoTemp = new DirectorVo();
            directorVoTemp.setPage(directorVo.getPage());
            directorVoTemp.setRows(directorVo.getRows());
            BeanUtils.copyProperties(director, directorVoTemp);
            directorVoList.add(directorVoTemp);
        }
        directorVoTableVo.setRows(directorVoList);
        String countHql = "select count(*) " + hql;
        directorVoTableVo.setRecordsTotal((Long) directorDao.uniqueResult(countHql));
        return directorVoTableVo;
    }

    private String addWhere(DirectorVo directorVo, String hql, Map<String, Object> params) {

        // TODO 视频条件查询参数置入方法

        return null;
    }
}
