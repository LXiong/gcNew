package cn.voicet.gc.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.AcdDao;
import cn.voicet.gc.form.AcdForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("acdAnalyAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class AcdAnalyAction extends BaseAction implements ModelDriven<AcdForm>{
	private static Logger log = Logger.getLogger(AcdAnalyAction.class);
	@Resource(name=AcdDao.SERVICE_NAME)
	private AcdDao acdDao;
	private AcdForm acdForm = new AcdForm();
	
	public AcdForm getModel() {
		return acdForm;
	}
	
	public String list()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("form cts:"+ds.curCTS+", sdt:"+acdForm.getSdt()+", edt:"+acdForm.getEdt());
		if(null!=acdForm.getSdt() || null!=acdForm.getEdt())
		{
			ds.cursdt = acdForm.getSdt();
			ds.curedt = acdForm.getEdt();
		}
		log.info("ds cursdt:"+ds.cursdt+", curedt:"+ds.curedt);
		acdDao.queryAcdAnalyList(ds);
		return "show_acd_analy";
	}
	
}
