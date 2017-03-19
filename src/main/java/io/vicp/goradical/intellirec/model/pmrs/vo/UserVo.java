package io.vicp.goradical.intellirec.model.pmrs.vo;

/**
 * Created by Kenny on 2017/3/13.
 */
public class UserVo extends BaseVo{

	//User 模型字段
	private Integer id;

	private Integer userId;

	private String userName;

	private String email;

	private String password;

	private Integer userLevel;

	private String headPhotoSmall;

	private String headPhotoMiddle;

	private String headPhotoLarge;

	//扩展字段
	private int page = 1;

	private int rows = 10;

	public Integer getId() {
		return id;
	}

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
