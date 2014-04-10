$(function(){
	Esgi.module = Esgi.module || {}
	Esgi.module.annonce = Esgi.module.annonce || {}

	Esgi.module.annonce.CreationAnnonce = function (cfg) {
		new Esgi.html.Form({
			url : APP_CONTEXT+'/annonce/creation/',
			renderTo : cfg.id,
			redirect : APP_CONTEXT+'/annonce/nouvelle/',
			msgSuccess : 'Votre annonce a bien été créé. Vous allez être redirigé vers l\'accueil.',
			inputs : [
			          {
			        	  /*Liste D�roulante r�gions*/
			        	  type : "Select",
			        	  name : 'region',
			        	  id : 'region',
			        	  label : "Region",
			        	  options : {Alsace : 'Alsace',
			        		  Aquitaine : 'Aquitaine',
			        		  Auvergne : 'Auvergne',
			        		  BasseNormandie : 'Basse-Normandie',
			        		  Bourgogne : 'Bourgogne',
			        		  Bretagne : 'Bretagne',
			        		  Centre : 'Centre',
			        		  ChampagneArdenne : 'Champagne-Ardenne',
			        		  Corse : 'Corse',
			        		  FrancheComte : 'Franche-Comte',
			        		  HauteNormandie : 'Haute-Normandie',
			        		  IledeFrance : 'Ile-de-France',
			        		  LanguedocRoussillon : 'Languedoc-Roussillon',
			        		  Limousin : 'Limousin',
			        		  Lorraine : 'Lorraine',
			        		  MidiPyrenees : 'Midi-Pyrenees',
			        		  NordPasdeCalais : 'Nord-Pas-de-Calais',
			        		  PaysdelaLoire : 'Pays de la Loire',
			        		  Picardie : 'Picardie',
			        		  PoitouCharentes : 'Poitou-Charentes',
			        		  ProvenceAlpesCotedAzur : 'Provence-Alpes-Cote d\'Azur',
			        		  RhoneAlpes : 'Rhone-Alpes',
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
						/*Liste D�roulante cat�gories*/
						type : "Select",
						name : 'catego',
						id : 'catego',
						label : "Categorie",
						options : {
							Voitures : 'Voitures',
							Motos : 'Motos',
							Caravaning : 'Caravaning',
							Utilitaires : 'Utilitaires',
							EquipementAuto : 'Equipement Auto',
							EquipementMoto : ' Equipement Moto',
							EquipementCaravaning : ' Equipement Caravaning',
							Nautisme : 'Nautisme',
							EquipementNautisme : ' Equipement Nautisme',
							Ventesimmobilieres : 'Ventes immobili�res',
							Locations : ' Locations',
							Colocations : ' Colocations',
							Locationsdevacances : ' Locations de vacances',
							BureauxCommerces : ' Bureaux & Commerces',
							Informatique : ' Informatique',
							ConsolesJeuxvideo : ' Consoles & Jeux vid�o',
							ImageSon : ' Image & Son',
							Telephonie : ' T�l�phonie',
							Ameublement : ' Ameublement',
							Electromenager : ' Electrom�nager',
							Artsdelatable : ' Arts de la table',
							Decoration : ' D�coration',
							Lingedemaison : ' Linge de maison',
							Bricolage : ' Bricolage',
							Jardinage : ' Jardinage',
							Vetements : ' V�tements',
							Chaussures : ' Chaussures',
							AccessoiresBagagerie : ' Accessoires & Bagagerie',
							MontresBijoux : ' Montres & Bijoux',
							Equipementbebe : ' Equipement b�b�',
							Vetementsbebe : ' V�tements b�b�',
							DVDFilms : ' DVD / Films',
							CDMusique : ' CD / Musique',
							Livres : ' Livres',
							Animaux : ' Animaux',
							Velos : ' V�los',
							SportsHobbies : ' Sports & Hobbies',
							Instrumentsdemusique : ' Instruments de musique',
							Collection : ' Collection',
							JeuxJouets : ' Jeux & Jouets',
							VinsGastronomie : ' Vins & Gastronomie',
							MaterielAgricole : ' Mat�riel Agricole',
							TransportManutention : ' Transport & Manutention',
							BTPChantierGrosoeuvre : ' BTP & Chantier Gros oeuvre',
							OutillageMateriaux  : ' Outillage & Mat�riaux ',
							EquipementsIndustriels : ' �quipements Industriels',
							RestaurationHotellerie : ' Restauration & H�tellerie',
							FournituresdeBureau : ' Fournitures de Bureau',
							CommercesMarches : ' Commerces & March�s',
							MaterielMedical : ' Mat�riel M�dical',
							Emploi : ' Emploi',
							Services : ' Services',
							Billetterie : ' Billetterie',
							Evenements : ' Ev�nements',
							Coursparticuliers : ' Cours particuliers',
							Autres : ' Autres'
			        	  }
			          },
			          {
			        	  type : "Radio",
			        	  name : 'company_ad',
			        	  id : 'private_ad_id',
			        	  label : "Particulier",
			        	  value : "0",
			        	  checked : "checked"
			          },
			          {
			        	  type : "Radio",
			        	  name : 'company_ad',
			        	  id : 'private_ad_id',
			        	  label : "Professionnel",
			        	  value : "1"
			          }, {
			        	  type : "Radio",
			        	  name : 'typeann',
			        	  id : 'offre',
			        	  label : "Offre",
			        	  value : "0",
			        	  checked : "checked"
			          },
			          {
			        	  type : "Radio",
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
			        	  emptyText : 'Saisir l\'intitule de l\'annonce' 
			          },
			          {
			        	  type : "TextArea",
			        	  name : 'body',
			        	  id : 'body',
			        	  label : "Description",
			        	  emptyText : 'Saisir une description de l\'annonce' 
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