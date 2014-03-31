package org.esgi.module.user;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Inscription extends AbstractAction {

	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Inscription");
		context.addCSSDependency(context.getProperties().get("context")
				+ "/res/css/commun.css");
	}

	@Override
	public String getRoute() {
		return "/user/inscription/";
	}
}
