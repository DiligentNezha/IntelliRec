package io.vicp.goradical.intellirec.service;

import io.vicp.goradical.intellirec.model.pmrs.Director;
import io.vicp.goradical.intellirec.model.pmrs.vo.DirectorVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;

/**
 * Created by 李浩然 on 2017/3/28.
 */
public interface DirectorService extends BaseService<Director> {

    /**
     * 根据导演的视图模型返回导演数据的表格视图模型
     * @param directorVo   导演的视图模型
     * @return  导演数据的表格模型
     */
    TableVo<DirectorVo> tableData(DirectorVo directorVo);
}
