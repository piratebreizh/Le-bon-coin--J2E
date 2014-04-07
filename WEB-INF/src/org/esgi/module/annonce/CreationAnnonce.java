package org.esgi.module.annonce;

import java.util.regex.Pattern;

import org.esgi.web.action.AbstractAction;
import org.esgi.web.action.IContext;
import org.esgi.orm.ORM;
import org.esgi.orm.model.Annonce;

public class CreationAnnonce extends AbstractAction {

	public void execute(IContext context) throws Exception {
		context.getVelocityContext().put("title", "Creation d'une annonce");

		/*

		context.addCSSDependency(context.getProperties().get("context")
				+ "/res/css/commun.css");

		//GESTION DES ERREURS DE SAISIES
		int Erreur=0;
		/*Integer region=Integer.parseInt(context.getRequest().getParameter("region"));
		String cp=context.getRequest().getParameter("codepostal");
		Integer catego=Integer.parseInt(context.getRequest().getParameter("catego"));
		Integer company_ad=Integer.parseInt(context.getRequest().getParameter("company_ad"));
		Integer typeann=Integer.parseInt(context.getRequest().getParameter("typeann"));
		String name=context.getRequest().getParameter("name");
		String email=context.getRequest().getParameter("email");
		String phone=context.getRequest().getParameter("phone");
		String subject=context.getRequest().getParameter("subject");
		String body=context.getRequest().getParameter("body");
		double price=double.parseDouble(context.getRequest().getParameter("price"));
		
		Integer region=0;
		String cp="";
		Integer catego=0;
		Integer company_ad=0;
		Integer typeann=0;
		String name="";
		String email="";
		String phone="";
		String subject="";
		String body="";
		double price=0.0;
		/*
		if (Integer.parseInt(context.getRequest().getParameter("region"))==0) Erreur=1;
		if (context.getRequest().getParameter("codepostal").length()<5) Erreur=2;
		if (Integer.parseInt(context.getRequest().getParameter("catego"))==0) Erreur=3;
		if (context.getRequest().getParameter("name").length()<3) Erreur=4;
		if (!isEmailAdress(context.getRequest().getParameter("email"))) Erreur=5;
		if (context.getRequest().getParameter("phone").matches("[0-9]*")) Erreur=6;
		if (context.getRequest().getParameter("subject").length()<1) Erreur=7;
		if (!isNumeric(context.getRequest().getParameter("price"))) Erreur=8;
		
		switch (Erreur) {
		//pas d'erreur
		case 0:
			//Annonce a = new Annonce(region, cp, catego, company_ad, typeann, name, email, phone, subject, body, price);
			
			Annonce a = new Annonce();
			a.setBody(body);
			a.setCatego(catego);
			a.setCompany_ad(company_ad);
			a.setCp(cp);
			a.setEmail(email);
			a.setName(name);
			a.setPhone(phone);
			a.setPrice(price);
			a.setRegion(region);
			a.setSubject(subject);
			a.setTypeann(typeann);
			ORM.save(a);

			break;
			
		//erreur
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		}
		*/
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
