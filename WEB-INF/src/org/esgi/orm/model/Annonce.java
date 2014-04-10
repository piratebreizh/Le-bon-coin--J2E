package org.esgi.orm.model;

import org.esgi.orm.annotations.ORM_PK;
import org.esgi.orm.annotations.ORM_SCHEMA;
import org.esgi.orm.annotations.ORM_TABLE;

@ORM_SCHEMA("esgi")
@ORM_TABLE("annonce")
public class Annonce {

	@ORM_PK
	public int numero;
	public String titre;
	public String description;
	//Voir pour la gestion des clés étrangères dans l'ORM
	//public User user;
	public String cp;
	public String region;
	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}


	public String categorie;
	public String Email;
	public double Prix;

	
	
	public double getPrix() {
		return Prix;
	}



	public void setPrix(double prix) {
		Prix = prix;
	}



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}



	public Annonce(int numero, String titre, String description, User user,
			String cp, String catregorie) {
		super();
		this.numero = numero;
		this.titre = titre;
		this.description = description;
		//this.user = user;
		this.cp = cp;
		this.categorie = catregorie;
	}
	
	
	
	public Annonce(String titre, String ville, String catregorie) {
		super();
		this.titre = titre;
		this.cp = ville;
		this.categorie = catregorie;
	}

	public Annonce() {
	}



	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	public String getcp() {
		return cp;
	}
	public void setcp(String cp) {
		this.cp = cp;
	}
	public String getCatregorie() {
		return categorie;
	}
	public void setCatregorie(String catregorie) {
		this.categorie = catregorie;
	}

	
	@Override
	public String toString() {
		return "Annonce [numero=" + numero + ", titre=" + titre
				+ ", description=" + description + ", user=" 
				+ ", ville=" + cp + ", catregorie=" + categorie + "]";
	}
	
	
	
	
}
