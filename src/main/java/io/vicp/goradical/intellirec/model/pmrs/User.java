package io.vicp.goradical.intellirec.model.pmrs;

import io.vicp.goradical.intellirec.model.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kenny on 2017/3/4.
 * 用户实体类
 */
@Entity
@Table(name = "t_user", indexes = {
		@Index(name = "fk_user_name", columnList = "user_name"),
		@Index(name = "fk_email", columnList = "email", unique = true),
		@Index(name = "fk_password", columnList = "password")})
public class User extends BaseEntity{
	/**
	 * 代理主键
	 */
	@Id
	@GeneratedValue(generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(columnDefinition = "int(11) unsigned")
	private Integer id;

	/**
	 * 用户Id,此 数据是抓取的优酷站点的,此Id是优酷站点的用户Id
	 */
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * email
	 */
	@Column(length = 30)
	private String email;

	/**
	 * 密码
	 */
	@Column(length = 30)
	private String password;

	/**
	 * 用户级别
	 */
	@Column(name = "user_level")
	private Integer userLevel;

	/**
	 * 用户小头像地址链接
	 */
	@Column(name = "head_photo_small")
	private String headPhotoSmall;

	/**
	 * 用户中号头像地址链接
	 */
	@Column(name = "head_photo_middle")
	private String headPhotoMiddle;

	/**
	 * 用户大头像地址链接
	 */
	@Column(name = "head_photo_large")
	private String headPhotoLarge;

	/**
	 * 用户的详细信息
	 *
	 * User 与 UserDetailInfo 为一对一关系，采用一对一单向关联
	 */
	@OneToOne
	@JoinColumn(name = "user_detail_info_id", foreignKey = @ForeignKey(name = "fk_user_detail_info_id"))
	private UserDetailInfo userDetailInfo;

	/**
	 * 用户的收藏记录
	 * <p>
	 * User 与 CollectRecord 为一对多关系，采用一对多双向关联,由多的一方维护关联关系
	 */
	@OneToMany(mappedBy = "user")
	private Set<CollectRecord> collectRecords = new HashSet<>();
	/**
	 * 用户的评论记录
	 * <p>
	 * User 与 CommentRecord 为一对多关系，采用一对多双向关联,由多的一方维护关联关系
	 */
	@OneToMany(mappedBy = "user")
	private Set<CommentRecord> commentRecords = new HashSet<>();
	/**
	 * 用户的浏览记录
	 * <p>
	 * User 与 GlanceRecord 为一对多关系，采用一对多双向关联,由多的一方维护关联关系
	 */
	@OneToMany(mappedBy = "user")
	private Set<GlanceRecord> glanceRecords = new HashSet<>();
	/**
	 * 用户的评分记录
	 * <p>
	 * User 与 MarkRecord 为一对多关系，采用一对多双向关联,由多的一方维护关联关系
	 */
	@OneToMany(mappedBy = "user")
	private Set<MarkRecord> markRecords = new HashSet<>();
	/**
	 * 用户的播放记录
	 * <p>
	 * User 与 PlayRecord 为一对多关系，采用一对多双向关联,由多的一方维护关联关系
	 */
	@OneToMany(mappedBy = "user")
	private Set<PlayRecord> playRecords = new HashSet<>();
	/**
	 * 用户的点赞记录
	 * <p>
	 * User 与 PraiseRecord 为一对多关系，采用一对多双向关联,由多的一方维护关联关系
	 */
	@OneToMany(mappedBy = "user")
	private Set<PraiseRecord> praiseRecords = new HashSet<>();
	/**
	 * 用户的搜索记录
	 * <p>
	 * User 与 SearchRecord 为一对多关系，采用一对多双向关联,由多的一方维护关联关系
	 */
	@OneToMany(mappedBy = "user")
	private Set<SearchRecord> searchRecords = new HashSet<>();

	/**
	 * 用户的推荐
	 * <p>
	 * User 与 UserRecommend 为一对多关系，采用一对多双向关联,由多的一方维护关联关系
	 */
	@OneToMany(mappedBy = "user")
	private Set<UserRecommend> userRecommends = new HashSet<>();

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getHeadPhotoSmall() {
		return headPhotoSmall;
	}

	public void setHeadPhotoSmall(String headPhotoSmall) {
		this.headPhotoSmall = headPhotoSmall;
	}

	public String getHeadPhotoMiddle() {
		return headPhotoMiddle;
	}

	public void setHeadPhotoMiddle(String headPhotoMiddle) {
		this.headPhotoMiddle = headPhotoMiddle;
	}

	public String getHeadPhotoLarge() {
		return headPhotoLarge;
	}

	public void setHeadPhotoLarge(String headPhotoLarge) {
		this.headPhotoLarge = headPhotoLarge;
	}

	public UserDetailInfo getUserDetailInfo() {
		return userDetailInfo;
	}

	public void setUserDetailInfo(UserDetailInfo userDetailInfo) {
		this.userDetailInfo = userDetailInfo;
	}

	public Set<CollectRecord> getCollectRecords() {
		return collectRecords;
	}

	public void setCollectRecords(Set<CollectRecord> collectRecords) {
		this.collectRecords = collectRecords;
	}

	public Set<CommentRecord> getCommentRecords() {
		return commentRecords;
	}

	public void setCommentRecords(Set<CommentRecord> commentRecords) {
		this.commentRecords = commentRecords;
	}

	public Set<GlanceRecord> getGlanceRecords() {
		return glanceRecords;
	}

	public void setGlanceRecords(Set<GlanceRecord> glanceRecords) {
		this.glanceRecords = glanceRecords;
	}

	public Set<MarkRecord> getMarkRecords() {
		return markRecords;
	}

	public void setMarkRecords(Set<MarkRecord> markRecords) {
		this.markRecords = markRecords;
	}

	public Set<PlayRecord> getPlayRecords() {
		return playRecords;
	}

	public void setPlayRecords(Set<PlayRecord> playRecords) {
		this.playRecords = playRecords;
	}

	public Set<PraiseRecord> getPraiseRecords() {
		return praiseRecords;
	}

	public void setPraiseRecords(Set<PraiseRecord> praiseRecords) {
		this.praiseRecords = praiseRecords;
	}

	public Set<SearchRecord> getSearchRecords() {
		return searchRecords;
	}

	public void setSearchRecords(Set<SearchRecord> searchRecords) {
		this.searchRecords = searchRecords;
	}

	public Set<UserRecommend> getUserRecommends() {
		return userRecommends;
	}

	public void setUserRecommends(Set<UserRecommend> userRecommends) {
		this.userRecommends = userRecommends;
	}
}
