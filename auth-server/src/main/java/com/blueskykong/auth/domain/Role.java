/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：Role.java
 * Date：18-3-6 下午3:47
 * Author：boni
 */

package com.blueskykong.auth.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Role implements Comparable<Role>{
	private Integer id;
	private String name;
	private Integer roleLevel;
	private String description;
	private String menuItems;

	@Override
	public int compareTo(Role o) {
		if(id == o.getId()){
			return 0;
		}else if(id > o.getId()){
			return 1;
		}else{
			return -1;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Role){
			if(this.id == ((Role)obj).getId()){
				return true;
			}
		}
		return false;
	}
}