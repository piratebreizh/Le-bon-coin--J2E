$(function(){
	Esgi.module = Esgi.module || {}
	Esgi.module.annonce = Esgi.module.annonce || {}

	Esgi.module.annonce.CreationAnnonce = function (cfg) {
		new Esgi.html.Form({
			url : APP_CONTEXT+'/annonce/creation/',
			renderTo : cfg.id,
			redirect : APP_CONTEXT+'/index/',
			msgSuccess : 'Votre annonce a bien Ã©tÃ© crÃ©Ã©. Vous allez Ãªtre redirigÃ© vers l\'accueil.',
			inputs : [
			          {
			        	  /*Liste Déroulante régions*/
			        	  type : "Select",
			        	  name : 'region',
			        	  id : 'region',
			        	  label : "Region",
			        	  options : {Alsace : 'Alsace',
			        		  Aquitaine : 'Aquitaine',
			        		  Auvergne : 'Auvergne',
			        		  Basse-Normandie : 'Basse-Normandie',
			        		  Bourgogne : 'Bourgogne',
			        		  Bretagne : 'Bretagne',
			        		  Centre : 'Centre',
			        		  Champagne-Ardenne : 'Champagne-Ardenne',
			        		  Corse : 'Corse',
			        		  Franche-Comte : 'Franche-Comte',
			        		  Haute-Normandie : 'Haute-Normandie',
			        		  Ile-de-France : 'Ile-de-France',
			        		  Languedoc-Roussillon : 'Languedoc-Roussillon',
			        		  Limousin : 'Limousin',
			        		  Lorraine : 'Lorraine',
			        		  Midi-Pyrenees : 'Midi-Pyrenees',
			        		  Nord-Pas-de-Calais : 'Nord-Pas-de-Calais',
			        		  Pays de la Loire : 'Pays de la Loire',
			        		  Picardie : 'Picardie',
			        		  Poitou-Charentes : 'Poitou-Charentes',
			        		  Provence-Alpes-Cote d'Azur : 'Provence-Alpes-Cote d'Azur',
			        		  Rhone-Alpes : 'Rhone-Alpes',
			        		  Guadeloupe : 'Guadeloupe',
			        		  Martinique : 'Martinique',
			        		  Guyane : 'Guyane',
			        		  Reunion : 'Reunion'
			        	  }
			        	   
			          },
			          {
			        	  type : "Text",
			        	  name : 'cp',
			        	  id : 'cp',
			        	  label : "Code Postal",
			        	  emptyText : 'Saisir votre code postal' 
			          },
			          {
			        	  /*Liste Déroulante catégories*/
			        	  type : "Select",
			        	  name : 'catego',
			        	  id : 'catego',
			        	  label : "Categorie",
			        	  options : {
			        		  Voitures : 'Voitures',
			        		  Motos : 'Motos',
			        		  Caravaning : 'Caravaning',
			        		  Utilitaires : 'Utilitaires',
			        		  Equipement Auto : 'Equipement Auto',
			        		   Equipement Moto : ' Equipement Moto',
			        		   Equipement Caravaning : ' Equipement Caravaning',
			        		  Nautisme : 'Nautisme',
			        		   Equipement Nautisme : ' Equipement Nautisme',
			        		  Ventes immobilières : 'Ventes immobilières',
			        		   Locations : ' Locations',
			        		   Colocations : ' Colocations',
			        		   Locations de vacances : ' Locations de vacances',
			        		   Bureaux & Commerces : ' Bureaux & Commerces',
			        		   Informatique : ' Informatique',
			        		   Consoles & Jeux vidéoti : ' Consoles & Jeux vidéoti',
			        		   Image & Son : ' Image & Son',
			        		   Téléphonie : ' Téléphonie',
			        		   Ameublement : ' Ameublement',
			        		   Electroménager : ' Electroménager',
			        		   Arts de la table : ' Arts de la table',
			        		   Décoration : ' Décoration',
			        		   Linge de maison : ' Linge de maison',
			        		   Bricolage : ' Bricolage',
			        		   Jardinage : ' Jardinage',
			        		   Vêtements : ' Vêtements',
			        		   Chaussures : ' Chaussures',
			        		   Accessoires & Bagagerie : ' Accessoires & Bagagerie',
			        		   Montres & Bijoux : ' Montres & Bijoux',
			        		   Equipement bébé : ' Equipement bébé',
			        		   Vêtements bébé : ' Vêtements bébé',
			        		   DVD / Films : ' DVD / Films',
			        		   CD / Musique : ' CD / Musique',
			        		   Livres : ' Livres',
			        		   Animaux : ' Animaux',
			        		   Vélos : ' Vélos',
			        		   Sports & Hobbies : ' Sports & Hobbies',
			        		   Instruments de musique : ' Instruments de musique',
			        		   Collection : ' Collection',
			        		   Jeux & Jouets : ' Jeux & Jouets',
			        		   Vins & Gastronomie : ' Vins & Gastronomie',
			        		   Matériel Agricole : ' Matériel Agricole',
			        		   Transport & Manutention : ' Transport & Manutention',
			        		   BTP & Chantier Gros oeuvre : ' BTP & Chantier Gros oeuvre',
			        		   Outillage & Matériaux  : ' Outillage & Matériaux ',
			        		   équipements Industriels : ' équipements Industriels',
			        		   Restauration & Hôtellerie : ' Restauration & Hôtellerie',
			        		   Fournitures de Bureau : ' Fournitures de Bureau',
			        		   Commerces & Marchés : ' Commerces & Marchés',
			        		   Matériel Médical : ' Matériel Médical',
			        		   Emploi : ' Emploi',
			        		   Services : ' Services',
			        		   Billetterie : ' Billetterie',
			        		   Evénements : ' Evénements',
			        		   Cours particuliers : ' Cours particuliers',
			        		   Autres : ' Autres'

			        	  }
			          },
			          {
			        	  type : "radio",
			        	  name : 'company_ad',
			        	  id : 'private_ad_id',
			        	  label : "Particulier",
			        	  value : "0",
			        	  checked : "checked"
			          },
			          {
			        	  type : "radio",
			        	  name : 'company_ad',
			        	  id : 'private_ad_id',
			        	  label : "Professionnel",
			        	  value : "1"
			          }, {
			        	  type : "radio",
			        	  name : 'typeann',
			        	  id : 'offre',
			        	  label : "Offre",
			        	  value : "0",
			        	  checked : "checked"
			          },
			          {
			        	  type : "radio",
			        	  name : 'typeann',
			        	  id : 'demande',
			        	  label : "Demande",
			        	  value : "1"
			          },
			          {
			        	  type : "Text",
			        	  name : 'name',
			        	  id : 'name',
			        	  label : "Nom",
			        	  emptyText : 'Saisir votre nom' 
			          },
			          {
			        	  type : "Text",
			        	  name : 'email',
			        	  id : 'email',
			        	  label : "Email",
			        	  emptyText : 'Saisir votre email' 
			          },
			          {
			        	  type : "Text",
			        	  name : 'phone',
			        	  id : 'phone',
			        	  label : "Telephone",
			        	  emptyText : 'Saisir votre telephone' 
			          },
			          {
			        	  type : "Text",
			        	  name : 'subject',
			        	  id : 'subject',
			        	  label : "Titre annonce",
			        	  emptyText : 'Saisir l''intitule de l''annonce' 
			          },
			          {
			        	  type : "TextArea",
			        	  name : 'body',
			        	  id : 'body',
			        	  label : "Description",
			        	  emptyText : 'Saisir une description de l''annonce' 
			          },
			          {
			        	  type : "Text",
			        	  name : 'price',
			        	  id : 'price',
			        	  label : "Prix",
			        	  emptyText : 'Saisir le prix' 
			          }
			          ]
		});
		

	}

});