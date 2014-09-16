package cn.voicet.gc.web.action;
import java.io.IOException;

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
	 * @throws IOException 
	 */
	public String saveAgent() throws IOException
	{
		log.info("agttxt[] length:"+agentForm.getAgttxt().length);
		String ret = agentDao.saveAgent(agentForm);
		if(ret.equals("ok")){
			log.info("save agent ["+agentForm.getAgttxt()[2]+"] success");
		}
		else if(ret.equals("err_telexists"))
		{
			log.info("save agent ["+agentForm.getAgttxt()[2]+"] fail");
		}
		else if(ret.equals("err_teloraccountexists"))
		{
			log.info("save agent ["+agentForm.getAgttxt()[2]+"] fail");
		}
		else
		{
			log.info("save agent error");
		}
		response.getWriter().print(ret);
		return null;
	}
	
	/**
	 * 删除话务员
	 * @return
	 */
	public String deleteAgent()
	{
		log.info("account:"+agentForm.getAgtid());
		agentDao.deleteAgentByAccount(agentForm);
		log.info("delete agent ["+agentForm.getAgtid()+"] success");
		return home();
	}
	
	/**
	 * 密码初始化
	 * @return
	 */
	public String initAgentpwd()
	{
		log.info("agtid:"+agentForm.getAgtid());
		agentDao.initAgentpwdByAgtid(agentForm);
		log.info("init agent password ["+agentForm.getAgtid()+"] success");
		return null;
	}
}
