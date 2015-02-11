package cn.voicet.gc.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.SysParamDao;
import cn.voicet.util.DotSession;

@Controller("sysParamAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class SysParamAction extends BaseAction{

	private static Logger log = Logger.getLogger(SysParamAction.class);
	@Resource(name=SysParamDao.SERVICE_NAME)
	private SysParamDao sysParamDao;
	
	public String list() {
		log.info("defcts:"+defcts);
		request.setAttribute("ani", sysParamDao.getAni(defcts));
		request.setAttribute("maxwait", sysParamDao.getMaxwait(defcts));
		return "show_sysparam";
	}
	
	public String save()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("defcts:"+defcts+", ani:"+ani+", maxwait:"+maxwait);
		sysParamDao.saveParam(ds, defcts, ani, maxwait);
		return null;
	}
	
	private String ani;
	private int maxwait;
	private String defcts = "cts100";
	public String getAni() {
		return ani;
	}
	public void setAni(String ani) {
		this.ani = ani;
	}
	public int getMaxwait() {
		return maxwait;
	}
	public void setMaxwait(int maxwait) {
		this.maxwait = maxwait;
	}
	//
	public String getDefcts() {
		return defcts;
	}
	public void setDefcts(String defcts) {
		this.defcts = defcts;
	}
	//
	public String init()
	{
		return "sysInitPage";
	}

}
