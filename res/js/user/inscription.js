$(function(){
	Esgi.module = Esgi.module || {}
	Esgi.module.user = Esgi.module.user || {}

	Esgi.module.user.Inscription = function (cfg) {
		new Esgi.html.Form({
			url : APP_CONTEXT+'/user/enregistrement/',
			renderTo : cfg.id,
			redirect : APP_CONTEXT+'/index/',
			msgSuccess : 'Votre compte a bien été créé. Vous allez être redirigé vers l\'accueil.',
			inputs : [
			          {
			        	  type : "Text",
			        	  name : 'nom',
			        	  id : 'nom',
			        	  label : "Nom",
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
			        	  label : "Adresse email",
			        	  emptyText : 'Saisir votre login' 
			          },{
			        	  type : "Password",
			        	  label : "Password",
			        	  name : 'password',
			        	  id : 'password'
			          }
			          ]
		});
		

	}

});