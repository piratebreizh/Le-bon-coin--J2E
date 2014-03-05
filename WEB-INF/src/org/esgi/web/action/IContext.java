package org.esgi.web.action;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;

public interface IContext {

	Object getParameter(String key);
	void setParameter(String key, Object o);
	Properties getProperties();
	// Object instance must have a toString implementation.
	Object getFragment(String fragment);
	void setFragment(String fragment, Object o);
	String getPageTitle();
	void setPageTitle(String title);
	HttpServletRequest getRequest();
	HttpServletResponse getResponse();
	VelocityContext getVelocityContext();
	void addKeyword(String keyword);
	void setDescription(String description);
	void addJSDependency(String url);
	void addCSSDependency(String url);
	void addInlineCSS(String cssRule);
	void addRawHeader(String rawHeadLine);
	void addOnJsReady(String str);
}