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
		if(!me.cfg.textSubmit)
			me.cfg.textSubmit = "Envoyer";
		me.submit = $("<button>"+me.cfg.textSubmit+"</button>");
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
					if (me.cfg.msgSuccess)
						alert(me.cfg.msgSuccess);
					else if(me.cfg.resultTo)
						$(me.cfg.resultTo).html(response);
					else
						alert(response);
					if (me.cfg.redirect)
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
			if (me.cfg.label != undefined)
				$(
						"<label class\"" + me.cfg.classLabel + "\" for=\"" + me.cfg.id + "\">" + me.cfg.label
								+ " :&nbsp;</label>").appendTo(me.cfg.renderTo);
			$(me.cfg.renderTo).appendTo(me.cfg.renderTo);
			$(me.cfg.renderTo).append(me.el);
			if(!me.cfg.noInsertBr)
				$(me.cfg.renderTo).append("<br/>");
			
		},
		getValue : function() {
			return $(this.el).val();
		}
	}

	Esgi.html.inputs = {};
	Esgi.html.inputs.Text = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<input class\"" + me.cfg.classInput + "\" name=\"" + cfg.name + "\" id=\"" + cfg.id
				+ "\" type='text'/>");
		this.init();

	}
	Esgi.html.inputs.Text.prototype = commons;
	
	Esgi.html.inputs.TextArea = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<textarea class\"" + me.cfg.classInput + "\" name=\"" + cfg.name + "\" id=\"" + cfg.id
				+ "\" />");
		this.init();

	}
	Esgi.html.inputs.TextArea.prototype = commons;

	Esgi.html.inputs.Password = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<input class\"" + me.cfg.classInput + "\" name=\"" + cfg.name + "\" id=\"" + cfg.id
				+ "\" type='password'/>");
		this.init();
	}
	Esgi.html.inputs.Password.prototype = commons;
	
	Esgi.html.inputs.Radio = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<input class\"" + me.cfg.classInput + "\" name=\"" + cfg.name + "\" id=\"" + cfg.id
				+ "\" type='radio'/>");
		this.init();
	}
	Esgi.html.inputs.Radio.prototype = commons;

	Esgi.html.inputs.Select = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<select class\"" + me.cfg.classInput + "\" name=\"" + cfg.name + "\" id=\"" + cfg.id + "\">");
		for ( var val in cfg.options) {
			$("<option />", {
				value : val,
				text : cfg.options[val]
			}).appendTo(me.el);
		}
		me.init();

	}
	Esgi.html.inputs.Select.prototype = commons;
	
	Esgi.html.inputs.File = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<input class\"" + me.cfg.classInput + "\" name=\"" + cfg.name + "\" id=\"" + cfg.id
				+ "\" type='file'/>");
		this.init();
	}
	Esgi.html.inputs.File.prototype = commons;
	


	//
	// LINKS
	//
	Esgi.html.link = function(cfg) {
		var me = this;
		me.cfg = cfg;
		me.el = $("<a href=\"" + cfg.href + "\" class=\"" + cfg.classe + "\"\">" + cfg.label + "</a>");
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
