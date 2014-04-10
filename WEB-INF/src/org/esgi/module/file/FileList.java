package org.esgi.module.file;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class FileList extends AbstractAction {

	@Override
	public void execute(IContext context) throws Exception {
		if(Boolean.parseBoolean(context.getProperties().getProperty("autoindex"))){
			File repo = new File((String) context.getProperties().get("file.repository"));
	
			String path = (String) context.getParameter("path");
	
			File currentDir = new File(repo, path);
			if(currentDir.exists() && currentDir.isDirectory()){
				context.setDescription("Liste des fichiers du repertoire "+ path);
				context.addKeyword("fichier");
				context.addKeyword("dossier");
				context.getVelocityContext().put("title", repo + path);
				context.getVelocityContext().put("items", currentDir.listFiles());
				if(!path.equals("/")){
					context.getVelocityContext().put("parenturl", context.getRequest().getContextPath() + "/file/list" + path.substring(0,path.substring(0, path.length()-2).lastIndexOf("/")) + "/");
				}
			}else{
				context.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}else{
			context.getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
			throw new Exception("Access denied 403");
		}
	}


	public String getRoute() {
		return "^/file/list(.*/)$";
	}


	public String[] getRewriteGroups() {
		return new String[]{"path"};
	}

}
