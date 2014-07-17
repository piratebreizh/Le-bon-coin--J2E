package org.esgi.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.Velocity;
import org.esgi.module.annonce.AjoutPhotoAnnonce;
import org.esgi.module.annonce.CreationAnnonce;
import org.esgi.module.file.FileDelete;
import org.esgi.module.file.FileDownload;
import org.esgi.module.file.FileList;
import org.esgi.module.file.FileUpload;
import org.esgi.module.index.Index;
import org.esgi.module.recherche.AnnonceEnDetail;
import org.esgi.module.recherche.Recherche;
import org.esgi.module.recherche.ResultatRecherche;
import org.esgi.module.user.Connect;
import org.esgi.module.user.Connexion;
import org.esgi.module.user.Enregistrement;
import org.esgi.module.user.EspacePerso;
import org.esgi.module.user.Inscription;
import org.esgi.module.user.VosAnnonces;
import org.esgi.orm.model.User;
import org.esgi.web.action.IAction;
import org.esgi.web.action.IContext;
import org.esgi.web.layout.LayoutRenderer;
import org.esgi.web.route.Router;

/**
 * Le frontcontroller est
 * le lien entre tomcat et votre
 * framework. Il g��n��re le context pour 
 * chaque requete et peu contenir des filtres 
 * d'entree et de sortie pour chaque requete
 * exemple validateur de champs ou compression gzip.
 */
public class FrontController extends HttpServlet{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Router router = new Router();
	Properties properties = new Properties();
	private LayoutRenderer layoutRender;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		String configFile = config.getServletContext().getInitParameter("config");
		String path = config.getServletContext().getRealPath("/");

		try {
			properties.load(new FileInputStream(path +"/" + configFile));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Should be in init.

		Properties configVelocity = new Properties();
		configVelocity.setProperty("file.resource.loader.path", config.getServletContext().getRealPath("/") + properties.getProperty("template.path")+ "/");
		Velocity.init(configVelocity);

		properties.put("realPath", config.getServletContext().getRealPath("/"));

		registerAction(new FileList());
		registerAction(new FileDownload());
		registerAction(new FileUpload());
		registerAction(new FileDelete());
		registerAction(new Index());
		registerAction(new Connect());

		registerAction(new Inscription());
		registerAction(new Enregistrement());
		registerAction(new Connexion());
		registerAction(new EspacePerso());
		registerAction(new CreationAnnonce());
		registerAction(new AjoutPhotoAnnonce());
		registerAction(new Recherche());
		registerAction(new ResultatRecherche());
		registerAction(new AnnonceEnDetail());
		registerAction(new VosAnnonces());
		layoutRender = new LayoutRenderer();
	}
	
	@Override
	public void service(HttpServletRequest 
			request, HttpServletResponse response)
					throws ServletException, IOException {

		String url = request.getPathInfo();
		IContext context = createContext(request, response);

		if(url == null || url.equals("/")){
			context.getResponse().sendRedirect(context.getRequest().getContextPath() + "/index/");
			return;
		}
		
		if(url.equals("/user/logout/")){
			HttpSession session = request.getSession();
			session.invalidate();
			properties.remove("userConnected");
			context.getResponse().sendRedirect(context.getRequest().getContextPath() + "/index/");
			return;
		}else{		
			//Récupération de l'utilisateur connecté
			HttpSession session = request.getSession();
	       	User userConnected = (User) session.getAttribute("userConnected");
	       	if(userConnected!=null){
	    		properties.put("userConnected", userConnected);
	       	}
		}
		
		IAction action = router.find(url, context);
		
		properties.put("context", request.getContextPath());
		

		if (null != action){

			if (null == action.getLayout()) {
				try {
					action.execute(context);
				} catch (Exception e) {
					throw new ServletException(e);
				}
			} else {
				try {
					layoutRender.render(action, context, router);
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}
		}

	}

	private IContext createContext(HttpServletRequest request, 
			HttpServletResponse response) {
		return new Context(request, response, properties);
	}

	public void registerAction(IAction action) {
		router.register(action);
	}
}
