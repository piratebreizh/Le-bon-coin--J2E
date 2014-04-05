package org.esgi.module.index;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Index extends AbstractAction{
	
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Index");
		System.out.println(context.getRequest().getParameter("login"));
	}
	@Override
	public String getRoute() {
		return "^/$";
		context.getVelocityContext().put("title", "Bienvenue sur LeBonCoinJ2E");

		context.addCSSDependency(context.getProperties().get("context") + "/res/css/commun.css");
	}


}
