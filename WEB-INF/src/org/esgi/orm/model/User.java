package org.esgi.orm.model;

import java.util.Date;

import org.esgi.orm.annotations.ORM_SCHEMA;
import org.esgi.orm.annotations.ORM_TABLE;
import org.esgi.orm.annotations.ORM_PK;

@ORM_SCHEMA("esgi")
@ORM_TABLE("user")
public class User{
	
	@ORM_PK
	public Integer id;
	public String login;
	public String password;
	public String nom;
	public String prenom;
	public String adresseMail;
	public volatile Date connectedAt;

	
	public User(){
	}

	public User(String _login, String _password){
		this.login = _login;
		this.password = _password;
	//	this.connectedAt = new java.util.Date();
	}
	

	public User(Integer id, String login, String password, String nom,
			String prenom) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
		
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", connectedAt=" + "connectedAt" + "]";
	}

}
