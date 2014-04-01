$(function(){
	Esgi.module = Esgi.module || {}
	Esgi.module.index = Esgi.module.index || {}

	Esgi.module.index.Index = function (cfg) {
		new Esgi.html.link({
    	  href : APP_CONTEXT + '/user/inscription/',
		  renderTo : cfg.id,
    	  label : 'S\'inscrire'
      });
		

	}

});