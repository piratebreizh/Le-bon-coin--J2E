package org.esgi.module.recherche;

import java.util.ArrayList;
import java.util.HashMap;

import org.esgi.orm.ORM;
import org.esgi.orm.annotations.ORM_SEARCH_WITHOUT_PK;
import org.esgi.orm.model.Annonce;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class ResultatRecherche extends AbstractAction{

	public void execute(IContext context) throws Exception {
		
		context.getVelocityContext().put("title", "Résultat de la recherche");
		
		
		
		ORM_SEARCH_WITHOUT_PK critere = new ORM_SEARCH_WITHOUT_PK();
		
		String titreRecherche = "", villeRecherche = "", categorieRecherche = "";
		
		if(context.getRequest().getParameter("nomRecherche") != null && !context.getRequest().getParameter("nomRecherche").equals("")){
			titreRecherche =  context.getRequest().getParameter("nomRecherche");
			critere.addConstrainte("titre", titreRecherche);
		}

		if(context.getRequest().getParameter("categorie") != null){
			categorieRecherche =  context.getRequest().getParameter("categorie");
			critere.addConstrainte("categorie", categorieRecherche);
		}
		
		if(context.getRequest().getParameter("ville") != null){
			villeRecherche =  context.getRequest().getParameter("ville");
			critere.addConstrainte("ville", villeRecherche);
		}
		

		ArrayList<Annonce> listeResultatAnnonce = new ArrayList<>();
		
		listeResultatAnnonce = (ArrayList<Annonce>) ORM.loadWithOutPrimaryKey(Annonce.class, critere);
		
		context.getVelocityContext().put("listeRecherche", listeResultatAnnonce);

	}
	
	@Override
	public String getRoute() {
		return "/resultatrecherche/";
	}

	@Override
	public String getLayout() {
		return "onlytext";
	}
	
}
