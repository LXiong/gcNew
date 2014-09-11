package cn.voicet.gc.web.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.service.BlackService;
import cn.voicet.gc.web.form.BlackForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("blackAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class BlackAction extends BaseAction implements ModelDriven<BlackForm>{
	private static Logger log = Logger.getLogger(BlackAction.class);
	@Resource(name=BlackService.SERVICE_NAME)
	private BlackService blackService;
	private BlackForm blackForm = new BlackForm();
	
	public BlackForm getModel() {
		return blackForm;
	}
	
	public String home(){
		DotSession ds = DotSession.getVTSession(request);
		log.info("telnum:"+blackForm.getTelnum());
		blackService.queryBlackList(ds, blackForm);
		log.info("---------- blackList load success ----------");
		return "show_blacklist";
	}
	
	/**
	 * 添加或更新黑名单
	 * @return 黑名单列表页面
	 */
	public String saveTelnum(){
		log.info("bid:"+blackForm.getBid()+", telnum:"+blackForm.getTelnum()+" noteinfo:"+blackForm.getNoteinfo());
		blackService.saveTelnum(blackForm);
		log.info("---------- save telnum success ----------");
		blackForm.setTelnum(null);
		return home();
	}
	
	public String deleteTelnum(){
		log.info("bid:"+blackForm.getBid());
		blackService.deleteTelnumByBid(blackForm);
		log.info("---------- delete telnum success ----------");
		return home();
	}
	
}
