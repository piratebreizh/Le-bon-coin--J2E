package org.esgi.module.user;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class EspacePerso extends AbstractAction {

	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Espace Perso");
		
		context.addCSSDependency(context.getProperties().get("context") + "/res/css/commun.css");
	}

	@Override
	public String getRoute() {
		return "/user/espaceperso/";
	}
}
