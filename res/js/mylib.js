var global = this, loadMyLib = function(onloaded) {

	global.Esgi = {};
	global.Esgi.html = {};

	// /
	// / FORM
	// /

	/**
	 * *
	 * 
	 * @cfg.renderTo : Dom css identifier *
	 * @cfg.url : url to submit form. *
	 * @cfg.inputs : list of inputs cfg
	 */
	global.Esgi.html.Form = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.render();
		me.initInputs();
		me.submit = $("<button>Envoyer</button>");
		$(me.cfg.renderTo).append(me.submit);
		me.submit.on('click', function(e) {
			me.onButtonClick(e)
		});
	};

	global.Esgi.html.Form.prototype = {
		initInputs : function() {
			var me = this;
			me._inputs = {};
			$.each(this.cfg.inputs, function(idx, item) {
				item.renderTo = me.el;
				me._inputs[item.name] = new Esgi.html.inputs[item.type](item);
			});
		},
		addInput : function(input) {

		},
		render : function() {
			this.el = $("<form/>");
			$(this.cfg.renderTo).append(this.el)
		},
		onButtonClick : function(e) {
			var me = this, data = {};
			$.each(me._inputs, function(key, item) {
				data[key] = item.getValue();
			});
			$.ajax({
				url : me.cfg.url,
				method : 'POST',
				data : data,
				success : function(response) {
					if(me.cfg.msgSuccess)
						alert(me.cfg.msgSuccess);
					else if(me.cfg.resultTo)
						$(me.cfg.resultTo).html(response);
					else
						alert(response);
					if(me.cfg.redirect)
						$(location).attr('href', me.cfg.redirect);
				}

			})
			e.preventDefault();
			return false;
		}

	}

	//
	// INPUTS
	//
	var commons = {
		init : function() {
			var me = this;
			if(me.cfg.label != undefined)
				$("<label for=\""+me.cfg.id+"\">"+me.cfg.label+" :&nbsp;</label>").appendTo(me.cfg.renderTo);
			$(me.cfg.renderTo).appendTo(me.cfg.renderTo);
			$(me.cfg.renderTo).append(me.el);
		},
		getValue : function() {
			return $(this.el).val();
		}
	}

	Esgi.html.inputs = {};
	Esgi.html.inputs.Text = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<input name=\""+cfg.name+"\" id=\""+cfg.id+"\" /><br/>");
		this.init();

	}

	Esgi.html.inputs.Text.prototype = commons;

	Esgi.html.inputs.Password = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<input name=\""+cfg.name+"\" id=\""+cfg.id+"\" type='password'/><br/>");
		this.init();
	}
	
	Esgi.html.inputs.Password.prototype = commons;

	Esgi.html.inputs.Select = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<select name=\""+cfg.name+"\" id=\""+cfg.id+"\">");
		for(var val in cfg.options) {
		    $("<option />", {value: val, text: cfg.options[val]}).appendTo(me.el );
		}
		me.init();

	}
	Esgi.html.inputs.Select.prototype = commons;

	//
	// LINKS
	//
	Esgi.html.link = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<a href=\""+cfg.href+"\">"+cfg.label+"</a>");
		this.init();
	}
	Esgi.html.link.prototype = {
			init : function() {
				var me = this;
				$(me.cfg.renderTo).append(me.el);
			}
		};
}

$(loadMyLib);
