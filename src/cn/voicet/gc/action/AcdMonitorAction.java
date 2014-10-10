package cn.voicet.gc.action;
import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.AcdDao;
import cn.voicet.gc.dao.SysParamDao;
import cn.voicet.gc.form.AcdForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("acdMonitorAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class AcdMonitorAction extends BaseAction implements ModelDriven<AcdForm>{
	private static Logger log = Logger.getLogger(AcdMonitorAction.class);
	@Resource(name=AcdDao.SERVICE_NAME)
	private AcdDao acdDao;
	
	@Resource(name=SysParamDao.SERVICE_NAME)
	private SysParamDao sysParamDao;
	private AcdForm acdForm = new AcdForm();
	
	public AcdForm getModel() {
		return acdForm;
	}
	
	/**
	 * 业务监控状态列表
	 * @return
	 */
	public String list()
	{
		DotSession ds = DotSession.getVTSession(request);
		if(null!=ds.curClientLocal && null!=ds.curCTS)
		{
			log.info("start acdgrp monitor:"+ds.curClientLocal+", "+ds.curCTS+", 2234, -1, -1");
			sysParamDao.startAcdMonitor(ds);
			request.getSession().setAttribute("acdm", "acdmonitorstart");
		}
		acdDao.getAcdMonitorList(ds);
		log.info("list size:"+ds.list.size());
		return "show_acd_monitor";
	}
	
	/**
	 * 设置外呼主叫
	 */
	public String setCaller()
	{
		DotSession ds = DotSession.getVTSession(request);		
		log.info("cts:"+ds.curCTS+", grpid:"+acdForm.getGrpid()+", ani:"+acdForm.getAni());
		acdDao.setCaller(ds,acdForm);
		return null;
	}
	
	/**
	 * 添加减少中继数
	 * @throws IOException 
	 */
	public String editTrunk() throws IOException
	{
		DotSession ds = DotSession.getVTSession(request);		
		log.info("cts:"+ds.curCTS+", grpid:"+acdForm.getGrpid()+", trknum:"+acdForm.getTrknum());
		acdDao.changeTrunkByGrpid(ds,acdForm);
		JSONObject json = new JSONObject();
		json.put("status", "ok");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		return null;
	}
	
	public String editCallState() throws IOException
	{
		DotSession ds = DotSession.getVTSession(request);		
		log.info("cts:"+ds.curCTS+", grpid:"+acdForm.getGrpid()+", callstate:"+acdForm.getCallstate());
		acdDao.changeCallState(ds,acdForm);
		JSONObject json = new JSONObject();
		json.put("status", "ok");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		return null;
	}
}

