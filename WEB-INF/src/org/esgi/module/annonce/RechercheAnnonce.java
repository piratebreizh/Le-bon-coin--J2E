package org.esgi.module.annonce;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class RechercheAnnonce extends AbstractAction {

	@Override
	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Recherche d'une annonce");
		
	}

	@Override
	public String getRoute() {
		return "^/annonce/recherche/";
	}

	@Override
	public String[] getRewriteGroups() {
		return new String[]{"path"};
	}

}
