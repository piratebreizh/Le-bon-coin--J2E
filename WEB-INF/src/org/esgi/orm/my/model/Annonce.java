package org.esgi.orm.my.model;

import org.esgi.orm.my.annotations.ORM_PK;
import org.esgi.orm.my.annotations.ORM_SCHEMA;
import org.esgi.orm.my.annotations.ORM_TABLE;

@ORM_SCHEMA("esgi")
@ORM_TABLE("annonce")
public class Annonce {
	@ORM_PK
	public Integer region;
	public String cp;
	public Integer catego;
	public Integer company_ad;
	public Integer typeann;
	public String name;
	public String email;
	public String phone;
	public String subject;
	public String body;
	public double price;
	
	public Annonce(){
		this.region = 0;
		this.cp = "";
		this.catego = 0;
		this.company_ad = 0;
		this.typeann = 0;
		this.name = "";
		this.email = "";
		this.phone = "";
		this.subject = "";
		this.body = "";
		this.price = 0.0;
	}
	public Annonce(Integer region, String cp, Integer catego,
			Integer company_ad, Integer typeann, String name, String email,
			String phone, String subject, String body, double price2) {
		super();
		this.region = region;
		this.cp = cp;
		this.catego = catego;
		this.company_ad = company_ad;
		this.typeann = typeann;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.subject = subject;
		this.body = body;
		this.price = price2;
	}

	@Override
	public String toString() {
		return "Annonce [region=" + region + ", cp=" + cp + ", catego="
				+ catego + ", company_ad=" + company_ad + ", typeann="
				+ typeann + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", subject=" + subject + ", body=" + body
				+ ", price=" + price + "]";
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public Integer getCatego() {
		return catego;
	}

	public void setCatego(Integer catego) {
		this.catego = catego;
	}

	public Integer getCompany_ad() {
		return company_ad;
	}

	public void setCompany_ad(Integer company_ad) {
		this.company_ad = company_ad;
	}

	public Integer getTypeann() {
		return typeann;
	}

	public void setTypeann(Integer typeann) {
		this.typeann = typeann;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	


}
