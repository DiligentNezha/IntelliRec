package io.vicp.goradical.intellirec.model.pmrs.vo;

import com.google.common.collect.Sets;
import io.vicp.goradical.intellirec.model.pmrs.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 李浩然 on 2017/3/22.
 */
public class VideoVo extends BaseVo {

    private Integer id;

    private String videoId;

    private String videoName;

    private String cover;

    private Date published;

    private Integer duration;

    private String alias;

    private String summary;

    private String youkuPlayUrl;

    private String youkuDetailUrl;

    private String downloadUrl;

    private Category category;

    private ActionInfo actionInfo;

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

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getYoukuPlayUrl() {
        return youkuPlayUrl;
    }

    public void setYoukuPlayUrl(String youkuPlayUrl) {
        this.youkuPlayUrl = youkuPlayUrl;
    }

    public String getYoukuDetailUrl() {
        return youkuDetailUrl;
    }

    public void setYoukuDetailUrl(String youkuDetailUrl) {
        this.youkuDetailUrl = youkuDetailUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ActionInfo getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }
}
