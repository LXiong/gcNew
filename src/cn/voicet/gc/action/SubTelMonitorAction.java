package cn.voicet.gc.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.SubTelDao;
import cn.voicet.gc.dao.SysParamDao;
import cn.voicet.gc.form.SubTelForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("subTelMonitorAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class SubTelMonitorAction extends BaseAction implements ModelDriven<SubTelForm>{
	private static Logger log = Logger.getLogger(SubTelMonitorAction.class);
	@Resource(name=SubTelDao.SERVICE_NAME)
	private SubTelDao subTelDao;
	
	@Resource(name=SysParamDao.SERVICE_NAME)
	private SysParamDao sysParamDao;
	private SubTelForm subTelForm = new SubTelForm();
	
	public SubTelForm getModel() {
		return subTelForm;
	}
	
	/**
	 * 座席分机监控首页
	 * @return
	 */
	public String list()
	{
		DotSession ds = DotSession.getVTSession(request);
		if(null!=ds.curClientLocal && null!=ds.curCTS)
		{
			log.info("start acdgrp monitor:"+ds.curClientLocal+", "+ds.curCTS+", 2241, -1, -1");
			sysParamDao.startSubtelMonitor(ds);
			request.getSession().setAttribute("subtelm", "subtelmonitorstart");
		}
		subTelDao.querySubTelMonitorList(ds);
		return "show_subtel_monitor";
	}
	
	/**
	 * 座席分机监听
	 * @return
	 */
	public String listen()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("curClientLocal:"+ds.curClientLocal+", cts:"+ds.curCTSLocal+", telnum:"+subTelForm.getTelnum());
		subTelDao.listen(ds, subTelForm);
		return null;
	}
	
}
