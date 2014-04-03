$(function() {
	Esgi.module = Esgi.module || {}
	Esgi.module.user = Esgi.module.user || {}

	Esgi.module.user.Connexion = function(cfg) {
		new Esgi.html.Form({
			url : APP_CONTEXT + '/user/enregistrement/',
			renderTo : cfg.id,
			redirect : APP_CONTEXT + '/index/',
			inputs : [ {
				type : "Text",
				name : 'login',
				id : 'login',
				label : "Email"
			}, {
				type : "Password",
				label : "Password",
				name : 'password',
				id : 'password'
			} ]
		});

	}

});