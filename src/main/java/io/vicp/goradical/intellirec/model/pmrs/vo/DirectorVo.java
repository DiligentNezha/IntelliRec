package io.vicp.goradical.intellirec.model.pmrs.vo;

/**
 * Created by 李浩然 on 2017/3/28.
 */
public class DirectorVo extends BaseVo {

    private Integer id;

    private String directorName;

    private String description;

    //扩展字段
    private int page = 1;

    private int rows = 10;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
}
