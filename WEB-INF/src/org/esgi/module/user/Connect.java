package org.esgi.module.user;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.esgi.orm.ORM;
import org.esgi.orm.annotations.ORM_SEARCH_WITHOUT_PK;
import org.esgi.orm.model.User;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Connect extends AbstractAction{

	@Override
	public void execute(IContext context) throws Exception {
		String login = context.getRequest().getParameter("login");
		String password = context.getRequest().getParameter("password");
		ORM_SEARCH_WITHOUT_PK critere = new ORM_SEARCH_WITHOUT_PK();
		critere.addConstrainte("login", login);
		ArrayList<User> results = (ArrayList<User>) ORM.loadWithOutPrimaryKey(User.class, critere);
		
		boolean isPasswordCorrect = false;
		if(results.size() > 0){
			User u = results.get(0);
			isPasswordCorrect = password.equals(u.getPassword());
			//Enregistrement en session
			HttpSession session = context.getRequest().getSession();
	       	session.setAttribute("userConnected", u);
		}
		context.getVelocityContext().put("isPasswordCorrect", isPasswordCorrect);
	}
	
	@Override
	public String getRoute() {
		return "/user/connect";
	}
	
	@Override
	public String getLayout() {
		return "onlytext";
	}
}
