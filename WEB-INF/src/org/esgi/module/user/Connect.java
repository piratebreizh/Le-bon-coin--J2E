package org.esgi.module.user;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Connect extends AbstractAction{
	@Override
	public String getRoute() {
		return "/user/connect";
	}
	@Override
	public String getLayout() {
		return null;
	}
	@Override
	public void execute(IContext context) throws Exception {
		System.out.println("Login = " + context.getRequest().getParameter("login"));
	}
}
