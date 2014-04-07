$(function() {
	Esgi.module = Esgi.module || {}
	Esgi.module.user = Esgi.module.user || {}

	Esgi.module.user.EspacePerso = function(cfg) {
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/espaceperso/',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : 'Détails de votre compte'
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/annonces/',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : 'Vos annonces'
		});

	}

});