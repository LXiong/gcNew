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
		request.setAttribute("ani", sysParamDao.getAni());
		request.setAttribute("maxwait", sysParamDao.getMaxwait());
		return "show_sysparam";
	}
	
	public String save()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("ani:"+ani+", maxwait:"+maxwait);
		sysParamDao.saveParam(ds, ani, maxwait);
		return null;
	}
	
	private String ani;
	private int maxwait;
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
}
