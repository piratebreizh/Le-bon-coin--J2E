package org.esgi.orm.my.model;

import java.util.Date;
import java.util.List;

import org.esgi.orm.my.IORM;
import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_SCHEMA;
import org.esgi.orm.my.annotations.ORM_TABLE;

@ORM_SCHEMA("esgi")
@ORM_TABLE("user")
public class User{
	@ORM_PK
	public Integer id;
	public String login;
	public String password;
	public volatile Date connectedAt;
	
	public User(String _login, String _password){
		this.login = _login;
		this.password = _password;
		this.connectedAt = new java.util.Date();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", connectedAt=" + connectedAt + "]";
	}
}
