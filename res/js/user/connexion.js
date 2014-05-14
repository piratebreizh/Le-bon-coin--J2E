$(function() {
	Esgi.module = Esgi.module || {}
	Esgi.module.user = Esgi.module.user || {}

	Esgi.module.user.Connexion = function(cfg) {
		new Esgi.html.Form({
			url : APP_CONTEXT + '/user/connect/',
			textSubmit : "OK",
			renderTo : cfg.id,
			redirect : APP_CONTEXT + '/index/',
			inputs : [ {
				type : "Text",
				classInput : "form_el input",
				name : 'login',
				id : 'login',
				label : "Email",
				classLabel : "form_el name",
				noInsertBr : true
			}, {
				type : "Password",
				classInput : "form_el input",
				name : 'password',
				id : 'password',
				label : "Mot de passe",
				classLabel : "form_el pass",
				noInsertBr : true
			} ]
		});

	}

});