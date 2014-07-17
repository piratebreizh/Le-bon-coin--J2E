package org.esgi.module.annonce;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;
import org.esgi.orm.ORM;
import org.esgi.orm.model.Annonce;
import org.esgi.orm.model.User;

public class CreationAnnonce extends AbstractAction {

	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Creation d'une annonce");

		
		if(context.getProperties().get("userConnected") == null){
			context.getVelocityContext().put("utilisateurConnecter", false);		
		}else{
			
			User userConnected = (User) context.getProperties().get("userConnected");
			context.getVelocityContext().put("utilisateurConnecter", true);

			if(context.getRequest().getParameter("region") != null && context.getRequest().getParameter("description") != null && 
					context.getRequest().getParameter("titre") != null && context.getRequest().getParameter("categorie") != null 
					&& context.getRequest().getParameter("prix") != null ){
				
				java.util.Date today = new java.util.Date();
				Date dateDuJouDate = new Date(today.getTime());
				Annonce nouvelleCreation = new Annonce(context.getRequest().getParameter("titre"),context.getRequest().getParameter("description"),
						userConnected.getId(),context.getRequest().getParameter("region"),context.getRequest().getParameter("categorie"),Double.parseDouble(context.getRequest().getParameter("prix")),
						dateDuJouDate);
				
				ORM.save(nouvelleCreation);
				
				
			}
		}
		
		context.addCSSDependency(context.getProperties().get("context")
				+ "/res/css/commun.css");

	
	}

	public String getRoute() {
		return "^/annonce/creation/";
	}


	public String[] getRewriteGroups() {
		return new String[]{"path"};
	}
	
	public static boolean isEmailAdress(String email){
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		java.util.regex.Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}
