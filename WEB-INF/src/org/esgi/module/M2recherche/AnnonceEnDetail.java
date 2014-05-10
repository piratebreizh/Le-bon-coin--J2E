package org.esgi.module.M2recherche;

import org.esgi.orm.ORM;
import org.esgi.orm.model.Annonce;
import org.esgi.orm.model.User;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class AnnonceEnDetail extends AbstractAction{
	
	public void execute(IContext context) throws Exception {
		
		String idAnnonce = "";
		boolean annonceExist=true;
		if(context.getRequest().getParameter("idAnnonce") != null){
			idAnnonce = context.getRequest().getParameter("idAnnonce");
		}else{
			annonceExist =false;
		}
		
		context.getVelocityContext().put("title", "D&eacute;tail de l'annonce");		
		//context.getVelocityContext().put("title", context.getRequest().getParameter("nomRecherche"));
		Annonce annonce = (Annonce) ORM.load(Annonce.class,idAnnonce);
		if(annonce.getNumero()==0){
			annonceExist = false; 
		}
		
		
		/*User test = new User(1, "keke22","123", "Durant", "Jean-Marc");
		ORM.save(test);*/
		
		
		User vendeur = (User) ORM.load(User.class, annonce.getUser());
		
		context.getVelocityContext().put("AnnonceEnvoi", annonce);
		
		context.getVelocityContext().put("Vendeur", vendeur);
		
		context.addCSSDependency(context.getProperties().get("context") + "/res/css/commun.css");
	
	}
	@Override
	public String getRoute() {
		return "/AnnonceEnDetail/";
	}
}
