package org.esgi.module.user;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Enregistrement extends AbstractAction {

	public void execute(IContext context) throws Exception {
		System.out.println(context.getRequest().getParameter("login"));
	}

	@Override
	public String getRoute() {
		return "/user/enregistrement/";
	}
}
