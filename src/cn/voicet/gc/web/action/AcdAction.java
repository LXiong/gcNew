package cn.voicet.gc.web.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.AcdDao;
import cn.voicet.gc.web.form.AcdForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("acdAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class AcdAction extends BaseAction implements ModelDriven<AcdForm>{
	private static Logger log = Logger.getLogger(AcdAction.class);
	@Resource(name=AcdDao.SERVICE_NAME)
	private AcdDao acdDao;
	private AcdForm acdForm = new AcdForm();
	
	public AcdForm getModel() {
		return acdForm;
	}
	
	public String home()
	{
		DotSession ds = DotSession.getVTSession(request);
		acdDao.queryAcdList(ds);
		return "show_acd";
	}
	
	/**
	 * 添加或更新业务组
	 * @return
	 */
	public String saveAcd()
	{
		log.info("acdtxt[] length:"+acdForm.getAcdtxt().length);
		acdDao.saveAcd(acdForm);
		log.info("save acd ["+acdForm.getAcdtxt()[2]+"] success");
		return home();
	}
	
	/**
	 * 删除业务组
	 * @return
	 */
	public String deleteAcd()
	{
		log.info("grpid:"+acdForm.getGrpid());
		acdDao.deleteAcdByGrpid(acdForm);
		log.info("delete acd ["+acdForm.getGrpid()+"] success");
		return home();
	}
	
	
}
