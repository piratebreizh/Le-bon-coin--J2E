package org.esgi.module.user;

import java.util.ArrayList;

import org.esgi.orm.ORM;
import org.esgi.orm.annotations.ORM_SEARCH_WITHOUT_PK;
import org.esgi.orm.model.Annonce;
import org.esgi.orm.model.User;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class VosAnnonces extends AbstractAction {
	
		public void execute(IContext context) throws Exception {
			context.getVelocityContext().put("title", "Vos annonces");
			context.addCSSDependency(context.getProperties().get("context")
					+ "/res/css/commun.css");
			
			ORM_SEARCH_WITHOUT_PK critere = new ORM_SEARCH_WITHOUT_PK();

			if(context.getProperties().get("userConnected") != null){
				User userConnected = (User) context.getProperties().get("userConnected");				
				critere.addConstrainte("user", String.valueOf(userConnected.getId()));
	
				ArrayList<Annonce> listeResultatAnnonce = new ArrayList<>();
				
				listeResultatAnnonce = (ArrayList<Annonce>) ORM.loadWithOutPrimaryKey(Annonce.class, critere);
				
				context.getVelocityContext().put("listeRecherche", listeResultatAnnonce);
	
				
				context.addCSSDependency(context.getProperties().get("context") + "/res/css/commun.css");
			}
		}

		@Override
		public String getRoute() {
			return "/user/espaceperso/vosannonces/";
		}

}
