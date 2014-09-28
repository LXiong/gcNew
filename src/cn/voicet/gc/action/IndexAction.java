package cn.voicet.gc.action;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.util.DotRoleMenu;
import cn.voicet.util.DotSession;

@Controller("indexAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class IndexAction extends BaseAction{

	private static Logger log = Logger.getLogger(IndexAction.class);
	public String index(){
		String xmlFilePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/appconfig-item.xml");
		DotRoleMenu roleMenu = (DotRoleMenu)ServletActionContext.getServletContext().getAttribute("vta");
		if (roleMenu == null) {
			roleMenu = new DotRoleMenu();
			roleMenu.loadInfoFromXML(xmlFilePath);
			ServletActionContext.getServletContext().setAttribute("vta", roleMenu);
		}
		log.info("to login");
		if (request.getSession().getAttribute("vts")==null) {
			DotSession ds = new DotSession();
			request.getSession().setAttribute("vts", ds);
		}
		DotSession ds=DotSession.getVTSession(request);
		ds.curClientLocal = agt;
		ds.curCTSLocal = cts;
		log.info("curClientLocal:"+ds.curClientLocal);
		log.info("curCTSLocal:"+ds.curCTSLocal);
		return "show_login";
	}
	
	private String agt;
	private String cts;
	public String getAgt() {
		return agt;
	}
	public void setAgt(String agt) {
		if(null!=agt){
			this.agt = agt.toLowerCase();
		}else{
			this.agt = agt;
		}
	}
	public String getCts() {
		return cts;
	}
	public void setCts(String cts) {
		if(null!=cts){
			this.cts = cts.toLowerCase();
		}else{
			this.cts = cts;
		}
	}
}
