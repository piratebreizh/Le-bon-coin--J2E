package org.esgi.module.user;

import java.util.ArrayList;

import org.esgi.orm.ORM;
import org.esgi.orm.annotations.ORM_SEARCH_WITHOUT_PK;
import org.esgi.orm.model.User;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class Enregistrement extends AbstractAction {

	public void execute(IContext context) throws Exception {
		User user = new User();
		//On vérifie que le login n'est pas déjà utilisé
		ORM_SEARCH_WITHOUT_PK critere = new ORM_SEARCH_WITHOUT_PK();
		critere.addConstrainte("login", context.getRequest().getParameter("login"));
		ArrayList<User> results = (ArrayList<User>) ORM.loadWithOutPrimaryKey(User.class, critere);
		
		if(results.size() > 0){
			//Login déjà utilisé => message d'erreur
			context.getVelocityContext().put("erreur", "loginAlreadyUsed");
		}else{
			//On enregistre le nouvel utilisateur
			user.setLogin(context.getRequest().getParameter("login"));
			user.setPassword(context.getRequest().getParameter("password"));
			user.setNom(context.getRequest().getParameter("nom"));
			user.setPrenom(context.getRequest().getParameter("prenom"));
			user = (User) ORM.save(user);
			if(!(user.getId()>0))
				context.getVelocityContext().put("erreur", "saveFailed");
		}
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
