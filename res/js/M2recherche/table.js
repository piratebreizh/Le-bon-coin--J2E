var global = this;
$(function(){
	global.Esgi.html.Table = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.initComponent();
	}

	global.Esgi.html.Table.prototype = {
			initComponent : function(){
				var me = this,
				tr = $('<tr/>')
				me.table = $('<table style="background-color:white;"/>');
				$(me.cfg.renderTo).append(me.table);
				me.table.append(tr);
				$.each(me.cfg.columns, function(idx, label){
					var th = $('<th/>');
					th.text(label);
					tr.append(th);
				});
			},
			setItems : function(items){
				var me = this;
				$.each(items, function(idx, item) {
					me.addItem(item);
				})
			},
			addItem : function(item){
				var me = this,
				tr = $('<tr/>');
				me.table.append(tr);
				$.each(me.cfg.columns, function(idx, label){
					var td = $('<td/>');
					tr.append(td);
					if ('actions' != label) {
						td.text(item[label]);
					} else {
						$.each(me.cfg.itemActions, function(idx, action){
							var a = $('<a style="color:#333;cursor:pointer"/>');
							a.on('click', function(){
								console.log(action);
								action.callback(item);
							});
							a.text(action.label);
							td.append(a, '|');
						})
					}
				});
			}
	}

});