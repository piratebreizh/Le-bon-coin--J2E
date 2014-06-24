$(function() {
	Esgi.module = Esgi.module || {}
	Esgi.module.shared = Esgi.module.shared || {}

	Esgi.module.shared.MenuPrincipal = function(cfg) {
		new Esgi.html.link({
			href : APP_CONTEXT + '/index/',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : 'Accueil'
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/annonce/creation/',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : 'Déposer une annonce'
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/recherche/',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : 'Recherche'
		});
		new Esgi.html.link({
			href : APP_CONTEXT + '/user/espaceperso/',
			renderTo : cfg.id,
			classe : 'lienMenu',
			label : 'Mon compte'
		});

	}

});