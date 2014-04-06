package org.esgi.module.user;

import org.esgi.orm.ORM;
import org.esgi.orm.model.User;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Enregistrement extends AbstractAction {

	public void execute(IContext context) throws Exception {
		User user = new User();
		user.setLogin(context.getRequest().getParameter("login"));
		user.setPassword(context.getRequest().getParameter("password"));
		user.setNom(context.getRequest().getParameter("nom"));
		user.setPrenom(context.getRequest().getParameter("prenom"));
		user = (User) ORM.save(user);
		context.getVelocityContext().put("user", user);
	}

	@Override
	public String getRoute() {
		return "/user/enregistrement/";
	}

	@Override
	public String getLayout() {
		return "onlytext";
	}
	
}
