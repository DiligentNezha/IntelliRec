package io.vicp.goradical.intellirec.model.security;


import io.vicp.goradical.intellirec.model.BaseEntity;
import io.vicp.goradical.intellirec.model.pmrs.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 */
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {

	@Id
	@GeneratedValue(generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(columnDefinition = "int(11) unsigned")
	private Integer id;

	private String roleName;
	private String roleValue;
	private String roleDesc;

	/**
	 * 权限集合
	 * Role 与 Right 之间是多对多关联，采用双向多对多关联
	 */
	@ManyToMany
	@JoinTable(name = "t_role_right_link",
			joinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_id")),
			inverseJoinColumns = @JoinColumn(name = "right_id", foreignKey = @ForeignKey(name = "fk_right_id")))
	private Set<Right> rights = new HashSet<>();

	/**
	 * Role 与 User 之间为多对多关系, 采用双向多对多关联映射,由 User 一方来维护关联关系
	 */
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
