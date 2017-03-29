package io.vicp.goradical.intellirec.model.pmrs.vo;

/**
 * Created by 李浩然 on 2017/3/23.
 */
public class ActorVo extends BaseVo {

    private Integer id;

    private String actorName;

    private String description;

    //扩展字段
    private int page = 1;

    private int rows = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
