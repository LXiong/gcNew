package cn.voicet.gc.web.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.SubTelDao;
import cn.voicet.gc.web.form.SubTelForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("subTelAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class SubTelAction extends BaseAction implements ModelDriven<SubTelForm>{
	private static Logger log = Logger.getLogger(SubTelAction.class);
	@Resource(name=SubTelDao.SERVICE_NAME)
	private SubTelDao subTelDao;
	private SubTelForm subTelForm = new SubTelForm();
	
	public SubTelForm getModel() {
		return subTelForm;
	}
	
	public String home()
	{
		DotSession ds = DotSession.getVTSession(request);
		subTelDao.querySubTelList(ds);
		return "show_subtel";
	}
	
	/**
	 * 添加或更新座席分机
	 * @return
	 */
	public String saveSubTel()
	{
		subTelDao.saveSubTel(subTelForm);
		return home();
	}
	
	/**
	 * 删除座席分机
	 */
	public String deleteSubTel()
	{
		log.info("cts:"+subTelForm.getCts()+", telid:"+subTelForm.getTelid());
		subTelDao.deleteSubTelByTelid(subTelForm);
		log.info("delete subtel ["+subTelForm.getTelid()+"] success!");
		return home();
	}
}
