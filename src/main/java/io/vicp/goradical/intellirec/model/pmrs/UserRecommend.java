package io.vicp.goradical.intellirec.model.pmrs;

import io.vicp.goradical.intellirec.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kenny on 2017/3/4.
 * 用户推荐视频实体类
 */
@Entity
@Table(name = "t_user_recommend_record")
public class UserRecommend extends BaseEntity {
	/**
	 * 代理主键
	 */
	@Id
	@GeneratedValue(generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(columnDefinition = "int(11) unsigned")
	private Integer id;

	/**
	 * 推荐 true 为推荐，false 为不推荐
	 */
	private boolean recommend;

	/**
	 * 理由
	 */
	private String reason;

	/**
	 * 时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date recommendDate;

	/**
	 * UserRecommend 与 Video 之间为多对一关系，采用双向多对一关联映射，由多的一方 UserRecommend 来维护关联关系
	 */
	@ManyToOne
	@JoinColumn(name = "video_userrecommend_id", foreignKey = @ForeignKey(name = "fk_video_userrecommend_id"))
	private Video video;

	/**
	 * UserRecommend 与 User 之间为多对一关系，采用双向多对一关联映射，由多的一方 UserRecommend 来维护关联关系
	 */
	@ManyToOne
	@JoinColumn(name = "user_userrecommend_id", foreignKey = @ForeignKey(name = "fk_user_userrecommend_id"))
	private User user;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isRecommend() {
		return recommend;
	}

	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getRecommendDate() {
		return recommendDate;
	}

	public void setRecommendDate(Date recommendDate) {
		this.recommendDate = recommendDate;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
