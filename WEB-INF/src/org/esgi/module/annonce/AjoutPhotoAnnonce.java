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
import org.esgi.orm.annotations.ORM_SEARCH_WITHOUT_PK;
import org.esgi.orm.model.Annonce;
import org.esgi.orm.model.User;

public class AjoutPhotoAnnonce extends AbstractAction {

	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Ajout de photos");

		if(context.getProperties().get("userConnected") == null){
			context.getVelocityContext().put("utilisateurConnecter", false);		
		}else{

			User userConnected = (User) context.getProperties().get("userConnected");		
			context.getVelocityContext().put("utilisateurConnecter", true);
			context.getVelocityContext().put("idAnnonce", "1");
			/*
			ORM_SEARCH_WITHOUT_PK critere = new ORM_SEARCH_WITHOUT_PK();
			critere.addConstrainte("user", String.valueOf(userConnected.getId()));
			Annonce derniereAnnonceCree = (Annonce) ORM.loadWithOutPrimaryKey(Annonce.class, critere);
			*/

			boolean isMultipart = ServletFileUpload.isMultipartContent(context.getRequest());
			if(isMultipart){

				FileItemFactory fif = new DiskFileItemFactory(); 
				
				ServletFileUpload servletFileUpload = new ServletFileUpload(fif); 
				
				try{

					List<FileItem> fileList = servletFileUpload.parseRequest(context.getRequest());
					
					if(fileList.size()==0)
						return;
					
					for(int numPhoto = 0 ; numPhoto< 3 ; numPhoto++){
						FileItem uploadedFile = fileList.get(numPhoto);
						String idAnnonce = context.getRequest().getParameter("idAnnonce");
						
						if(idAnnonce== null)
							idAnnonce = "1";
						Annonce annonce = (Annonce) ORM.load(Annonce.class, idAnnonce);

						//Si fichier et ID offre sont renseignés
						if(uploadedFile.getName()!= null && !uploadedFile.getName().equals("") 
								&& idAnnonce!= null && !idAnnonce.equals("")){					
							//on enregistre l'image
							String path = context.getProperties().get("realPath") + "/" + "res/img/offres/" + idAnnonce + "/";
							System.out.println("File uploaded to : " + path + uploadedFile.getName());
							//On créé les répertoires s'ils n'existent pas
							File rep = new File(path);
							rep.mkdirs();
							String fileUploadName = uploadedFile.getName();
							String ext = fileUploadName.substring(fileUploadName.lastIndexOf("."), fileUploadName.length());
							File file = new File(path + "photo"+numPhoto+ext);
							uploadedFile.write(file);
							
							annonce.setPhoto(numPhoto, "offres/"+idAnnonce+"/photo"+numPhoto);
						}
						ORM.save(annonce);
					}
				}
				catch (Exception e){
					throw e;
				}
			}
			
			
				
		}
		
		context.addCSSDependency(context.getProperties().get("context")
				+ "/res/css/commun.css");
	
	}

	public String getRoute() {
		return "^/annonce/ajoutphoto/";
	}


	public String[] getRewriteGroups() {
		return new String[]{"path"};
	}
	

}
