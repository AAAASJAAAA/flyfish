package com.songxinjing.flyfish.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 用户组信息表实体类
 * 
 * @author songxinjing
 *
 */
@Entity
public class UserGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户组ID
	 */
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int groupId;

	/**
	 * 用户组名称
	 */
	@Column(length = 16)
	private String groupName;

	/**
	 * 用户组状态：0-正常；1-删除
	 */
	@Column
	private Integer state;

	/**
	 * 成员列表
	 */
	@ManyToMany
	private Set<User> members;

	/**
	 * 用户组所属角色列表
	 */
	@ManyToMany(mappedBy = "userGroups")
	private Set<Role> roles;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}