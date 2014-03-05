package org.esgi.module.file;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;


public class FileDownload extends AbstractAction{

	@Override
	public void execute(IContext context) throws Exception {
		String path = (String) context.getParameter("path");
		File f = new File(context.getProperties().get("file.repository").toString() + "/" + path );
		System.out.println(f.getPath());
		if (!f.isDirectory()){
			byte buffer[] = new byte[16384];
			OutputStream outbin = context.getResponse().getOutputStream();
			InputStream inbin = Files.newInputStream(Paths.get(f.getAbsolutePath()));
			while(inbin.read(buffer) != -1){
				outbin.write(buffer);
			}
			inbin.close();
			outbin.close();
		}
	}

	@Override
	public String getRoute() {
		return "/file/list/(.+[^/])$";
	}

	@Override
	public String[] getRewriteGroups() {
		return new String[]{"path"};
	}
	

}
