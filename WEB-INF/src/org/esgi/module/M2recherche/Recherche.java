package org.esgi.module.M2recherche;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Recherche extends AbstractAction{
	
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Recherche");
		
	}
	@Override
	public String getRoute() {
		return "/recherche/";
	}

}
