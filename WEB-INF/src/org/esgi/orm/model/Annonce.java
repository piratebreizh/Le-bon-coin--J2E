package org.esgi.orm.model;

import org.esgi.orm.annotations.ORM_SCHEMA;
import org.esgi.orm.annotations.ORM_TABLE;

@ORM_SCHEMA("esgi")
@ORM_TABLE("user")
public class Annonce {

	
	public int numero;
	public String titre;
	public String description;
	//Voir pour la gestion des clés étrangères dans l'ORM
	public User user;
	public String ville;
	public String categorie;

	
	
	public Annonce(int numero, String titre, String description, User user,
			String ville, String catregorie) {
		super();
		this.numero = numero;
		this.titre = titre;
		this.description = description;
		this.user = user;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
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
				+ ", description=" + description + ", user=" + user
				+ ", ville=" + ville + ", catregorie=" + categorie + "]";
	}
	
	
	
	
}
