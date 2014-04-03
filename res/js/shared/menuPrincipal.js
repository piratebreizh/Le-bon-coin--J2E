$(function() {
	Esgi.module = Esgi.module || {}
	Esgi.module.shared = Esgi.module.shared || {}

	Esgi.module.shared.MenuPrincipal = function(cfg) {
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/index/',
			renderTo : cfg.id,
			label : 'Accueil'
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/espaceperso/',
			renderTo : cfg.id,
			label : 'Mon compte'
		});

	}

});