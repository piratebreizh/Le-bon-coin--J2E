package org.esgi.module.recherche;

import java.util.Enumeration;
import java.util.Iterator;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Recherche extends AbstractAction{
	
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Recherche");		
		//context.getVelocityContext().put("title", context.getRequest().getParameter("nomRecherche"));
		context.addCSSDependency(context.getProperties().get("context") + "/res/css/commun.css");
	
	}
	@Override
	public String getRoute() {
		return "/recherche/";
	}

}
