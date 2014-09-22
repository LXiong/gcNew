package cn.voicet.gc.web.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.NtsDao;
import cn.voicet.gc.web.form.NtsForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("ntsAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class NtsAction extends BaseAction implements ModelDriven<NtsForm>{
	private static Logger log = Logger.getLogger(NtsAction.class);
	@Resource(name=NtsDao.SERVICE_NAME)
	private NtsDao ntsDao;
	private NtsForm ntsForm = new NtsForm();
	
	public NtsForm getModel() {
		return ntsForm;
	}
	
	public String home(){
		DotSession ds = DotSession.getVTSession(request);
		ntsDao.queryNtsList(ds, ntsForm);
		return "show_nts";
	}
	
	/**
	 * 保存NTS
	 * @return
	 */
	public String saveNts()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("ntstxt length:"+ntsForm.getNtstxt().length);
		ntsDao.saveNts(ds, ntsForm);
		return null;
	}
	
	/**
	 * 删除NTS
	 * @return
	 */
	public String deleteNts()
	{
		log.info("account:"+ntsForm.getAccount());
		ntsDao.deleteNts(ntsForm);
		return home();
	}
	
}
