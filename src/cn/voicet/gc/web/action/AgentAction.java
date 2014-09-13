package cn.voicet.gc.web.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.AgentDao;
import cn.voicet.gc.web.form.AgentForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("agentAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class AgentAction extends BaseAction implements ModelDriven<AgentForm>{
	private static Logger log = Logger.getLogger(AgentAction.class);
	@Resource(name=AgentDao.SERVICE_NAME)
	private AgentDao agentDao;
	private AgentForm agentForm = new AgentForm();
	
	public AgentForm getModel() {
		return agentForm;
	}
	
	public String home()
	{
		DotSession ds = DotSession.getVTSession(request);
		agentDao.queryAgentList(ds);
		return "show_agent";
	}
	
	/**
	 * 添加或更新话务员
	 * @return
	 */
	public String saveAgent()
	{
		log.info("agttxt[] length:"+agentForm.getAgttxt().length);
		agentDao.saveAgent(agentForm);
		log.info("save agent ["+agentForm.getAgttxt()[3]+"] success");
		return home();
	}
	
	/**
	 * 删除话务员
	 * @return
	 */
	public String deleteAgent()
	{
		log.info("account:"+agentForm.getAccount());
		agentDao.deleteAgentByAccount(agentForm);
		log.info("delete agent ["+agentForm.getAccount()+"] success");
		return home();
	}
}
