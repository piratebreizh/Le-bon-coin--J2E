package org.esgi.module.file;

import java.io.File;
import java.io.FileNotFoundException;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class FileDelete extends AbstractAction {

	@Override
	public void execute(IContext context) throws Exception {
		File repo = new File((String) context.getProperties().get("file.repository"));
		
		File file = new File(repo, (String) context.getParameter("path"));
		if(file.delete()){
			throw new FileNotFoundException("Le fichier n'a pas pu etre supprimé.");
		}
		context.getResponse().sendRedirect(context.getRequest().getContextPath()+ "/file/list/" + context.getParameter("path").toString().substring(0,context.getParameter("path").toString().lastIndexOf("/")) + "/");
	}

	@Override
	public String getRoute() {
		return "/file/delete/(.+[^/])$";
	}

	@Override
	public String[] getRewriteGroups() {
		return new String[]{"path"};
	}

}
