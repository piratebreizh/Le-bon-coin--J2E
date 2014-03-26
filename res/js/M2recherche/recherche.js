$(function(){
	Esgi.module = Esgi.module || {}
	Esgi.module.user = Esgi.module.user || {}

	Esgi.module.user.Connect = function (cfg) {
		new Esgi.html.Form({
			url : APP_CONTEXT+'/ResultatRecherche/',
			renderTo : cfg.id,
			inputs : [
			          {
			        	  type : "Text",
			        	  name : 'nomRecherche',
			        	  label : "nomRecherche",
			        	  emptyText : 'Saisir le nom de votre recherche' 
			          },{
			        	  type : "Select",
			        	  label : "categorie",
			        	  name : 'Catégorie',
			          },{
			        	  type : "Select",
			        	  label : "ville",
			        	  name : 'Ville',
			          }
			          ]
		});
		
		var removeAction = function(item) {

			$.ajax({
				success : function(r){
					if (r.success) {

						table.removeItem(item);
					}
				}
			})
			console.log('Item to remove : ', item);
		}

		
		/*<!--var table = new Esgi.html.Table({
			renderTo : cfg.id,
			columns : ['login', 'password', 'actions'],
			itemActions : [{
				label : 'Remove',
				type : 'button',
				callback : removeAction
					
			},{
				type : 'link',
				label : 'Update',
				callback : function(){alert('Table')}
			}
			]
		});
		
		table.setItems([{
			login : 'Michael',
			password : 'blabla'
		},{
			login : 'Rayane',
			password : 'ligue2014'
		},{
			login : 'Jonathan',
			password : 'blabla2'
		}]);*/

	}

});