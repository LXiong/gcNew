package cn.voicet.gc.web.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.BlackDao;
import cn.voicet.gc.web.form.BlackForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("blackAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class BlackAction extends BaseAction implements ModelDriven<BlackForm>{
	private static Logger log = Logger.getLogger(BlackAction.class);
	@Resource(name=BlackDao.SERVICE_NAME)
	private BlackDao blackDao;
	private BlackForm blackForm = new BlackForm();
	
	public BlackForm getModel() {
		return blackForm;
	}
	
	public String home(){
		DotSession ds = DotSession.getVTSession(request);
		log.info("telnum:"+blackForm.getTelnum());
		blackDao.queryBlackList(ds, blackForm);
		log.info("---------- blackList load success ----------");
		return "show_blacklist";
	}
	
	/**
	 * 添加或更新黑名单
	 * @return 黑名单列表页面
	 */
	public String saveTelnum(){
		log.info("bid:"+blackForm.getBid()+", telnum:"+blackForm.getTelnum()+" noteinfo:"+blackForm.getNoteinfo());
		blackDao.saveTelnum(blackForm);
		log.info("---------- save telnum success ----------");
		blackForm.setTelnum(null);
		return home();
	}
	
	public String deleteTelnum(){
		log.info("bid:"+blackForm.getBid());
		blackDao.deleteTelnumByBid(blackForm);
		log.info("---------- delete telnum success ----------");
		return home();
	}
	
	/**
	 * 清空黑名单 
	 * @return
	 */
	public String emptyBlack()
	{
		blackDao.emptyBlack();
		log.info("---------- empte black telnum success ----------");
		return home();
	}
}
