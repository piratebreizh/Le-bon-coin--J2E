$(function(){
	Esgi.module = Esgi.module || {}
	Esgi.module.user = Esgi.module.user || {}

	Esgi.module.user.Inscription = function (cfg) {
		new Esgi.html.Form({
			url : APP_CONTEXT+'/user/enregistrement/',
			renderTo : cfg.id,
			inputs : [
			          {
			        	  type : "Text",
			        	  name : 'nom',
			        	  id : 'nom',
			        	  emptyText : 'Saisir votre nom' 
			          },
			          {
			        	  type : "Text",
			        	  name : 'prenom',
			        	  id : 'prenom',
			        	  label : "Prénom",
			        	  emptyText : 'Saisir votre prenom' 
			          },
			          {
			        	  type : "Text",
			        	  name : 'login',
			        	  id : 'login',
			        	  label : "Login",
			        	  emptyText : 'Saisir votre login' 
			          },{
			        	  type : "Password",
			        	  label : "Password",
			        	  name : 'password',
			        	  id : 'password'
			          },{
			        	  type : "Select",
			        	  label : "Select",
			        	  name : 'select',
			        	  options : {val1 : 'choix1',val2 : 'choix2'}
			          }
			          ]
		});
		

	}

});