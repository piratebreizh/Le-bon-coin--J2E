package org.esgi.orm.model;

import java.sql.Date;

import org.esgi.orm.annotations.ORM_PK;
import org.esgi.orm.annotations.ORM_SCHEMA;
import org.esgi.orm.annotations.ORM_TABLE;



@ORM_SCHEMA("esgi")
@ORM_TABLE("annonce")
public class Annonce {

	@ORM_PK
	public Integer numero;
	public String titre;
	public String description;
	public int user;
	public String ville;
	public String categorie;
	public String photo1;
	public String photo2;
	public String photo3;
	public double prix;
	public Date dateCreation;
	
	
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public String getDateToString(){
		return this.dateCreation.toString();
	}

	public Annonce(int numero, String titre, String description, String ville,
			String categorie, String photo1, String photo2, String photo3,
			String photo4, double prix) {
		super();
		this.numero = numero;
		this.titre = titre;
		this.description = description;
		this.ville = ville;
		this.categorie = categorie;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.photo3 = photo3;
		this.prix = prix;
	}



	public Annonce(String titre, String description, int user, String ville,
			String categorie, double prix, Date dateCreation) {
		
		this.titre = titre;
		this.description = description;
		this.user = user;
		this.ville = ville;
		this.categorie = categorie;
		this.prix = prix;
		this.dateCreation = dateCreation;
	}

	public String getCategorie() {
		return categorie;
	}



	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}



	public String getPhoto1() {
		return photo1;
	}



	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}



	public String getPhoto2() {
		return photo2;
	}



	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}



	public String getPhoto3() {
		return photo3;
	}



	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}




	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}



	public Annonce(int numero, String titre, String description, User user,
			String ville, String catregorie) {
		super();
		this.numero = numero;
		this.titre = titre;
		this.description = description;
		//this.user = user;
		this.ville = ville;
		this.categorie = catregorie;
	}
	
	
	
	public Annonce(String titre, String ville, String catregorie) {
		super();
		this.titre = titre;
		this.ville = ville;
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
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
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
				+ ", description=" + description + ", ville=" + ville
				+ ", categorie=" + categorie + ", photo1=" + photo1
				+ ", photo2=" + photo2 + ", photo3=" + photo3 + ", photo4="
				+ ", prix=" + prix + "]";
	}
	
	
	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}
	
	
	
}
