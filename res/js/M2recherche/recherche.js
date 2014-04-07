$(function(){
	Esgi.module = Esgi.module || {}
	Esgi.module.recherche = Esgi.module.recherche || {}

	Esgi.module.recherche.Form = function (cfg) {
		new Esgi.html.Form({
			url : APP_CONTEXT+'/resultatrecherche/',
			renderTo : cfg.id,
			resultTo : cfg.divResultID,
			inputs : [
			          {
			        	  type : "Text",
			        	  name : 'nomRecherche',
			        	  label : "nomRecherche",
			        	  emptyText : 'Saisir le nom de votre recherche' 
			          },{
			        	  type : "Select",
			        	  label : "categorie",
			        	  name : 'categorie',
			        	  options : {vehicule : 'Véhicule',immobilier: 'Immobilier',mutlimedia: 'Multimédia'}
			          },{
			        	  type : "Select",
			        	  label : "ville",
			        	  name : 'ville',
			        	  options : {idf : 'Ile de France',bretagne : 'Bretagne', alsace : 'Alsace', aquitaine : 'Aquitaine', auvergne : 'Auverge'}
			          }
			          ]
		});

	}

});