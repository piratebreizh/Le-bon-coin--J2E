package org.esgi.web.action;

public class AbstractAction implements IAction {

	@Override
	public void execute(IContext context) throws Exception {
		
	}

	@Override
	public String getRoute() {
		return null;
	}

	@Override
	public String[] getRewriteGroups() {
		return null;
	}

	@Override
	public String getLayout() {
		return "default";
	}

}
