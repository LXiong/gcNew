package cn.voicet.gc.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.AcdDao;
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
		acdDao.getAcdMonitorList(ds);
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
}
