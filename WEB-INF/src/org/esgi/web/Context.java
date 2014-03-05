package org.esgi.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.esgi.web.action.IContext;

public class Context implements IContext {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private VelocityContext velocityContext;
	public Properties properties;
	
	Map<String, Object> mapParameters;
	Map<String, Object> mapFragments;
	
	public String pageTitle;
	public String pageMetaDescription;
	public List<String> keywords;
	
	public Set<String> jsUrls;
	public Set<String> cssUrls;
	
	public List<String> inlineCss;
	public List<String> rawHeaders;

	Context(HttpServletRequest _request, HttpServletResponse _response, Properties properties){
		request = _request;
		response = _response;
		this.properties = properties;
		this.velocityContext = new VelocityContext();
		this.velocityContext.put("context", this);
		
		mapParameters = new HashMap<>();
		mapFragments = new HashMap<>();
		keywords = new ArrayList<String>();
		
		jsUrls = new TreeSet<String>();
		cssUrls = new TreeSet<String>();
		inlineCss = new ArrayList<>();
	}
	
	@Override
	public Object getParameter(String key) {
		
		return mapParameters.get(key);
	}

	@Override
	public void setParameter(String key, Object o) {
		mapParameters.put(key, o);
	}

	@Override
	public HttpServletRequest getRequest() {

		return this.request;
	}

	@Override
	public HttpServletResponse getResponse() {
		return this.response;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}
	@Override
	public Object getFragment(String fragment) {
		return mapFragments.get(fragment);
	}

	@Override
	public void setFragment(String fragment, Object o) {
		mapFragments.put(fragment, o);
		
	}
	
	@Override
	public VelocityContext getVelocityContext(){
		return velocityContext;
	}
	
	@Override
	public void addKeyword(String keyword) {
		keywords.add(keyword);
	}
	@Override
	public void setDescription(String description) {
		pageMetaDescription = description;
	}
	public String getDescription() {
		return pageMetaDescription;
	}
	@Override
	public void addJSDependency(String url) {
		jsUrls.add(url);
	}
	@Override
	public void addCSSDependency(String url) {
		cssUrls.add(url);
	}
	@Override
	public void addInlineCSS(String cssRule) {
		inlineCss.add(cssRule);
	}
	@Override
	public void addRawHeader(String rawHeadLine) {
		rawHeaders.add(rawHeadLine);
	}

	@Override
	public String getPageTitle() {
		if(this.getVelocityContext().get("title") != null){
			return (String) this.getVelocityContext().get("title");
		}
		return pageTitle;
	}

	@Override
	public void setPageTitle(String title) {
		pageTitle = title;
	}
	
	public String genKeywords(){
		if(keywords.size() > 0){
			StringBuffer impKeywords = new StringBuffer();
			Iterator<String> i = this.keywords.iterator();
			while(i.hasNext()) {
				impKeywords.append(i.next());
				if(i.hasNext()) impKeywords.append(",");
			}
			return impKeywords.toString();
		}
		return null;
	}

	List<String> onJsReady = new ArrayList<>();
	@Override
	public void addOnJsReady(String str) {
		onJsReady.add(str);
	}
	public List<String> getOnJsReady(){
		return onJsReady;
	}
	public Set<String> getJsUrls() {
		return jsUrls;
	}
	

}
