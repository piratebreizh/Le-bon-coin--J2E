package org.esgi.module.file;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;

public class FileUpload extends AbstractAction {

	@Override
	public void execute(IContext context) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(context.getRequest());
		if(isMultipart){

			FileItemFactory fif = new DiskFileItemFactory(); 
			
			ServletFileUpload servletFileUpload = new ServletFileUpload(fif); 
			
			try{

				List<FileItem> fileList = servletFileUpload.parseRequest(context.getRequest());
				FileItem uploadedFile = fileList.get(0);
				
				//test if form have filename
				if(uploadedFile.getName().equals("")){
					throw new Exception("Le nom de fichier est vide.");
				}
				
				//Save new file
				System.out.println("File uploaded to : " + context.getProperties().get("file.repository") + "/" + context.getParameter("path") + uploadedFile.getName());
				File file = new File(context.getProperties().get("file.repository") + "/" + context.getParameter("path") + uploadedFile.getName());
				uploadedFile.write(file);
				
				context.getResponse().sendRedirect(context.getRequest().getContextPath() + "/file/list" + context.getParameter("path"));
				
			}
			catch (Exception e){
				throw e;
			}
		}else{
			context.getResponse().sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			throw new Exception("500");
		}
	}

	@Override
	public String getRoute() {
		return "^/file/upload/(.*/)$";
	}

	@Override
	public String[] getRewriteGroups() {
		return new String[]{"path"};
	}

}
