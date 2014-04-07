$(function(){
	Esgi.module = Esgi.module || {}
	Esgi.module.annonce = Esgi.module.annonce || {}

	Esgi.module.annonce.CreationAnnonce = function (cfg) {
		new Esgi.html.Form({
			url : APP_CONTEXT+'/annonce/creation/',
			renderTo : cfg.id,
			redirect : APP_CONTEXT+'/index/',
			msgSuccess : 'Votre annonce a bien été créé. Vous allez être redirigé vers l\'accueil.',
			inputs : [
			          {
			        	  /*Liste D�roulante r�gions*/
			        	  type : "Select",
			        	  name : 'region',
			        	  id : 'region',
			        	  label : "Region",
			        	   
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
			        	  label : "Categorie"
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